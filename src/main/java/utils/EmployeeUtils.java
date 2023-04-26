package utils;

import model.account;
import model.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class EmployeeUtils implements Utils<employee,String>  {
    private  Connection con = connection.getConnection();
    @Override
    public void create() {

    }

    @Override
    public boolean deleteProduct(String id) throws SQLException {
        return false;
    }

    @Override
    public void update(int id) {

    }
    public account CheckLogin(String username, String pass) throws SQLException {
        if(con!= null){
            PreparedStatement stmt = con.prepareStatement("Select * From `coffeeorder`.account where  account.userName = ? and account.passWord = ?" );
            stmt.setString(1,username);
            stmt.setString(2,pass);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String userName = rs.getString(1);
                String passWord = rs.getString(2);
                int role = rs.getInt(3);
                return  new account(userName,passWord,role);
            }
        }
        return null;
    }
    @Override
    public List<employee> get(String key) throws SQLException {
        List<employee> list = new ArrayList<employee>();
        PreparedStatement stmt = null;
        Connection con = connection.getConnection();
        if (con != null) {
            if(key.equals("All")){
                stmt= con.prepareStatement("SELECT * FROM `coffeeorder`.employee");
            }
             else {
                 stmt =con.prepareStatement("SELECT  * FROM `coffeeorder`.employee WHERE employee.Username = ?");
                 stmt.setString(1, key);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String fullName = rs.getString(2);
                String phoneNumber = rs.getString(3);
                String email = rs.getString(4);
                String dateOfBirth = rs.getString(5);
                String address = rs.getString(6);
                String imageEmployee = rs.getString(7);
                int salary = rs.getInt(8);
                int workHour = rs.getInt(9);
                employee item = new employee(id,fullName,phoneNumber,email,dateOfBirth,address,imageEmployee,salary,workHour);
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
