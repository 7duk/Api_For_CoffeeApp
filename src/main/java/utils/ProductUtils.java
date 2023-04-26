package utils;

import model.ProductByOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductUtils implements Utils<ProductByOrder, String> {
    public void create() {

    }

    public boolean deleteProduct(String id) throws SQLException {
        Connection con = connection.getConnection();
        System.out.println("222");
        if(con!= null){
            System.out.println("111");
            PreparedStatement stmt = con
                    .prepareStatement("Delete `coffeeorder`.product FROM `coffeeorder`.product where idProduct = ?");
            stmt.setString(1,id);
            System.out.println(stmt.toString());
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        }
        return false;
    }



    public void update(int id) {

    }
    @Override
    public List<ProductByOrder> get(String key) throws SQLException {        List<ProductByOrder> list = new ArrayList<ProductByOrder>();
        PreparedStatement stmt = null;
        Connection con = connection.getConnection();
        if (con != null) {
            if(key.equals("All")){
                stmt =  con.prepareStatement("SELECT * FROM `coffeeorder`.product");
            }
            else {

            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                double salePrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                String image = rs.getString(5);
                String idCate = rs.getString(6);
                ProductByOrder item = new ProductByOrder(id, name, salePrice, quantity, image,idCate);
                list.add(item);
            }
        }
        else {
            System.out.println("not connection !");
            return  null;
        }
        return list;
    }
}
