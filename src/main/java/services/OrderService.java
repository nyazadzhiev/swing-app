package services;

import jakarta.persistence.Query;
import models.Order;
import models.Status;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class OrderService {
    private final SessionFactory sessionFactory;

    public OrderService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    // Create operation
    public void saveOrder(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Read operation
    public Order getOrderById(Long orderId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update operation
    public void updateOrder(Long orderId, Status newStatus, User courier) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Fetch the existing order by ID
            Order existingOrder = getOrderById(orderId);
            if (existingOrder != null) {
                existingOrder.setStatus(newStatus);
                existingOrder.setCourier(courier);
                session.update(existingOrder);
                transaction.commit();
            } else {
                System.err.println("Order with ID " + orderId + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete operation
    public void deleteOrder(Long orderId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Fetch the order by ID
            Order orderToDelete = getOrderById(orderId);
            if (orderToDelete != null) {
                session.delete(orderToDelete);
                transaction.commit();
            } else {
                System.err.println("Order with ID " + orderId + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Additional method to get all orders
    public List<Order> getAllOrders() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to get orders by client
    public List<Order> getOrdersByClient(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Order WHERE client.username = :username", Order.class);
            query.setParameter("username", username);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to get orders by courier
    public List<Order> getOrdersByCourier(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Order WHERE courier.username = :username", Order.class);
            query.setParameter("username", username);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
