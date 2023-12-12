package icebase.app.types;

public class Store {
    private final String id;
    private final String sellerId;
    private final String name;

    public Store(String id, String sellerId, String name) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getSellerId() {
        return this.sellerId;
    }

    public String getName() {
        return this.name;
    }

    public String toCSVString() {
        return String.join(",", id, sellerId, name);
    }
}
