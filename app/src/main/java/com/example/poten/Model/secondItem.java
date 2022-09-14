package com.example.poten.Model;

public class secondItem {
    private String id;
    private String fileName;
    private int likeCount;
    private int commentCount;
    private String subtitle;

    public secondItem(String id, String fileName, int likeCount, int commentCount, String subtitle){
        this.id = id;
        this.fileName = fileName;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.subtitle = subtitle;
    }

    public String getId() {return id;}

    public void setTitle(String id) {this.id = id;}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLikeCount() {return likeCount;}

    public void setLikeCount(int likeCount) {this.likeCount = likeCount;}

    public int getCommentCount() {return commentCount;}

    public void setCommentCount(int commentCount) {this.commentCount = commentCount;}

    public String getSubTitle() {return subtitle;}

    public void setSubTitle(String subtitle) {this.subtitle = subtitle;}
}
