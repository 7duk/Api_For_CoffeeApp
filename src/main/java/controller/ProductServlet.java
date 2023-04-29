package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.account;
import model.employee;
import model.product;
import utils.ProductUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet/", value = "/ProductServlet/*")
public class ProductServlet extends HttpServlet {
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
        List<product> list = new ArrayList<product>();
        String key = request.getPathInfo().substring(1);
        try {
            list = productUtils.get(key);
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
        String productId = req.getPathInfo().substring(1);
        try {
            productUtils.delete(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(204);
    }

    @Override
    @HttpMethodConstraint("PUT")
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
        product productItem = new product(listPara.get(0),listPara.get(1),Double.parseDouble(listPara.get(2)),Integer.parseInt(listPara.get(3)),listPara.get(4),listPara.get(5));
        try{
            if(productUtils.create(productItem)){
                System.out.println("Add success product !");
            }
        } catch (SQLException e) {
            System.out.println("Add error product !");
            e.printStackTrace();
        }
    }
}
