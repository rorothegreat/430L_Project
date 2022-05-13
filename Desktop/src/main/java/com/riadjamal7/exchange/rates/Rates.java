package com.riadjamal7.exchange.rates;


import com.riadjamal7.exchange.Authentication;
import com.riadjamal7.exchange.api.ExchangeService;
import com.riadjamal7.exchange.api.model.BuyGraph;
import com.riadjamal7.exchange.api.model.ExchangeRates;
import com.riadjamal7.exchange.api.model.SellGraph;
import com.riadjamal7.exchange.api.model.Transaction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class Rates {
    public Label buyUsdRateLabel;
    public Label sellUsdRateLabel;
    public Label lbl_sdaysavg;
    public Label lbl_sdaybavg;
    public Label lbl_alltimeuv;
    public Label lbl_alltimelv;
    public Label lbl_lastdayuv;
    public Label lbl_lastdaylv;
    public Label lbl_srsd;
    public Label lbl_brsd;
    public Label lbl_tfhsrt;
    public Label lbl_tfhbrt;
    public TextField lbpTextField;
    public TextField usdTextField;
    public ToggleGroup transactionType;
    public LineChart buygraph;
    public LineChart sellgraph;
    float usd_amount;
    float lbp_amount;
    boolean usd_to_lbp;
    float usd_to_lbp_rate;
    float lbp_to_usd_rate;
    public void initialize() {
        fetchRates();
    }
    private void fetchRates() {
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new Callback<ExchangeRates>() {
         @Override
         public void onResponse(Call<ExchangeRates> call, Response<ExchangeRates> response) {
             ExchangeRates exchangeRates = response.body();
             Platform.runLater(() -> {

                 buyUsdRateLabel.setText(exchangeRates.lbpToUsd.toString());
                 sellUsdRateLabel.setText(exchangeRates.usdToLbp.toString());
                 lbl_sdaysavg.setText(exchangeRates.s_day_usd_to_lbp.toString());
                 lbl_sdaybavg.setText(exchangeRates.s_day_lbp_to_usd.toString());
                 lbl_alltimeuv.setText(exchangeRates.all_time_usd_volume.toString());
                 lbl_alltimelv.setText(exchangeRates.all_time_lbp_volume.toString());
                 lbl_lastdayuv.setText(exchangeRates.last_day_usd_volume.toString());
                 lbl_lastdaylv.setText(exchangeRates.last_day_lbp_volume.toString());
                 lbl_srsd.setText(exchangeRates.usd_to_lbpstdev.toString());
                 lbl_brsd.setText(exchangeRates.lbp_to_usd_stdev.toString());
                 lbl_tfhsrt.setText(exchangeRates.usd_to_lbp_trend? "Upwards":"Downwards");
                 lbl_tfhbrt.setText(exchangeRates.lbp_to_usd_trend? "Upwards":"Downwards");


             });
         }
         @Override
         public void onFailure(Call<ExchangeRates> call, Throwable throwable) {
         }
         });
        ExchangeService.exchangeApi().getBuyGraph().enqueue(new Callback<List<BuyGraph>>() {
            @Override
            public void onResponse(Call<List<BuyGraph>> call, Response<List<BuyGraph>> response) {
                List<BuyGraph> graphData = response.body();
                Platform.runLater(() -> {
                    XYChart.Series buyCurve = new XYChart.Series();
                    buyCurve.setName("Buy Rate");
                    for(int i = graphData.size()-1; i>=0; i--){
                        buyCurve.getData().add(new XYChart.Data(graphData.get(i).getAddedDate(), graphData.get(i).getRate()));
                    }
                    buygraph.getData().clear();
                    buygraph.getData().addAll(buyCurve);
                });
            }
            @Override
            public void onFailure(Call<List<BuyGraph>> call, Throwable throwable) {
            }
        });
        ExchangeService.exchangeApi().getSellGraph().enqueue(new Callback<List<SellGraph>>() {
            @Override
            public void onResponse(Call<List<SellGraph>> call, Response<List<SellGraph>> response) {
                List<SellGraph> graphData = response.body();
                Platform.runLater(() -> {
                    XYChart.Series sellCurve = new XYChart.Series();
                    sellCurve.setName("Sell Rate");
                    for(int i = graphData.size()-1; i>=0; i--){
                        sellCurve.getData().add(new XYChart.Data(graphData.get(i).getAddedDate(), graphData.get(i).getRate()));
                    }
                    sellgraph.getData().clear();
                    sellgraph.getData().addAll(sellCurve);
                });
            }
            @Override
            public void onFailure(Call<List<SellGraph>> call, Throwable throwable) {
            }
        });
        }
    public void addTransaction(ActionEvent actionEvent) {
        Transaction transaction = new Transaction(
                Float.parseFloat(usdTextField.getText()),
                Float.parseFloat(lbpTextField.getText()),
                ((RadioButton)
                        transactionType.getSelectedToggle()).getText().equals("Sell USD")
        );


        String userToken = Authentication.getInstance().getToken();
        String authHeader = userToken != null ? "Bearer " + userToken : null;
        ExchangeService.exchangeApi().addTransaction(transaction, authHeader).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
              fetchRates();
              Platform.runLater(() -> {
                  usdTextField.setText("USD Value");
                  lbpTextField.setText("LBP Value");
              });
            }
            @Override
            public void onFailure(Call<Object> call, Throwable throwable)
            {
            }
            });
    }
    public void Convert(ActionEvent actionEvent) {

        usd_to_lbp = ((RadioButton) transactionType.getSelectedToggle()).getText().equals("Sell USD");
        usd_to_lbp_rate = Float.parseFloat(buyUsdRateLabel.getText());
        lbp_to_usd_rate = Float.parseFloat(sellUsdRateLabel.getText());

        if (usd_to_lbp) {
            if (usdTextField.getText().isEmpty()) {
                lbp_amount = Float.parseFloat(lbpTextField.getText());
                usd_amount = lbp_amount/usd_to_lbp_rate;
            }
            else{
                usd_amount = Float.parseFloat(usdTextField.getText());
                lbp_amount = usd_amount *usd_to_lbp_rate;;
            }

        }
        else {

            if (usdTextField.getText().isEmpty()) {
                lbp_amount = Float.parseFloat(lbpTextField.getText());
                usd_amount = lbp_amount/lbp_to_usd_rate;
            }

            else{
                usd_amount = Float.parseFloat(usdTextField.getText());
                lbp_amount = usd_amount *lbp_to_usd_rate;
            }
        }
        usdTextField.setText(String.valueOf(usd_amount));
        lbpTextField.setText(String.valueOf(lbp_amount));

    }

}
