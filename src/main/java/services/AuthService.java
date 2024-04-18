package services;

import jakarta.persistence.Query;
import models.Order;
import models.Person;
import models.Role;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import utils.HibernateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class AuthService {
    private final SessionFactory sessionFactory;
    private final Map<String, User> loggedInUsers;
    private static AuthService authServiceInstance;


    public static AuthService getInstance(){
        if (authServiceInstance == null){
            authServiceInstance = new AuthService();
        }
        return authServiceInstance;
    }

    private AuthService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
        this.loggedInUsers = new HashMap<>();
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            User newUser = new User("user", "user", null, Role.ADMIN);
//
//            // Begin transaction
//            session.beginTransaction();
//
//            // Save the new user to the database
//            session.save(newUser);
//
//            // Commit the transaction
//            session.getTransaction().commit();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public String login(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            User user = session.createQuery("FROM User WHERE username = :username AND password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            if (user != null) {
                String sessionToken = UUID.randomUUID().toString(); // Generate a session token
                loggedInUsers.put(sessionToken, user); // Store the session token and user
                return sessionToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public User getUserBySessionToken(String sessionToken) {
        return loggedInUsers.get(sessionToken); // Retrieve user using session token
    }

    public boolean logout(String sessionToken) {
        if (loggedInUsers.containsKey(sessionToken)) {
            loggedInUsers.remove(sessionToken); // Remove session token from map
            return true;
        }
        return false;
    }

    public boolean register(String username, String password, Person person, Role role) {
        if (username == null || password == null || person == null || role == null) {
            return false;
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User existingUser = session.get(User.class, username);
            if (existingUser != null) {
                transaction.rollback();
                return false;
            }

            User newUser = new User(username, password, person, role);
            session.save(newUser);

            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllCouriers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User WHERE role = :role", User.class)
                    .setParameter("role", Role.COURIER)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUser(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User userToDelete = session.get(User.class, username);
            if (userToDelete != null) {
                session.delete(userToDelete);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(String username, String newPassword) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User userToUpdate = session.get(User.class, username);
            if (userToUpdate != null) {
                userToUpdate.setPassword(newPassword);
                session.update(userToUpdate);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
