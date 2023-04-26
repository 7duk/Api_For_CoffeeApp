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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String key = req.getPathInfo().substring(1);
        System.out.println(key);
        String keys[] = key.split("&");
        account acc = null;
        try {
            acc = employeeUtils.CheckLogin(keys[0], keys[1]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.writeValue(resp.getOutputStream(), acc);
    }
}
