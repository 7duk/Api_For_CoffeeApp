package utils;

import model.employee;
import model.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableUtils implements Utils<table, String> {
    private Connection con;
    private PreparedStatement stmt;

    @Override
    public boolean create(table obj) throws SQLException {
        con = connection.getConnection();
        if (con != null) {
            stmt = con.prepareStatement(
                    "INSERT  INTO  `coffeeorder`.tableproperties VALUES(?,?,?)"
            );
            stmt.setString(1, obj.getIdTable());
            stmt.setString(2, obj.getNameTable());
            stmt.setInt(3, obj.getStatus());
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        } else {
            System.out.println("not connection !");
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        con = connection.getConnection();
        if (con != null) {
            stmt = con
                    .prepareStatement("Delete `coffeeorder`.tableproperties FROM `coffeeorder`.tableproperties where idTable = ?");
            stmt.setString(1, id);
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        } else {
            System.out.println("not connection !");
        }
        return false;
    }

    @Override
    public boolean update(table obj) throws SQLException {
        con = connection.getConnection();
        if (con != null) {
            stmt = con.prepareStatement("UPDATE `coffeeorder`.tableproperties SET status = ? WHERE idTable = ?");
            stmt.setInt(1, obj.getStatus());
            stmt.setString(2, obj.getIdTable());
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        } else {
            System.out.println("not connection !");
        }
        return false;
    }

    @Override
    public List<table> get(String key) throws SQLException {
        List<table> list = new ArrayList<table>();
        con = connection.getConnection();
        if (con != null) {
            if (key.equals("All")) {
                stmt = con.prepareStatement("SELECT * FROM `coffeeorder`.tableproperties");
            } else {
                stmt = con.prepareStatement("SELECT  * FROM `coffeeorder`.tableproperties WHERE idTable = ?");
                stmt.setString(1, key);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String Name = rs.getString(2);
                int status = rs.getInt(3);
                table item = new table(id, Name,status);
                list.add(item);
            }
        } else {
            System.out.println("not connection !");
            return null;
        }
        return list;
    }
}
