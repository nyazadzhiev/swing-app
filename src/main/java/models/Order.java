package models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private User courier;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String orderInfo;

    public Order() {
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Order(User courier, User client, Status status, String orderInfo) {
        this.courier = courier;
        this.client = client;
        this.status = status;
        this.orderInfo = orderInfo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
        this.courier = courier;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", courier=" + courier +
                ", client=" + client +
                ", status=" + status +
                '}';
    }
}
