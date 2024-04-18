import models.Order;
import models.Status;
import models.User;
import services.OrderService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateOrder {
    private JPanel panel;
    private JButton submitButton;
    private JTextArea textArea1;
    private OrderService orderService;

    public CreateOrder(User user, ActionListener listener) {
        orderService = new OrderService();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();
                order.setClient(user);
                order.setOrderInfo(textArea1.getText());
                order.setStatus(Status.WAITINGCOURIER);
                orderService.saveOrder(order);
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RefreshDashboard"));
                SwingUtilities.getWindowAncestor(panel).dispose();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
