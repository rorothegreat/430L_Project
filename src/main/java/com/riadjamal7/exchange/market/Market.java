package com.riadjamal7.exchange.market;
import com.riadjamal7.exchange.Authentication;
import com.riadjamal7.exchange.api.ExchangeService;
import com.riadjamal7.exchange.api.model.Listing;
import com.riadjamal7.exchange.api.model.Listing;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class Market implements Initializable {
    public TableColumn<Listing, Long> ad_amount;
    public TableColumn<Listing, String> ad_currency;
    public TableColumn<Listing, Long> ask_amount;
    public TableColumn<Listing, Integer> t_id;
    public TableColumn<Listing, Integer> p_id;
    public TableView<Listing> tableView;
    public TableView<Listing> tableview2;
    public TextField c_ad_amount;
    public TextField c_ask_amount;
    public ToggleGroup cad_type;
    public TextField phone_no;
    public Button add_button;
    public TextField phone_no2;
    public TableColumn<Listing, Long> p_ad_amount;
    public TableColumn<Listing, Long> p_ask_amount;
    public TableColumn<Listing, String> p_ad_currency;
    public TableColumn<Listing, Integer> p_phone_no;
    public TableColumn p_delete;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ad_amount.setCellValueFactory(new PropertyValueFactory<Listing, Long>("ad_amount"));
        ad_currency.setCellValueFactory(new PropertyValueFactory<Listing, String>("ad_type"));
        ask_amount.setCellValueFactory(new PropertyValueFactory<Listing, Long>("ask_amount"));
        p_phone_no.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("user2_phone"));
        p_ad_amount.setCellValueFactory(new PropertyValueFactory<Listing, Long>("ad_amount"));
        p_ad_currency.setCellValueFactory(new PropertyValueFactory<Listing, String>("ad_type"));
        p_ask_amount.setCellValueFactory(new PropertyValueFactory<Listing, Long>("ask_amount"));
        p_phone_no.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("user2_phone"));
        t_id.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("id"));
        p_id.setCellValueFactory(new PropertyValueFactory<Listing, Integer>("id"));
        updateTables();
    }

    public void updateTables(){
        System.out.println("Updatecalls");
        ExchangeService.exchangeApi().getListings().enqueue(new Callback<List<Listing>>() {
            @Override
            public void onResponse(Call<List<Listing>> call,
                                   Response<List<Listing>> response) {
                tableView.getItems().clear();
                tableView.getItems().setAll(response.body());

            }
            @Override
            public void onFailure(Call<List<Listing>> call,
                                  Throwable throwable) {
            }
        });
        ExchangeService.exchangeApi().getuserListings("Bearer " + Authentication.getInstance().getToken()).enqueue(new Callback<List<Listing>>() {
            @Override
            public void onResponse(Call<List<Listing>> call,
                                   Response<List<Listing>> response) {
                tableview2.getItems().clear();
                tableview2.getItems().setAll(response.body());

            }
            @Override
            public void onFailure(Call<List<Listing>> call,
                                  Throwable throwable) {
            }
        });
    }
    public void addListing(ActionEvent actionEvent) {
        Listing listing = new Listing(
                null,
                Float.parseFloat(c_ad_amount.getText()),
                ((RadioButton) cad_type.getSelectedToggle()).getText().equals("USD"),
                Float.parseFloat(c_ask_amount.getText())
        );


        String userToken = Authentication.getInstance().getToken();
        String authHeader = userToken != null ? "Bearer " + userToken : null;
        ExchangeService.exchangeApi().adduserListing(listing, authHeader).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                updateTables();
            }
            @Override
            public void onFailure(Call<Object> call, Throwable throwable)
            {
            }
        });
    }
    public void DeleteListing(ActionEvent actionEvent){
        Listing tbrow = tableview2.getSelectionModel().getSelectedItem();
        System.out.println(tbrow.getAd_amount().toString());
        String userToken = Authentication.getInstance().getToken();
        String authHeader = userToken != null ? "Bearer " + userToken : null;
        ExchangeService.exchangeApi().rmListing(tbrow, authHeader).enqueue(new Callback<Listing>() {
            @Override
            public void onResponse(Call<Listing> call, Response<Listing> response) {
                updateTables();
            }
            @Override
            public void onFailure(Call<Listing> call, Throwable throwable)
            {
            }
        });
        updateTables();

    }
    public void AcceptListing(ActionEvent actionEvent){
        Listing tbrow = tableView.getSelectionModel().getSelectedItem();
        System.out.println(tbrow.getId().toString());
        tbrow.setUser2_phone(Integer.parseInt(phone_no2.getText()));
        ExchangeService.exchangeApi().addListing(tbrow).enqueue(new Callback<Listing>() {
            @Override
            public void onResponse(Call<Listing> call, Response<Listing> response) {
                updateTables();
            }
            @Override
            public void onFailure(Call<Listing> call, Throwable throwable)
            {
            }
        });

    }

}