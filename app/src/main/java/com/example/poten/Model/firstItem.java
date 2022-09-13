package com.example.poten.Model;

public class firstItem {
    private String id;
    private String tag;
    private String subtitle;
    private String dday;
    private int imgProfile;
    private int imgNotice;

    public firstItem(String id, String tag, String subtitle, String dday, int imgProfile, int imgNotice){
        this.id = id;
        this.tag = tag;
        this.subtitle = subtitle;
        this.dday = dday;
        this.imgProfile = imgProfile;
        this.imgNotice = imgNotice;
    }

    public String getId() { return id;}
    public void setId(String id) {this.id = id;}
    public String getTag() { return tag;}
    public void setTag(String tag) {this.tag = tag;}
    public String getSubtitle() { return subtitle;}
    public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
    public String getDday() { return dday;}
    public void setDday(String dday) {this.dday = dday;}

    public int getImgProfile() { return imgProfile;}
    public void setImgProfile(int imgProfile) {this.imgProfile = imgProfile;}

    public int getImgNotice() {return imgNotice;}
    public void setImgNotice(int imgNotice) {this.imgNotice = imgNotice;}
}
