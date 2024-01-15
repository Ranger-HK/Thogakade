package controller;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created By Ravindu Prathibha
 * @created 1/15/2024 - 3:35 PM
 * @project Thogakade
 */
public class CustomerController {

    //Customer Ids Combo Box Load
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String>ids = new ArrayList<>();

        while (resultSet.next()){
            ids.add(
                    resultSet.getString(1)
            );
        }
        return ids;
    }



}
