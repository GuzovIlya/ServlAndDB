package com.mycompany.db.web;

import com.mycompany.db.DAO.ProductsDao;
import com.mycompany.db.model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = {"/acc_Customer"})
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductsDao productsDao = (ProductsDao)this.getServletContext().getAttribute("productsDao");
        if (request.getParameter("Product_ID") == null) {
            List<Product> products = productsDao.list();
            request.setAttribute("products", products);
        }
        boolean edit = request.getRequestURI().endsWith("edit");
        if (edit) {
            long id = Long.parseLong(request.getParameter("Product_ID"));
            Product cm = productsDao.get(id);
            request.setAttribute("products", cm);
        }
        String route = edit ? "/productsEdit.jsp" : "/product.jsp";
        this.getServletContext().getRequestDispatcher(route).forward(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ProductsDao productsDao = (ProductsDao)this.getServletContext().getAttribute("productsDao");
        Product products;
        if (request.getParameter("Product_ID") != null) {
            long id = Long.parseLong(request.getParameter("Product_ID"));
            products = productsDao.get(id);
        } else {
            products = new Product();
        }
        if (products == null) {
            response.sendError(404);
            return;
        }
        String name_Product = request.getParameter("name_Product");
        if (name_Product != null) {
            products.setName_Product(name_Product);
        }
        String price = request.getParameter("price");
        if (price != null) {
            products.setPrice(Integer.parseInt(price));
        }
        String rating = request.getParameter("rating");
        if (rating != null) {
            products.setRating(Float.parseFloat(rating));
        }
        try {
            productsDao.save(products);
        } catch(Exception ex) {
            log("Failed to save", ex);
            response.sendError(500, "Failed to save " + ex.getMessage());
        }
        response.sendRedirect( request.getContextPath() + "/product");
    }
}

