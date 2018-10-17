package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
