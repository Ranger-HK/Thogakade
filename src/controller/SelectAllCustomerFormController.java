package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import view.tm.CustomerTM;

import java.sql.*;
import java.util.ArrayList;

/**
 * @Created By Ravindu Prathibha
 * @created 1/13/2024 - 2:55 PM
 * @project Thogakade
 */
public class SelectAllCustomerFormController {
    public TableView <CustomerTM> tblCustomer;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn coCusSalary;

    public void initialize(){
        try {
            colCusId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            coCusSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

            loadAllCustomers();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadAllCustomers() throws ClassNotFoundException, SQLException {   
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();

        while (resultSet.next()){
            customers.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));

        }
        setCustomersToTable(customers);
    }

    private void setCustomersToTable(ArrayList<Customer> customers) {
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
        customers.forEach(e->{
            observableList.add(new CustomerTM(e.getCustomerId(),e.getName(),e.getAddress(),e.getSalary()));
        });
        tblCustomer.setItems(observableList);
    }

}
