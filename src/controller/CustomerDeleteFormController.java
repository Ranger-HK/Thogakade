package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

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

    public void DeleteCusOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            String query = "DELETE FROM Customer WHERE customerId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,txtCustomerId.getText());

            if ( preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Delete Successful").show();
                txtClear();

            }else {

                new Alert(Alert.AlertType.WARNING,"Try Again...").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void customerSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/Thogakade",
                    "root",
                    "19990202Ravi@:&pra"
            );
            String query ="SELECT * FROM Customer WHERE customerId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1,txtCustomerId.getText());
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

    public void txtClear(){
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerSalary.clear();
    }
}

