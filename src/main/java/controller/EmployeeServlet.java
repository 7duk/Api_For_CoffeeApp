package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.account;
import model.employee;
import utils.EmployeeUtils;
import utils.ProductUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet("/EmployeeServlet/*")
public class EmployeeServlet extends HttpServlet {
    EmployeeUtils employeeUtils;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeUtils = new EmployeeUtils();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String key = request.getPathInfo().substring(1);
        List<employee> list = new ArrayList<employee>();
        try {
            list = employeeUtils.get(key);
            System.out.println("Key:" + key);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.writeValue(response.getOutputStream(), list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = req.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        String body = stringBuilder.toString();
        String[] splitBody  = body.split("\n");
        List<String>  listPara = new ArrayList<>();
        for(int i = 3 ; i <splitBody.length;i+=4){
            listPara.add(splitBody[i]);
        }
        listPara.forEach(System.out::println);
        employee employeeItem = new employee(listPara.get(0),listPara.get(1),listPara.get(2),listPara.get(3),listPara.get(4),listPara.get(5),listPara.get(6),Integer.parseInt(listPara.get(7)),Integer.parseInt(listPara.get(8)));
        try {
            if(employeeUtils.update(employeeItem)){
                System.out.println("Update success employee !");
            }
        } catch (SQLException e) {
            System.out.println("Update error employee !");
            e.printStackTrace();
        }
    }
    @HttpMethodConstraint("DELETE")
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getPathInfo().substring(1);
        try {
            if(employeeUtils.delete(key)){
                System.out.println("Delete success employee !");
            }
            else{
                System.out.println("Delete error employee !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(204);
    }
    @HttpMethodConstraint("Put")
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = req.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        String body = stringBuilder.toString();
        String[] splitBody  = body.split("\n");
        List<String>  listPara = new ArrayList<>();
        for(int i = 3 ; i <splitBody.length;i+=4){
            listPara.add(splitBody[i]);
        }
        listPara.forEach(System.out::println);
        employee employeeItem = new employee(listPara.get(0),listPara.get(1),listPara.get(2),listPara.get(3),listPara.get(4),listPara.get(5),listPara.get(6),Integer.parseInt(listPara.get(7)),Integer.parseInt(listPara.get(8)));
        account accountItem = new account(listPara.get(0),listPara.get(9),Integer.parseInt(listPara.get(10)));
        try {
            if(employeeUtils.create(employeeItem,accountItem)){
                System.out.println("Add success employee !");
            }
        } catch (SQLException e) {
            System.out.println("Add error employee !");
            e.printStackTrace();
        }
    }

}
