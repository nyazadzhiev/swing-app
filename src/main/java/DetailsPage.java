import models.Order;
import models.Status;
import models.User;
import services.AuthService;
import services.OrderService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DetailsPage {
    private JButton backButton;
    private JButton removeButton;
    private JButton editButton;
    private JPanel panel1;
    private JTextField textFieldId;
    private JTextField textFieldClient;
    private JComboBox comboBox1;
    private JComboBox comboBoxCourier;
    private Status selectedStatus;

    private AuthService authService;
    private OrderService orderService;
    public DetailsPage(Order order, ActionListener listener) {
        authService = AuthService.getInstance();
        List<User> userList = authService.getAllCouriers();
        for (User user : userList)
            comboBoxCourier.addItem(user);
        comboBoxCourier.setSelectedItem(order.getCourier());
        comboBoxCourier.setEditable(false);
        orderService = new OrderService();
        textFieldId.setText(order.getOrderId().toString());
        textFieldClient.setText(order.getClient().getUsername());
        for (Status status : Status.values()){
            comboBox1.addItem(status);
        }
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedStatus = (Status)comboBox1.getSelectedItem();
                User selectedUser = (User) comboBoxCourier.getSelectedItem();
                orderService.updateOrder(order.getOrderId(), selectedStatus, selectedUser);
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RefreshDashboard"));
                SwingUtilities.getWindowAncestor(panel1).dispose();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderService.deleteOrder(order.getOrderId());
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RefreshDashboard"));
                SwingUtilities.getWindowAncestor(panel1).dispose();
            }
        });
    }

    public JPanel getPanel1() {


        return panel1;

    }
}
