package app;

import app.model.Beer;
import app.model.Client;
import app.model.Container;
import app.model.order.Item;
import app.model.order.Order;
import app.model.order.OrderState;
import app.model.user.User;
import app.model.user.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Main {

//    static Client aClient;
//    static User anUser;
//    static Order anOrder;
//    static Beer aBeer;
//    static Container aContainer;
//    static Item anItem;
//    static Session sessionObj;
//    static SessionFactory sessionFactoryObj;
//
//    private static SessionFactory buildSessionFactory() {
//        // Creating Configuration Instance & Passing Hibernate Configuration File
//        Configuration configObj = new Configuration();
//        configObj.configure("hibernate.cfg.xml");
//
//        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
//        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
//
//        // Creating Hibernate SessionFactory Instance
//        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
//        return sessionFactoryObj;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
