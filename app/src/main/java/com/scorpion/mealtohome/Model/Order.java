package com.scorpion.mealtohome.Model;

public class Order {
    private String id;
    String customerName;
    String customerId;
    String branch;
    String time;
    String address1;
    String address2;
    String contactNo;

    public Order() {
    }

    public Order(String customerName, String customerId, String address1, String address2, String contactNo) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.address1 = address1;
        this.address2 = address2;
        this.contactNo = contactNo;
    }

    public Order(String id, String customerName, String customerId, String branch, String time, String address1, String address2, String contactNo) {
        this.id = id;
        this.customerName = customerName;
        this.customerId = customerId;
        this.branch = branch;
        this.time = time;
        this.address1 = address1;
        this.address2 = address2;
        this.contactNo = contactNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
