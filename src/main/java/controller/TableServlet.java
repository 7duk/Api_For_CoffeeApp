package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.employee;
import model.table;
import utils.EmployeeUtils;
import utils.TableUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TableServlet", value = "/TableServlet/*")
public class TableServlet extends HttpServlet {
    private TableUtils tableUtils ;
    public void init() throws ServletException {
        super.init();
        tableUtils = new TableUtils();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String key = request.getPathInfo().substring(1);
        List<table> list = new ArrayList<table>();
        try {
            list = tableUtils.get(key);
            System.out.println("Key:" + key);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.writeValue(response.getOutputStream(), list);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
