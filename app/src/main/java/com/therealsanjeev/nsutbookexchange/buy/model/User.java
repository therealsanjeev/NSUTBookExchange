package com.therealsanjeev.nsutbookexchange.buy.model;

public class User {

    private String author;
    private String price;
    private String sellerName;
    private String sellerNo;
    private String book;
    private String sellerEmail;

    public String getBook() {
        return book;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getAuthor() {
        return author;
    }

    public String getPrice() {
        return price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public User(String book, String author, String price, String sellerName,String sellerEmail, String sellerNo) {

        this.book = book;
        this.author = author;
        this.price = price;
        this.sellerName = sellerName;
        this.sellerEmail=sellerEmail;
        this.sellerNo = sellerNo;
    }

    public User() {
    }
}
