package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * @Created By Ravindu Prathibha
 * @created 1/15/2024 - 2:14 PM
 * @project Thogakade
 */
public class PlaceOrderFormController {
    public AnchorPane txtContext;
    public TableView tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public ComboBox cmbCustomer;
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox cmbItem;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnAddToCart;
    public JFXButton btnClear;
    public JFXButton btnPlaceOrder;
    public Label lblTotal;

    public void initialize(){
        try {
            loadDateAndTime();
            loadCustomerIds();
            loadItemIds();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Item Ids Combo Box Load
    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getItemIds();
        cmbItem.getItems().addAll(itemIds);
    }

    //Customer Ids Combo Box Load
    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomer.getItems().addAll(customerIds);
    }

    private void loadDateAndTime() {
        //Set Date
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        //Set Time
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,event -> {
            LocalTime localTime = LocalTime.now();
            lblTime.setText(
                    localTime.getHour()+" : "+localTime.getMinute()+" : "+localTime.getSecond()
            );
        }),
            new KeyFrame(Duration.seconds(1))
       );
       timeline.setCycleCount(Animation.INDEFINITE);
       timeline.play();

    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

    }
}
