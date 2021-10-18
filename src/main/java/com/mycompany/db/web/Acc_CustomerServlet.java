package com.mycompany.db.web;

import com.mycompany.db.DAO.Acc_CustomersDao;
import com.mycompany.db.model.Acc_Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Acc_CustomerServlet", urlPatterns = {"/acc_Customer"})
public class Acc_CustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Acc_CustomersDao acc_CuctomerDao = (Acc_CustomersDao)this.getServletContext().getAttribute("acc_CuctomerDao");
        if (request.getParameter("Acc_CustomerID") == null) {
            List<Acc_Customer> acc_customers = acc_CuctomerDao.list();
            request.setAttribute("acc_customers", acc_customers);
        }
        boolean edit = request.getRequestURI().endsWith("edit");
        if (edit) {
            long id = Long.parseLong(request.getParameter("Acc_CustomerID"));
            Acc_Customer cm = acc_CuctomerDao.get(id);
            request.setAttribute("acc_customers", cm);
        }
        String route = edit ? "/acc_customersEdit.jsp" : "/acc_customer.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Acc_CustomersDao acc_CuctomerDao = (Acc_CustomersDao)this.getServletContext().getAttribute("acc_CuctomerDao");
        Acc_Customer acc_customers;
        if (request.getParameter("Acc_CustomerID") != null) {
            long id = Long.parseLong(request.getParameter("Acc_CustomerID"));
            acc_customers = acc_CuctomerDao.get(id);
        } else {
            acc_customers = new Acc_Customer();
        }
        if (acc_customers == null) {
            response.sendError(404);
            return;
        }
        String count_Characters = request.getParameter("count_Characters");
        if (count_Characters != null) {
            acc_customers.setCount_Characters(Integer.parseInt(count_Characters));
        }
        String max_Level_Character = request.getParameter("max_Level_Character");
        if (max_Level_Character != null) {
            acc_customers.setMax_Level_Character(Integer.parseInt(max_Level_Character));
        }
        String name_Max_Level_Character = request.getParameter("name_Max_Level_Character");
        if (name_Max_Level_Character != null) {
            acc_customers.setName_Max_Level_Character(name_Max_Level_Character);
        }
        String forum_Posts = request.getParameter("forum_Posts");
        if (forum_Posts != null) {
            acc_customers.setForum_Posts(Integer.parseInt(forum_Posts));
        }
        String achievements_Completed = request.getParameter("achievements_Completed");
        if (achievements_Completed != null) {
            acc_customers.setAchievements_Completed(Integer.parseInt(achievements_Completed));
        }
        try {
            acc_CuctomerDao.save(acc_customers);
        } catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/acc_Customer");
    }
}

