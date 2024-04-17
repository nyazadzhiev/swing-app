import models.Order;
import models.Status;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsPage {
    private JButton backButton;
    private JButton removeButton;
    private JButton editButton;
    private JPanel panel1;
    private JTextField textFieldId;
    private JTextField textFieldCourier;
    private JTextField textFieldClient;
    private JComboBox comboBox1;
    private Status selectedStatus;

    public DetailsPage(Order order) {
        //textFieldId.setText(order.getOrderId().toString());
        textFieldCourier.setText(order.getCourier().toString());
        textFieldClient.setText(order.getClient().toString());
        for (Status status : Status.values()){
            comboBox1.addItem(status);
        }
        selectedStatus = (Status)comboBox1.getSelectedItem();

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //editOrderStatus(selectedStatus);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete(order.getOrderId);
            }
        });
    }

    public JPanel getPanel1() {


        return panel1;

    }
}
