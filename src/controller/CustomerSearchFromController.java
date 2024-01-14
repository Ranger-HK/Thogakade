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
 * @created 1/11/2024 - 3:50 PM
 * @project Thogakade
 */
public class CustomerSearchFromController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnSearchCustomer;

    public void SearchCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId=txtCustomerId.getText();

            Connection connection = DBConnection.getInstance().getConnection();
            String query ="SELECT * FROM Customer WHERE customerId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)

                );
               setData(customer);
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result set ").show();
            }
    }

    void setData(Customer customer){
        txtCustomerId.setText(customer.getCustomerId());
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());
        txtCustomerSalary.setText(String.valueOf(customer.getSalary()));
    }

    public void moveNameOnAction(ActionEvent actionEvent) {

    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {

    }

    public void moveAddresOnAction(ActionEvent actionEvent) {

    }
}
