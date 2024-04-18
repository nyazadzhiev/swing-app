import models.Order;
import models.Status;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public DetailsPage(Order order) {

        //textFieldId.setText(order.getOrderId().toString());
        //for(Courier courier : courierList)
        //comboBoxCourier.addItem(courier);}
        comboBoxCourier.setSelectedItem(order.getCourier());
        //if(user.getRole() != Role.ADMIN){
        comboBoxCourier.setEditable(false);
        //}
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
