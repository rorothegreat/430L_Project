module com.riadjamal7.exchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    requires java.prefs;
    opens com.riadjamal7.exchange to javafx.fxml;
    opens com.riadjamal7.exchange.api.model to  javafx.base, gson;
    exports com.riadjamal7.exchange;
    exports com.riadjamal7.exchange.rates;
    opens com.riadjamal7.exchange.rates to javafx.fxml;
    opens com.riadjamal7.exchange.login to javafx.fxml;
    opens com.riadjamal7.exchange.register to javafx.fxml;
    opens com.riadjamal7.exchange.transactions to javafx.fxml;
    opens com.riadjamal7.exchange.market to javafx.fxml;
}