import generators.OrderGenerator;
import models.Order;
import models.Person;
import models.Role;
import models.User;
import services.AuthService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DashBoard {
    private JPanel panel1;
    private JTable orderTable;
    private JTable userTable;
    private JButton editButtonOrders;
    private JButton removeButtonOrders;
    private JButton editButtonUser;
    private JButton removeButtonUser;
    private JButton logoutButton;
    private JLabel userLabel;
    private AuthService authService;


    public JPanel getPanel1(ActionListener listener) {
        authService = AuthService.getInstance();
        List<Order> orderList = OrderGenerator.generateOrders(10);
        List<User> userList = new ArrayList<>();
        Person person = new Person();
        User user = new User("nz","nz",person, Role.ADMIN);
        userList.add(user);
        userLabel.setText("Hello ,"+ user.getRole().toString());
        if(user.getRole() != Role.ADMIN){
            userTable.setVisible(false);
            editButtonUser.setVisible(false);
            removeButtonUser.setVisible(false);
        }
        if(user.getRole() == Role.CLIENT){
            removeButtonOrders.setVisible(false);
            editButtonOrders.setVisible(false);
        }

        orderTable.setModel(createOrderModel(orderList));
        userTable.setModel(createUserModel(userList));

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean token = authService.logout(App.getAuthToken());
                if ( token == true){
                    listener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"LogoutSuccessful"));
                }
                else
                    listener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"LogoutFailed"));
            }
        });


        return panel1;
    }

    public DefaultTableModel createOrderModel(List<Order>orderList){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("OrderId");
        tableModel.addColumn("Courier");
        tableModel.addColumn("Client");
        tableModel.addColumn("Status");
        for (Order order : orderList){
            tableModel.addRow( new Object[]{
                    order.getOrderId(),
                    order.getCourier(),
                    order.getClient(),
                    order.getStatus().toString()
                    });
        }
        return tableModel;
    }
    public DefaultTableModel createUserModel(List<User>userList){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Username");
        tableModel.addColumn("Role");
        for(User user : userList){
            tableModel.addRow(new Object[]{
            user.getUsername(),
            user.getRole().toString()});
        }
        return tableModel;
    }
}
