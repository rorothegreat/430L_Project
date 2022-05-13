package com.riadjamal7.exchange.api.model;

import com.google.gson.annotations.SerializedName;
public class Listing {
    @SerializedName("id")
    public Integer id ;
    @SerializedName("user2_phone")
    public Integer user2_phone;
    @SerializedName("ad_amount")
    public Float ad_amount;
    @SerializedName("ad_type")
    public Boolean ad_type;
    @SerializedName("ask_amount")
    public Float ask_amount;
    @SerializedName("added_date")
    public String added_date;
    public String currency;

    public Listing(Integer user2_phone, Float ad_amount, Boolean ad_type, Float ask_amount) {
        this.user2_phone = user2_phone;
        this.ad_amount = ad_amount;
        this.ad_type = ad_type;
        this.ask_amount = ask_amount;
    }

    public Integer getId() {
        return id;
    }

    public Float getAd_amount() {
        return ad_amount;
    }

    public Boolean getAd_type() {
        return ad_type;
    }

    public Float getAsk_amount() {
        return ask_amount;
    }

    public String getAdded_date() {
        return added_date;
    }

    public Integer getUser2_phone() {
        return user2_phone;
    }
    public String getCurrency(){
       return ad_type? "USD": "LBP";
    }

    public void setUser2_phone(Integer user2_phone) {
        this.user2_phone = user2_phone;
    }
}
