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
 * @created 1/13/2024 - 12:02 PM
 * @project Thogakade
 */
public class CustomerUpdateFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerSalary;
    public JFXButton btnUpdateCustomer;

    public void updateCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c1=new Customer(
                txtCustomerId.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                Double.parseDouble(txtCustomerSalary.getText())
        );

        if (updateCus(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successful").show();
            txtClear();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...");

        }

    }

    public void loadCustomerOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
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

                new Alert(Alert.AlertType.WARNING,"Try Again").show();

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void setData(Customer customer){
        txtCustomerId.setText(customer.getCustomerId());
        txtCustomerName.setText(customer.getName());
        txtCustomerAddress.setText(customer.getAddress());
        txtCustomerSalary.setText(String.valueOf(customer.getSalary()));
    }

    boolean updateCus(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "UPDATE Customer SET name=?,address=?,salary=? WHERE customerId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,customer.getName());
        preparedStatement.setObject(2,customer.getAddress());
        preparedStatement.setObject(3,customer.getSalary());
        preparedStatement.setObject(4,customer.getCustomerId());

        return preparedStatement.executeUpdate()>0;
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
