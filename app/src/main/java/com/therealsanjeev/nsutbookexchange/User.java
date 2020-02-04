package com.therealsanjeev.nsutbookexchange;

public class User {
    public String book;

    public User(String book, String author, String price, String sellerName,String sellerEmail, String sellerNo) {
        this.book = book;
        this.author = author;
        this.price = price;
        this.sellerName = sellerName;
        this.sellerEmail=sellerEmail;
        this.sellerNo = sellerNo;
    }
    public String sellerEmail;

    public String author;
    public String price;
    public String sellerName;
    public String sellerNo;
}
