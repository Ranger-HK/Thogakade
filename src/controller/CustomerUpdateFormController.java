package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;

/**
 * @Created By Ravindu Prathibha
 * @created 1/13/2024 - 12:02 PM
 * @project Thogakade
 */
public class CustomerUpdateFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnUpdateCustomer;

    public void updateCusOnAction(ActionEvent actionEvent) {
        String tempId = txtCustomerId.getText();
        String tempName = txtCustomerName.getText();
        String tempAddress = txtCustomerAddress.getText();
        Double tempSalary=Double.parseDouble(txtCustomerSalary.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            Statement statement = connection.createStatement();
            String query = "UPDATE Customer SET name='"+tempName+"',address='"+tempAddress+"',salary='"+tempSalary+"' WHERE customerId='"+tempId+"'";

            if (statement.executeUpdate(query)>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Successful").show();
                txtClear();

            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again...");

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomerOnAction(ActionEvent actionEvent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            Statement statement = connection.createStatement();
            String query ="SELECT * FROM Customer WHERE customerId='"+txtCustomerId.getText()+"'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
                txtCustomerName.requestFocus();

            }else {

                new Alert(Alert.AlertType.WARNING,"Try Again").show();

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void moveAddressOnAction(ActionEvent actionEvent) {
        txtCustomerAddress.requestFocus();
    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {
        txtCustomerSalary.requestFocus();
    }

    public void txtClear(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }


}
