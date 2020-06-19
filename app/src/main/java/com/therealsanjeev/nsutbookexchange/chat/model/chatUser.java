package com.therealsanjeev.nsutbookexchange.chat.model;

public class chatUser {

    private String id;
    private String name;
    private String imageURL;


    public chatUser(String id, String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
    }
    public chatUser(){

    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }


}
