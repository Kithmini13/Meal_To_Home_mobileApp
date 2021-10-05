package com.scorpion.mealtohome.Model;

public class Menu {

    private String id;
    private String cName;
    private int price;
    private String img;

    public Menu(String id, String cName, int price, String img) {
        this.id = id;
        this.cName = cName;
        this.price = price;
        this.img = img;
    }

    public Menu() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
