package utils;

import model.product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductUtils implements Utils<product, String> {
     private Connection con;
     private  PreparedStatement stmt;
    public boolean create(product productItem) throws SQLException {
        String id = productItem.getId();
        String name = productItem.getName();
        double salePrice = productItem.getSalePrice();
        int quantity = productItem.getQuantity();
        String image = productItem.getImage();
        String idCatelory = productItem.getIdCatelory();
        con = connection.getConnection();
        if(con!= null){
             stmt = con.prepareStatement(
                    "INSERT  INTO  `coffeeorder`.product VALUES(?,?,?,?,?,?)"
            );
            stmt.setString(1,id);
            stmt.setString(2,name);
            stmt.setDouble(3,salePrice);
            stmt.setInt(4,quantity);
            stmt.setString(5,image);
            stmt.setString(6,idCatelory);
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        }
        else {
            System.out.println("not connection !");
        }
        return false;
    }

    public boolean delete(String id) throws SQLException {
        con = connection.getConnection();
        if (con != null) {
             stmt = con
                    .prepareStatement("Delete `coffeeorder`.product FROM `coffeeorder`.product where idProduct = ?");
            stmt.setString(1, id);
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        }
        else {
            System.out.println("not connection !");
        }
        return false;
    }
    public boolean update(product productItem) throws SQLException {
        con = connection.getConnection();
        if(con != null){
            stmt  = con.prepareStatement("UPDATE `coffeeorder`.product SET nameProduct = ? ,imageProduct = ?,salePrice = ?,quantity = ?,idCategory= ? WHERE idProduct = ?");
            stmt .setString(1,productItem.getName());
            stmt .setString(2,productItem.getImage());
            stmt .setDouble(3,productItem.getSalePrice());
            stmt .setInt(4,productItem.getQuantity());
            stmt .setString(5,productItem.getIdCatelory());
            stmt .setString(6,productItem.getId());
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        }
        else {
            System.out.println("not connection !");
        }
        return  false;
    }

    @Override
    public List<product> get(String key) throws SQLException {
        List<product> list = new ArrayList<product>();
        con = connection.getConnection();
        if (con != null) {
            if (key.equals("All")) {
                stmt = con.prepareStatement("SELECT * FROM `coffeeorder`.product");
            } else {
                stmt = con.prepareStatement("SELECT  * FROM  `coffeeorder`.product WHERE idProduct = ?");
                stmt.setString(1,key);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                double salePrice = rs.getDouble(4);
                int quantity = rs.getInt(5);
                String image = rs.getString(3);
                String idCate = rs.getString(6);
                product item = new product(id, name, salePrice, quantity, image, idCate);
                list.add(item);
            }
        } else {
            System.out.println("not connection !");
            return null;
        }
        return list;
    }
}
