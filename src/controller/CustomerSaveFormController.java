package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.*;

/**
 * @Created By Ravindu Prathibha
 * @created 1/10/2024 - 6:13 PM
 * @project Thogakade
 */
public class CustomerSaveFormController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnSaveCustomer;

    public void SaveCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1=new Customer(
                txtCustomerId.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.parseDouble(txtCustomerSalary.getText())
        );

        if (saveCustomer(c1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
                txtClear();

        }else {
                new Alert(Alert.AlertType.WARNING,"Try Again").show();

        }

    }

    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println(connection);
        String query = "INSERT INTO Customer VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,customer.getCustomerId());
        preparedStatement.setObject(2,customer.getName());
        preparedStatement.setObject(3,customer.getAddress());
        preparedStatement.setObject(4,customer.getSalary());

        return preparedStatement.executeUpdate()>0;
    }

    public void moveNameOnAction(ActionEvent actionEvent) {
        txtCustomerName.requestFocus();

    }

    public void moveAddresOnAction(ActionEvent actionEvent) {
        txtCustomerAddress.requestFocus();
    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {
        txtCustomerSalary.requestFocus();

    }

    public void txtClear() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }
}
