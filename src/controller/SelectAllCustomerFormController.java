package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.CustomerTM;

import java.sql.*;

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
       /* Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Thogakade",
                "root",
                "19990202Ravi@:&pra"
        );
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM Customer";
        ResultSet resultSet = statement.executeQuery(query);
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(new CustomerTM(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getString(3),
                   resultSet.getDouble(4)
            ));

        }
        tblCustomer.setItems(observableList);*/


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Thogakade",
                "root",
                "19990202Ravi@:&pra"
        );
        String query = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(new CustomerTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));

        }
        tblCustomer.setItems(observableList);
    }

}
