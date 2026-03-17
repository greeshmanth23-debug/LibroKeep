package com.librokeep.model;

public class Book {

    private int bid;
    private String title;
    private int qty;

    public Book(int bid, String title, int qty) {
        this.bid = bid;
        this.title = title;
        this.qty = qty;
    }

    public int getBid() {
        return bid;
    }

    public String getTitle() {
        return title;
    }

    public int getQty() {
        return qty;
    }

    // 🔥 IMPORTANT FIX
    @Override
    public String toString() {
        return bid + " | " + title + " | " + qty;
    }
}