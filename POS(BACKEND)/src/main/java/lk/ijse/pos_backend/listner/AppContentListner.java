package lk.ijse.pos_backend.listner;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;



//@WebListener
public class AppContentListner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pos");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setInitialSize(2);
        dataSource.setMaxTotal(40);

        ServletContext sc = sce.getServletContext();
        sc.setAttribute("DBCP", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();
            BasicDataSource dataSource = (BasicDataSource) sc.getAttribute("DBCP");
            dataSource.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
