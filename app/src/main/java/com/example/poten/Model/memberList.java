package com.example.poten.Model;

public class memberList {
    private String id;
    private int image;

    public memberList(String id, int image){
        this.id = id;
        this.image = image;
    }

    public String getId() {return id;}

    public void setTitle(String id) {this.id = id;}

    public int getImage() {return image;}

    public void setImage(int image) {this.image = image;}
}
