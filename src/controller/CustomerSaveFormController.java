package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void SaveCusOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        double salary = Double.parseDouble(txtCustomerSalary.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO Customer VALUES (" + "'" + id + "'" + "," + "'" + name + "'" + "," + "'" + address + "'" + "," + "'" + salary + "'" + ")";
            int i = statement.executeUpdate(sql);
            if (i > 0) {
                System.out.println("Saved");
                txtClear();
            } else {
                System.out.println("Try Again..");
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

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
