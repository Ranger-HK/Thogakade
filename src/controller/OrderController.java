package controller;

import db.DBConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Created By Ravindu Prathibha
 * @created 1/16/2024 - 4:08 PM
 * @project Thogakade
 */
public class OrderController {

    //Save Order
    public boolean placeOrder(Order order) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO `Order` VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, order.getOrderID());
            preparedStatement.setObject(2, order.getCustomerId());
            preparedStatement.setObject(3, order.getOrderDate());
            preparedStatement.setObject(4, order.getOrderTime());
            preparedStatement.setObject(5, order.getCost());

            if (preparedStatement.executeUpdate() > 0){

               return saveOrderDetail(order.getOrderID(),order.getItemDetails());

            }else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Save OrderDetail
    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails>itemDetails) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp : itemDetails
        ) {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO OrderDetail VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, temp.getItemCode());
            preparedStatement.setObject(2, orderId);
            preparedStatement.setObject(3, temp.getQtyForSell());
            preparedStatement.setObject(4, temp.getUnitPrice());

            if (preparedStatement.executeUpdate() > 0) {

            } else {
                return false;
            }

        }
        return true;
    }
}
