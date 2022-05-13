package com.riadjamal7.exchange.api;

import com.riadjamal7.exchange.api.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Exchange {
    @POST("/user")
    Call<User> addUser(@Body User user);
    @POST("/authentication")
    Call<Token> authenticate(@Body User user);
    @GET("/exchangeRate")
    Call<ExchangeRates> getExchangeRates();
    @POST("/transaction")
    Call<Object> addTransaction(@Body Transaction transaction, @Header("Authorization") String authorization);
    @GET("/transaction")
    Call<List<Transaction>> getTransactions(@Header("Authorization") String authorization);
    @GET("/listing")
    Call<List<Listing>> getListings();
    @POST("/listing")
    Call<Listing> addListing(@Body Listing listing);
    @POST("/userListing")
    Call<Object> adduserListing(@Body Listing listing, @Header("Authorization") String authorization);
    @GET("/userListing")
    Call<List<Listing>> getuserListings(@Header("Authorization") String authorization);
    @HTTP(method = "DELETE", path = "/userListing", hasBody = true)
    Call<Listing> rmListing(@Body Listing listing,@Header("Authorization") String authorization);
    @GET("/buyGraph")
    Call<List<BuyGraph>> getBuyGraph();
    @GET("/sellGraph")
    Call<List<SellGraph>> getSellGraph();


}