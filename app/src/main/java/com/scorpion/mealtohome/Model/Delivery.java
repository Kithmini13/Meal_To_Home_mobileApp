package com.scorpion.mealtohome.Model;

public class Delivery {

    String id;
    String add1;
    String add2;
    String cusName;
    String cusNo;
    String orderID;

    public Delivery() {

    }

    public Delivery(String id, String add1, String add2, String cusName, String cusNo, String orderID) {
        this.id = id;
        this.add1 = add1;
        this.add2 = add2;
        this.cusName = cusName;
        this.cusNo = cusNo;
        this.orderID = orderID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
