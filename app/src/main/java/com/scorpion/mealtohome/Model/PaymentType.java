package com.scorpion.mealtohome.Model;

public class PaymentType {
    private String id;
    String cardOwnerName;
    String cardType;
    private String payAmount;
    String payDate;

    public PaymentType() {
    }

    public PaymentType(String id, String cardOwnerName, String cardType, String payAmount, String payDate) {
        this.id = id;
        this.cardOwnerName = cardOwnerName;
        this.cardType = cardType;
        this.payAmount = payAmount;
        this.payDate = payDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}
