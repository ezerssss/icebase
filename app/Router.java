package icebase.app;

import java.util.Locale.Category;

import icebase.app.screens.*;
import icebase.app.screens.buyers.*;
import icebase.app.screens.home.*;
import icebase.app.screens.sellers.*;
import icebase.app.enums.SCREEN_ENUM;

public class Router {
    public static void navigate(SCREEN_ENUM screen) {
        switch (screen) {
            case SPLASH_SCREEN:
                (new SplashScreen()).display();
                break;
            case LOGIN:
                (new Login()).display();
                break;
            case SIGN_UP:
                (new SignUp()).display();
                break;
            case HOME:
                (new Home()).display();
                break;
            case GLOBAL_SEARCH:
                (new GlobalSearch()).display();
                break;
            case STORE_LIST:
                (new StoreList()).display();
                break;
            case POST_ITEM:
                (new PostItem()).display();
                break;
            case SELLING_ITEM_LIST:
                (new SellingItemList()).display();
                break;
            case SELLING_VIEW:
                (new SellingView()).display();
                break;
            default:
                break;
        }
    }

    // NOTE: FOR FUTURE SCREENS THAT NEED PARAMETERS
    // public static void nagivate(SCREEN_ENUM screen, Store store) {
    // switch (screen) {
    // case BUYING_VIEW:
    // (new BuyingView(store)).display();
    // break;
    // default:
    // break;
    // }
    // }

    // public static void nagivate(SCREEN_ENUM screen, Store store, Category
    // category) {
    // switch (screen) {
    // case BUYING_ITEM_LIST:
    // (new BuyingItemList(store, category)).display();
    // break;
    // }
    // }
}
