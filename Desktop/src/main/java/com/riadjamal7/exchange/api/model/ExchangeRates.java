package com.riadjamal7.exchange.api.model;

import com.google.gson.annotations.SerializedName;
public class ExchangeRates {
    @SerializedName("usd_to_lbp")
    public Float usdToLbp;
    @SerializedName("lbp_to_usd")
    public Float lbpToUsd;
    @SerializedName("sev_day_usd_to_lbp")
    public Float s_day_usd_to_lbp;
    @SerializedName("sev_day_lbp_to_usd")
    public Float s_day_lbp_to_usd;
    @SerializedName("all_time_usd_volume")
    public Float all_time_usd_volume;
    @SerializedName("all_time_lbp_volume")
    public Float all_time_lbp_volume;
    @SerializedName("last_day_usd_volume")
    public Float last_day_usd_volume;
    @SerializedName("last_day_lbp_volume")
    public Float last_day_lbp_volume;
    @SerializedName("lbp_to_usd_trend")
    public Boolean lbp_to_usd_trend;
    @SerializedName("usd_to_lbp_trend")
    public Boolean usd_to_lbp_trend;
    @SerializedName("lbp_to_usd_stdev")
    public Float lbp_to_usd_stdev;
    @SerializedName("usd_to_lbp_stdev")
    public Float usd_to_lbpstdev;
}
