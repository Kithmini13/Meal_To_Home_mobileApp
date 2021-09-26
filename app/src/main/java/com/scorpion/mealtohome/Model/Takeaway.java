package com.scorpion.mealtohome.Model;

public class Takeaway {
    private String id;
    String cusID;
    String cusTWName;
    String cusTime;

    public Takeaway(String id, String cusID, String cusTWName, String cusTime) {
        this.id = id;
        this.cusID = cusID;
        this.cusTWName = cusTWName;
        this.cusTime = cusTime;
    }

    public Takeaway() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusTWName() {
        return cusTWName;
    }

    public void setCusTWName(String cusTWName) {
        this.cusTWName = cusTWName;
    }

    public String getCusTime() {
        return cusTime;
    }

    public void setCusTime(String cusTime) {
        this.cusTime = cusTime;
    }
}
