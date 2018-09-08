package app;

import app.model.Client;
import app.model.Order;
import app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class Main {

    static Client aClient;
    static User anUser;
    static Order anOrder;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static void main(String[] args) {

        System.out.println(".......Hibernate Maven Example.......\n");
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            for(int i = 1; i <= 5; i++) {
                aClient = new Client();
                aClient.setBusinessName("Zomvick");
                aClient.setCuit(Long.valueOf("27352170750"));
                aClient.setAddress("Lorca 260");
                aClient.setEmail("vicky@gmail.com");
                aClient.setPhoneNumber(Long.valueOf("1562084681"));

                anUser = new User();
                anUser.setClientId(aClient);
                anUser.setNickname("PEPITO");
                anUser.setPassword("123456");
                anUser.setRole("ADMIN");

                anOrder = new Order();
                anOrder.setUserId(anUser);
                anOrder.setVisible(true);
                anOrder.setStatus("FULFILLED");
                anOrder.setAmount(new BigDecimal(100.99));
                anOrder.setDate(new DateTime().toDate());

                sessionObj.save(aClient);
                sessionObj.save(anUser);
                sessionObj.save(anOrder);
            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }

        //SpringApplication.run(Main.class, args);
    }

}
