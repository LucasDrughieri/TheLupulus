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
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Main {

    static Client aClient;
    static User anUser;
    static Order anOrder;
    static Beer aBeer;
    static Container aContainer;
    static Item anItem;
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
                anUser.setRole(UserRole.NORMAL_USER.getCode());

                anOrder = new Order();
                anOrder.setUserId(anUser);
                anOrder.setVisible(true);
                anOrder.setStatus(OrderState.FINALIZED.getCode());
                anOrder.setAmount(new BigDecimal(100.99));
                anOrder.setDate(new DateTime().toDate());

                aBeer = new Beer();
                aBeer.setBeerId(Long.valueOf("123123123"));
                aBeer.setColor("Rubia");
                aBeer.setDensity(0.14f);
                aBeer.setGraduation(0.15f);
                aBeer.setGranos("Some granos");
                aBeer.setIbu(0.6f);
                aBeer.setName("My Beer");
                aBeer.setPricePerLitre(13f);
                aBeer.setQuantity(100);
                aBeer.setVisible(true);

                aContainer = new Container();
                aContainer.setCapacity(150);
                aContainer.setContainerId(Long.valueOf("123"));
                aContainer.setHeight(1.2f);
                aContainer.setMaterial("Madera");
                aContainer.setQuantity(10);
                aContainer.setVisible(true);

                anItem = new Item();
                anItem.setBeerId(aBeer);
                anItem.setContainerId(aContainer);
                anItem.setOrderId(anOrder);
                anItem.setItemId(Long.valueOf("123456"));

                sessionObj.save(aClient);
                sessionObj.save(anUser);
                sessionObj.save(anOrder);
                sessionObj.save(aBeer);
                sessionObj.save(aContainer);
                sessionObj.save(anItem);
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
