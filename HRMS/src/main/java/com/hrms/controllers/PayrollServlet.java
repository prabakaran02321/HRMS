package com.hrms.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.hrms.services.PayrollService;
import com.hrms.utils.RoleValidator;

@WebServlet(name = "PayrollServlet", urlPatterns = "/api/payroll")
public class PayrollServlet extends HttpServlet {

    private PayrollService payrollService;

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        this.payrollService = new PayrollService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String roleName = RoleValidator.getRole(request, response);

        if (roleName != null && (roleName.equals("admin") || roleName.equals("human resources") || roleName.equals("manager"))) {
            payrollService.getAllRecords(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roleName = RoleValidator.getRole(request, response);

        if (roleName != null && (roleName.equals("admin"))) {
            payrollService.createPayroll(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roleName = RoleValidator.getRole(request, response);

        if (roleName != null && (roleName.equals("admin"))) {
            payrollService.deletePayroll(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource");
        }
    }
}
