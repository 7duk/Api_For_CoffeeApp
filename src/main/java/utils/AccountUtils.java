package utils;

import model.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountUtils implements Utils<account, String> {
    private Connection con;
    private PreparedStatement stmt;

    public account CheckLogin(String username, String pass) throws SQLException {
        con = connection.getConnection();
        if (con != null) {
            stmt = con.prepareStatement("Select * From `coffeeorder`.account where  account.idEmployee = ? and account.passWord = ?");
            stmt.setString(1, username);
            stmt.setString(2, pass);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userName = rs.getString(1);
                String passWord = rs.getString(2);
                int role = rs.getInt(3);
                return new account(userName, passWord, role);
            }
        }
        return null;
    }

    @Override
    public boolean create(account obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(account accountItem) throws SQLException {
        con = connection.getConnection();
        if (con != null) {
            stmt = con.prepareStatement("UPDATE `coffeeorder`.account SET passWord = ?  WHERE idEmployee = ?");
            stmt.setString(1, accountItem.getPassWord());
            stmt.setString(2, accountItem.getUserName());
            int row = stmt.executeUpdate();
            System.out.println(row);
            return true;
        } else {
            System.out.println("not connection !");
        }
        return false;
    }

    @Override
    public List<account> get(String obj) throws SQLException {
        return null;
    }
}
