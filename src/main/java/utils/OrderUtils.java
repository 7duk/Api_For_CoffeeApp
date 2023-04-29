package utils;

import model.order;
import model.orderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class OrderUtils {
    private Connection con;
    private PreparedStatement stmt;
    private  String regix = "\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}";

    public List<order> getOrder(String key) throws SQLException {
        List<order> listOrder = new ArrayList<>();
        con = connection.getConnection();
        if (con != null) {
            if (key.equals("All")) {
                stmt = con.prepareStatement("SELECT * FROM  `coffeeorder`.orders");
            } else if (Pattern.matches(regix,key)) {
                stmt = con.prepareStatement("SELECT  * FROM  `coffeeorder`.orders WHERE dateOrder = ?");
                stmt.setString(1,key);
            } else{
                stmt = con.prepareStatement("SELECT * FROM `coffeeorder`.orders WHERE idOrder = ?");
                stmt.setString(1, key);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String idOrder = rs.getString(1);
                String dateOrder = rs.getString(2);
                String idEmployee = rs.getString(3);
                double priceTotal = rs.getDouble(4);
                String idTable = rs.getString(5);
                order item = new order(idOrder, dateOrder, idEmployee, priceTotal, idTable);
                listOrder.add(item);
            }
        }
        return listOrder;
    }

    public List<orderDetail> getOrderDetail(String key) throws SQLException {
        List<orderDetail> listDetail = new ArrayList<>();
        con = connection.getConnection();
        if (con != null) {
            stmt = con.prepareStatement("SELECT * FROM `coffeeorder`.orderdetail WHERE idOrder = ?");
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String idOrderDetail = rs.getString(1);
                String idProduct = rs.getString(2);
                String idOrder = rs.getString(3);
                int count = rs.getInt(4);
                String note = rs.getString(5);
                orderDetail item = new orderDetail(idOrderDetail,idProduct,idOrder,count,note);
                listDetail.add(item);
            }
        }
        return listDetail;
    }

}
