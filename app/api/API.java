package icebase.app.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import icebase.app.enums.CATEGORY_ENUM;
import icebase.app.enums.COLLECTION_ENUM;
import icebase.app.enums.fields.ITEM_FIELD;
import icebase.app.enums.fields.STORE_FIELD;
import icebase.app.exceptions.BuyException;
import icebase.app.exceptions.CategoryDoesNotExistException;
import icebase.app.exceptions.InvalidUserDataException;
import icebase.app.exceptions.UnauthorizedException;
import icebase.app.types.Store;
import icebase.app.types.items.Item;
import icebase.icebase.Auth;
import icebase.icebase.Db;
import icebase.icebase.Doc;
import icebase.icebase.QueryList;
import icebase.icebase.User;
import icebase.icebase.enums.USER_FIELD;

public class API {
    private static final Db db = Db.getDb();
    private static final Auth auth = Auth.getAuth();

    // USER
    public static void setupUser(User user, String storeName, double startingMoney) throws IOException {
        String storeId = UUID.randomUUID().toString();

        Store store = new Store(storeId, user.getId(), storeName);
        Doc storeDocument = db.collection(COLLECTION_ENUM.STORES.value).doc(storeId);
        storeDocument.setData(store.toCSVString());

        user.setStoreId(storeId);
        user.setMoney(startingMoney);
    }

    // STORES AND ITEMS
    public static Store getUserStore(String userId) throws IOException, InvalidUserDataException {
        QueryList docs = db.collection(COLLECTION_ENUM.STORES.value).where(userId,
                STORE_FIELD.SELLER_ID.index);

        // Should not happen since every user has a store
        if (docs.isEmpty()) {
            throw new InvalidUserDataException();
        }

        // Get the first match
        Doc doc = docs.get(0);
        String[] data = doc.data().split(",");

        return new Store(data[STORE_FIELD.STORE_ID.index], data[STORE_FIELD.SELLER_ID.index],
                data[STORE_FIELD.STORE_NAME.index]);
    }

    public static List<Store> getStores() throws IOException {
        List<Doc> docs = db.collection(COLLECTION_ENUM.STORES.value).getDocs();
        List<Store> stores = new ArrayList<>();

        for (Doc doc : docs) {
            String[] dataFields = doc.data().split(",");
            String storeId = dataFields[STORE_FIELD.STORE_ID.index];
            String sellerId = dataFields[STORE_FIELD.SELLER_ID.index];
            String name = dataFields[STORE_FIELD.STORE_NAME.index];

            stores.add(new Store(storeId, sellerId, name));
        }

        return stores;
    }

    public static List<Item> getStoreItems(Store store) throws IOException, CategoryDoesNotExistException {
        List<Item> items = new ArrayList<>();
        String storeId = store.getId();

        QueryList docs = db.collection(COLLECTION_ENUM.ITEMS.value).where(storeId, ITEM_FIELD.STORE_ID.index);

        for (Doc doc : docs) {
            Item item = ItemFactory.createItem(doc);

            items.add(item);
        }

        return items;
    }

    public static List<Item> getStoreItems(Store store, CATEGORY_ENUM category)
            throws IOException, CategoryDoesNotExistException {
        List<Item> items = new ArrayList<>();

        String storeId = store.getId();

        QueryList docs = db.collection(COLLECTION_ENUM.ITEMS.value).where(storeId, ITEM_FIELD.STORE_ID.index)
                .where(category.value, ITEM_FIELD.CATEGORY.index);

        for (Doc doc : docs) {
            Item item = ItemFactory.createItem(doc);

            items.add(item);
        }

        return items;
    }

    public static void buy(Item item, int amount)
            throws IOException, UnauthorizedException, CategoryDoesNotExistException, BuyException {
        // The currently logged in user will always be the buyer
        User buyer = auth.getUser();

        // Check if user is actually logged in
        if (buyer == null) {
            throw new UnauthorizedException();
        }

        // Verify if the item actually exists in the db
        QueryList itemQuery = db.collection(COLLECTION_ENUM.ITEMS.value).where(item.getId(), ITEM_FIELD.ITEM_ID.index);

        if (itemQuery.isEmpty()) {
            throw new BuyException("Item not found.");
        }

        // Find the seller of the item
        QueryList sellerQuery = db.collection(COLLECTION_ENUM.USERS.value).where(item.getSellerId(),
                USER_FIELD.USER_ID.index);

        if (sellerQuery.isEmpty()) {
            throw new BuyException("Item has invalid data");
        }

        Doc sellerDoc = sellerQuery.get(0);
        User seller = new User(sellerDoc);

        // A user can't buy his own products
        if (buyer.getId().equals(seller.getId())) {
            throw new BuyException("You can't buy your own product!");
        }

        // Check if there is enough stock for the item
        if (item.getStock() < amount) {
            throw new BuyException("The item does not have enough stocks.");
        }

        // Check if buyer has enough money
        if (buyer.getMoney() < item.getPrice()) {
            throw new BuyException("You don't have enough money.");
        }

        // We can now be sure that the transaction is valid, so we can now make changes
        // to the db
        item.decreaseStock(amount);

        double totalPrice = item.getPrice() * amount;

        buyer.decreaseMoney(totalPrice);
        seller.increaseMoney(totalPrice);
    }

    public static void sell(String... data) throws UnauthorizedException, IOException {
        // The currently logged in user will always be the buyer
        User seller = auth.getUser();

        // Check if user is actually logged in
        if (seller == null) {
            throw new UnauthorizedException();
        }

        String itemId = UUID.randomUUID().toString();
        String storeId = seller.getStoreId();
        String sellerId = seller.getId();

        String itemData = String.join(",", data);
        itemData = String.join(",", itemId, storeId, sellerId, itemData);

        Doc itemDoc = db.collection(COLLECTION_ENUM.ITEMS.value).doc(itemId);
        itemDoc.setData(itemData);
    }

    // GLOBAL ITEM SEARCH
    public static List<Item> itemSearch(String query) throws IOException, CategoryDoesNotExistException {
        List<Item> items = new ArrayList<>();

        QueryList docs = db.collection(COLLECTION_ENUM.ITEMS.value).where(query, ITEM_FIELD.ITEM_NAME.index);

        for (Doc doc : docs) {
            Item item = ItemFactory.createItem(doc);

            items.add(item);
        }

        return items;
    }
}
