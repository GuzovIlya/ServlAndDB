package com.mycompany.db.web;
import com.mycompany.db.DAO.*;
import org.postgresql.ds.PGPoolingDataSource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class DbInitListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public DbInitListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Initializing data source connection");

        PGPoolingDataSource poolingDataSource = new PGPoolingDataSource();
        poolingDataSource.setDataSourceName("Lab 1 data source");

        poolingDataSource.setServerName("localhost");
        poolingDataSource.setDatabaseName("laba1");
        poolingDataSource.setUser("postgres");
        poolingDataSource.setPassword("1234");
        poolingDataSource.setMaxConnections(8);
        poolingDataSource.setInitialConnections(1);

        CustomersDao custommersDao = new CustomersDao(poolingDataSource);
        Acc_CustomersDao acc_CuctomerDao = new Acc_CustomersDao(poolingDataSource);
        ProductsDao productsDao = new ProductsDao(poolingDataSource);


        sce.getServletContext().setAttribute("customersDao", custommersDao);
        sce.getServletContext().setAttribute("acc_CuctomerDao", acc_CuctomerDao);
        sce.getServletContext().setAttribute("productsDao", productsDao);

        

        sce.getServletContext().setAttribute("datasource", poolingDataSource);

        sce.getServletContext().log("Initialized all DAOs");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("Closing connections pool");

        PGPoolingDataSource poolingDataSource = (PGPoolingDataSource) sce.getServletContext().getAttribute("datasource");
        poolingDataSource.close();

    }
}
