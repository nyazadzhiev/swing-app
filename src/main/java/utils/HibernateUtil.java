package utils;

import models.Office;
import models.Order;
import models.Role;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/courier_db?useSSL=false");
                settings.put(Environment.USER, "user");
                settings.put(Environment.PASS, "user");
                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(Office.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                Session session = sessionFactory.openSession();

                List<Office> offices = new ArrayList<>();

                for (int i = 0; i<5; i++){
                    Office office = new Office("City" + Integer.toString(i), "Name" + Integer.toString(i), "089898989");
                    offices.add(office);
                    session.save(office);
                }

                User newUser = new User("user", "user", null, Role.ADMIN, null);
                session.beginTransaction();
                //session.save(newUser);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
