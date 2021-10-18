package com.mycompany.db.web;

import com.mycompany.db.DAO.CustomersDao;
import com.mycompany.db.model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomersDao customersDao = (CustomersDao)this.getServletContext().getAttribute("customersDao");
        if (request.getParameter("Customer_ID") == null) {
            List<Customer> customers = customersDao.list();
            request.setAttribute("customers", customers);
        }
        boolean edit = request.getRequestURI().endsWith("edit");
        if (edit) {
            long id = Long.parseLong(request.getParameter("Customer_ID"));
            Customer cm = customersDao.get(id);
            request.setAttribute("customers", cm);
        }
        String route = edit ? "/customersEdit.jsp" : "/customer.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        CustomersDao customersDao = (CustomersDao)this.getServletContext().getAttribute("customersDao");
        Customer customers;
        if (request.getParameter("Customer_ID") != null) {
            long id = Long.parseLong(request.getParameter("Customer_ID"));
            customers = customersDao.get(id);
        } else {
            customers = new Customer();
        }
        if (customers == null) {
            response.sendError(404);
            return;
        }
        String login = request.getParameter("login");
        if (login != null) {
            customers.setLogin(login);
        }
        String server_customer = request.getParameter("server_customer");
        if (server_customer != null) {
            customers.setServer_customer(server_customer);
        }
        String country = request.getParameter("country");
        if (country != null) {
            customers.setCountry(country);
        }
        String balanceBefore = request.getParameter("balanceBefore");
        if (balanceBefore != null) {
            customers.setBalanceBefore(Integer.parseInt(balanceBefore));
        }
        try {
            customersDao.save(customers);
        } catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/customer");
    }
}

