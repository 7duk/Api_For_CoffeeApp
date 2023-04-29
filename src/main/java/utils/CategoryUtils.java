package utils;

import model.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CategoryUtils implements  Utils<category,String>{
    private Connection con;
    private PreparedStatement stmt;

    @Override
    public boolean create(category obj) throws SQLException {
        return  false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(category obj) throws SQLException {
        return false;
    }

    @Override
    public List<category> get(String obj) throws SQLException {
        List<category>listCategory = new ArrayList<>();
        con = connection.getConnection();
        if(con!= null){
            if(obj.equals("All")){
                stmt = con.prepareStatement("SELECT  * from `coffeeorder`.category");
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String idCategory = rs.getString(1);
                String nameCategory = rs.getString(2);
                category categoryItem = new category(idCategory,nameCategory);
                listCategory.add(categoryItem);
            }
        }
        else {
            System.out.println("not connection !");
        }
        return listCategory;
    }
}
