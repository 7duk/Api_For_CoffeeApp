package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.category;
import model.order;
import model.orderDetail;
import utils.OrderUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet/*")
public class OrderServlet extends HttpServlet {
    private OrderUtils orderUtils;
    @Override
    public void init() throws ServletException {
        super.init();
        orderUtils = new OrderUtils();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String key = request.getPathInfo().substring(1);
        List<order> list = new ArrayList<order>();
        try {
            list = orderUtils.getOrder(key);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String key = request.getPathInfo().substring(1);
        List<orderDetail> list = new ArrayList<orderDetail>();
        try {
            list = orderUtils.getOrderDetail(key);
            System.out.println("Key:" + key);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.writeValue(response.getOutputStream(), list);
    }
}
