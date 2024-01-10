package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @Created By Ravindu Prathibha
 * @created 1/10/2024 - 5:48 PM
 * @project Thogakade
 */
public class DashBoardFormController {
    public JFXButton btnCustomerSave;
    public JFXButton btnCustomerUpdate;
    public JFXButton btnCustomerDelete;
    public JFXButton btnCustomerSearch;
    public JFXButton btnSelectAllCustomer;
    public JFXButton btnPlaceOrder;

    public void customerSaveOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CustomerSaveForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setTitle("Customer Form");
        stage.setScene(scene);
        stage.show();
    }

    public void customerUpdateOnAction(ActionEvent actionEvent) {
    }

    public void customerDeleteOnAction(ActionEvent actionEvent) {
    }

    public void customerSearchOnAction(ActionEvent actionEvent) {
    }

    public void customerSelectAllOnAction(ActionEvent actionEvent) {
    }

    public void customerPlaceOrderlOnAction(ActionEvent actionEvent) {
    }
}

