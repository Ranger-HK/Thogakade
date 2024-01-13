package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;

/**
 * @Created By Ravindu Prathibha
 * @created 1/11/2024 - 3:49 PM
 * @project Thogakade
 */
public class CustomerDeleteFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnDeleteCustomer;

    public void DeleteCusOnAction(ActionEvent actionEvent) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            Statement statement = connection.createStatement();

            String query="DELETE FROM Customer WHERE customerId='"+txtCustomerId.getText()+"'";
            //int i = statement.executeUpdate(query);
           // boolean save = statement.executeUpdate(query)>0;

            if ( statement.executeUpdate(query)>0){

                new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful").show();
                txtClear();

            }else {

                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void customerSearchOnAction(ActionEvent actionEvent) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Customer WHERE customerId='"+txtCustomerId.getText()+"'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));

            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void txtClear(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }
}

