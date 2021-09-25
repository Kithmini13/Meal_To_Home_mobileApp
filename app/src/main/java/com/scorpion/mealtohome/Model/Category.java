package com.scorpion.mealtohome.Model;

public class Category {
    private String CategoryName;
    private String price;
    private String img;

    public Category() {
    }

    public Category(String categoryName, String price, String img) {
        CategoryName = categoryName;
        this.price = price;
        this.img = img;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
