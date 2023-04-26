package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.ProductByOrder;
import utils.ProductUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductByOrderServlet/", value = "/ProductByOrderServlet/*")

//                @HttpMethodConstraint(value = "GET"),
//                @HttpMethodConstraint(value = "POST"),



public class ProductByOrderServlet extends HttpServlet {
    ProductUtils productUtils;
    @Override
    public void init() throws ServletException {
        super.init();
        productUtils = new ProductUtils();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<ProductByOrder> list = new ArrayList<ProductByOrder>();

        try {
            list = productUtils.get("All");
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

    @Override
    @HttpMethodConstraint(value = "DELETE")
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);

        String productId = req.getPathInfo().substring(1);

        PrintWriter s = resp.getWriter();
        s.println(productId);
        try {
            productUtils.deleteProduct(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Trả về mã trạng thái 204 nếu xoá thành công
        resp.setStatus(204);
    }

}
