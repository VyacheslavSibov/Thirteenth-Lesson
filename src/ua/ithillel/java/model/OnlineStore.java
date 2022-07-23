package ua.ithillel.java.model;

import ua.ithillel.java.model.product.Buying;

public class OnlineStore extends Store {
    private String url;

    public OnlineStore(String name, Buying[] sales, String url) {
        super(name, sales);
        this.url = url;
    }

    public String getUrl() {

        return url;
    }
}

