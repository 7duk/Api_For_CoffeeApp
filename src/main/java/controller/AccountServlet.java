package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.account;
import utils.AccountUtils;
import utils.ProductUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AccountServlet", value = "/AccountServlet/*")
public class AccountServlet extends HttpServlet {
    private  AccountUtils accountUtils;
    @Override
    public void init() throws ServletException {
        super.init();
        accountUtils = new AccountUtils();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String key = request.getPathInfo().substring(1);
        System.out.println(key);
        String keys[] = key.split("&");
        account acc = null;
        try {
            acc = accountUtils.CheckLogin(keys[0], keys[1]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.writeValue(response.getOutputStream(), acc);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getPathInfo().substring(1);
        String role = request.getParameter("role");
        String passWord = request.getParameter("password");
        System.out.println(key);
        System.out.println(passWord);
        System.out.println(role);
        try {
            boolean status = accountUtils.update(new account(key,passWord,Integer.parseInt(role)));
            if(status){
                System.out.println("Update success account !");
            }
            else {
                System.out.println("Update error account !");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
