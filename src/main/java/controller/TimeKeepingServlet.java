package controller;

import utils.EmployeeUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "TimeKeepingServlet", value = "/TimeKeepingServlet/*")
public class TimeKeepingServlet extends HttpServlet {
    private EmployeeUtils employeeUtils;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeUtils = new EmployeeUtils();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getPathInfo().substring(1);
        try {
            if (employeeUtils.TimeKeeping(id)) {
                System.out.println("TimeKeeping Success !");
            }
        } catch (SQLException e) {
            System.out.println("TimeKeeping Error !");
            e.printStackTrace();
        }
        response.setStatus(204);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
