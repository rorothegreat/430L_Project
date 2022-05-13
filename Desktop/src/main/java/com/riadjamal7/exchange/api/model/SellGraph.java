package com.riadjamal7.exchange.api.model;

import com.google.gson.annotations.SerializedName;
public class SellGraph {
    @SerializedName("added_date")
    public String addedDate;
    @SerializedName("rate")
    public Float rate;

    public String getAddedDate() {
        return addedDate;
    }

    public Float getRate() {
        return rate;
    }
}
