package lk.ijse.pos_backend.config;

import lk.ijse.pos_backend.entity.CustomerEntity;
import lk.ijse.pos_backend.entity.ItemEntity;
import lk.ijse.pos_backend.entity.OrderDetailsEntity;
import lk.ijse.pos_backend.entity.OrderEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public class SessionFactoryConfig {
    private static SessionFactoryConfig sessionFactoryConfig;
    private final SessionFactory sessionFactory;

    public SessionFactoryConfig() {
        Properties properties = new Properties();
        try {
            properties.load(SessionFactoryConfig.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        }catch (Exception e) {}

        sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(ItemEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(OrderDetailsEntity.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        return (sessionFactoryConfig==null)?sessionFactoryConfig = new SessionFactoryConfig() : sessionFactoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
