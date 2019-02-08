package com.swifton.swifton.Models;

public class SwiftUsers {
    private String sName, sPhone, sState, sCountry, sBio, sAddress;
    private int _id;

    public SwiftUsers(String sName, String sPhone, String sState, String sCountry, String sBio, String sAddress, int _id) {
        this.sName = sName;
        this.sPhone = sPhone;
        this.sState = sState;
        this.sCountry = sCountry;
        this.sBio = sBio;
        this.sAddress = sAddress;
        this._id = _id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsState() {
        return sState;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public String getsCountry() {
        return sCountry;
    }

    public void setsCountry(String sCountry) {
        this.sCountry = sCountry;
    }

    public String getsBio() {
        return sBio;
    }

    public void setsBio(String sBio) {
        this.sBio = sBio;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
