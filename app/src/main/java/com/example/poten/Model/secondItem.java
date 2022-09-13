package com.example.poten.Model;

public class secondItem {
    private String id;
    private int image;
    private int likeCount;
    private int commentCount;
    private String subtitle;

    public secondItem(String id, int image, int likeCount, int commentCount, String subtitle){
        this.id = id;
        this.image = image;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.subtitle = subtitle;
    }

    public String getId() {return id;}

    public void setTitle(String id) {this.id = id;}

    public int getImage() {return image;}

    public void setImage(int image) {this.image = image;}

    public int getLikeCount() {return likeCount;}

    public void setLikeCount(int likeCount) {this.likeCount = likeCount;}

    public int getCommentCount() {return commentCount;}

    public void setCommentCount(int commentCount) {this.commentCount = commentCount;}

    public String getSubTitle() {return subtitle;}

    public void setSubTitle(String subtitle) {this.subtitle = subtitle;}
}
