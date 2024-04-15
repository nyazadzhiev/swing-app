package models;

public class Order {
    private String orderId;
    private User courier;
    private User client;
    private Status status;

    public Order(String orderId, User courier, User client, Status status) {
        this.orderId = orderId;
        this.courier = courier;
        this.client = client;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
