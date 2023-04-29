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
    private  Connection con;
    private  PreparedStatement stmt;
    @Override
    public boolean create(employee employeeItem){
        return  false;
    }
    public boolean create(employee employeeItem, account accountItem) throws SQLException {
        con = connection.getConnection();
        if(con!= null){
            stmt = con.prepareStatement(
                    "INSERT  INTO  `coffeeorder`.employee VALUES(?,?,?,?,?,?,?,?,?)"
            );
            stmt.setString(1,employeeItem.getId());
            stmt.setString(2,employeeItem.getFullName());
            stmt.setString(3,employeeItem.getPhoneNumber());
            stmt.setString(4,employeeItem.getEmail());
            stmt.setString(5,employeeItem.getDateOfBirth());
            stmt.setString(6,employeeItem.getAddress());
            stmt.setString(7,employeeItem.getImageEmployee());
            stmt.setInt(8,employeeItem.getSalary());
            stmt.setInt(9,employeeItem.getWorkHour());
            int row = stmt.executeUpdate();
            System.out.println(row);
            stmt = con.prepareStatement(
                    "INSERT  INTO  `coffeeorder`.account VALUES(?,?,?)"
            );
            stmt.setString(1,accountItem.getUserName());
            stmt.setString(2,accountItem.getPassWord());
            stmt.setInt(3,accountItem.getRole());
            int row2 = stmt.executeUpdate();
            System.out.println(row2);
            return true;
        }
        else {
            System.out.println("not connection !");
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        con = connection.getConnection();
        if (con != null) {
            stmt = con
                    .prepareStatement("Delete `coffeeorder`.employee FROM `coffeeorder`.employee where idEmployee = ?");
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

    @Override
    public boolean update(employee employeeItem) throws SQLException {
        con = connection.getConnection();
        if(con != null){
            stmt  = con.prepareStatement("UPDATE `coffeeorder`.employee SET fullName = ? ,phoneNumber = ?,email = ?,dateOfBirth = ?,address= ?,imageEmployee = ?,salary = ?,workHour = ? WHERE idEmployee = ?");
            stmt .setString(1,employeeItem.getFullName());
            stmt .setString(2,employeeItem.getPhoneNumber());
            stmt .setString(3,employeeItem.getEmail());
            stmt .setString(4,employeeItem.getDateOfBirth());
            stmt .setString(5,employeeItem.getAddress());
            stmt .setString(6,employeeItem.getImageEmployee());
            stmt .setInt(7,employeeItem.getSalary());
            stmt .setInt(8,employeeItem.getWorkHour());
            stmt .setString(9,employeeItem.getId());
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
    public List<employee> get(String key) throws SQLException {
        List<employee> list = new ArrayList<employee>();
        PreparedStatement stmt = null;
        Connection con = connection.getConnection();
        if (con != null) {
            if(key.equals("All")){
                stmt= con.prepareStatement("SELECT * FROM `coffeeorder`.employee");
            }
             else {
                 stmt =con.prepareStatement("SELECT  * FROM `coffeeorder`.employee WHERE employee.idEmployee = ?");
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
    public boolean TimeKeeping(String id) throws SQLException {
        String workHour = "";
        con = connection.getConnection();
        if(con!= null){
            stmt = con.prepareStatement("SELECT workHour FROM `coffeeorder`.employee WHERE  idEmployee = ?");
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                workHour = rs.getString("workHour");
            }
            stmt = con.prepareStatement("UPDATE `coffeeorder`.employee SET workHour = ? WHERE idEmployee = ? ");
            stmt.setString(1,String.valueOf(Integer.parseInt(workHour)+7));
            stmt.setString(2,id);
            int row = stmt.executeUpdate();
            return  true;
        }
        else {
            System.out.println("not connection !");
        }
        return  false;
    }
}
