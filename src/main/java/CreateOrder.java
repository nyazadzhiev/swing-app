import models.Order;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateOrder {
    private JPanel panel;
    private JButton submitButton;
    private JTextArea textArea1;

    public CreateOrder(User user) {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();
                order.setClient(user);
                order.setOrderInfo(textArea1.getText());
                //createOrder(order);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
