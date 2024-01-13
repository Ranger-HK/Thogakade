package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

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

    public void SearchCusOnAction(ActionEvent actionEvent) {
        String customerId=txtCustomerId.getText();

     /*   try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM Customer WHERE customerId='"+customerId+"'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                String tempId = resultSet.getString(1);
                String tempName = resultSet.getString(2);
                String tempAddress = resultSet.getString(3);
                double tempSalary = resultSet.getDouble(4);

                txtCustomerId.setText(tempId);
                txtCustomerName.setText(tempName);
                txtCustomerAddress.setText(tempAddress);
                txtCustomerSalary.setText(String.valueOf(tempSalary));
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result set ").show();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            String query ="SELECT * FROM Customer WHERE customerId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                txtCustomerId.setText(resultSet.getString(1));
                txtCustomerName.setText(resultSet.getString(2));
                txtCustomerAddress.setText(resultSet.getString(3));
                txtCustomerSalary.setText(String.valueOf(resultSet.getDouble(4)));
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result set ").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public void moveNameOnAction(ActionEvent actionEvent) {

    }

    public void moveSalaryOnAction(ActionEvent actionEvent) {

    }

    public void moveAddresOnAction(ActionEvent actionEvent) {

    }
}
