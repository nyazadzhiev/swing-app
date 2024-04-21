
import models.Order;
import models.Role;
import models.User;
import services.AuthService;
import services.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DashBoard {
    private JPanel panel1;
    private JTable orderTable;
    private JTable userTable;
    private JButton logoutButton;
    private JLabel userLabel;
    private JButton createOrderButton;
    private AuthService authService;
    private OrderService orderService;
    private User loggedUser;
    private String selectedUser;
    List<Order> orderList;

    public DashBoard(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public JPanel getPanel1(ActionListener listener) {

        selectedUser = "";
        authService = AuthService.getInstance();
        orderService = new OrderService();
        List<User> userList = authService.getAllUsers();
        userLabel.setText("Hello ,"+ loggedUser.getUsername());
        if(loggedUser.getRole() != Role.ADMIN){
            userTable.setVisible(false);
        }
        if(loggedUser.getRole() == Role.CLIENT){
            orderList = orderService.getOrdersByClient(loggedUser.getUsername());
        }else {
            orderList = orderService.getAllOrders();
        }

        orderTable.setModel(createOrderModel(orderList));
        userTable.setModel(createUserModel(userList));

        orderTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed() && e.getButton() == MouseEvent.BUTTON1) {
                    int row = orderTable.getSelectedRow();
                    // Assuming your orderList contains at least 8 elements (0-based indexing)
                    if (row >= 0 && row <= orderList.size()) {
                        Order selectedOrder = orderList.get(row);
                        // Create an instance of the DetailsPage and pass the selectedOrder to its constructor
                        DetailsPage detailsPage = new DetailsPage(selectedOrder, listener);
                        // Create a JDialog to show the DetailsPage as a modal dialog
                        JDialog dialog = new JDialog((JFrame) null, "Order Details", true);
                        dialog.setContentPane(detailsPage.getPanel1());
                        dialog.pack();
                        dialog.setLocationRelativeTo(panel1);
                        dialog.setVisible(true);
                    }
                }
            }
        });

        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
                    int row = userTable.getSelectedRow();
                    if(row>=0 && row< userList.size()){
                        User chosenUser = userList.get(row);
                        UserDetails userDetails = new UserDetails(chosenUser, listener);
                        JDialog dialog = new JDialog((JFrame) null, "Order Details", true);
                        dialog.setContentPane(userDetails.getPanel());
                        dialog.pack();
                        dialog.setLocationRelativeTo(panel1);
                        dialog.setVisible(true);

                    }
                }
            }
        });


        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateOrder createOrder = new CreateOrder(loggedUser, listener);
                JDialog dialog = new JDialog((JFrame) null, "Create Order", true);
                dialog.setContentPane(createOrder.getPanel());
                dialog.pack();
                dialog.setLocationRelativeTo(panel1);
                dialog.setVisible(true);
            }
        });

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
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }};
        tableModel.addColumn("OrderId");
        tableModel.addColumn("Courier");
        tableModel.addColumn("Client");
        tableModel.addColumn("Status");
        for (Order order : orderList){
            tableModel.addRow( new Object[]{
                    order.getOrderId(),
                    order.getCourier() != null ? order.getCourier().getUsername() : "",
                    order.getClient().getUsername(),
                    order.getStatus().toString()
                    });
        }
        return tableModel;
    }
    public DefaultTableModel createUserModel(List<User>userList){

        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Username");
        tableModel.addColumn("Role");
        for(User user : userList){
            tableModel.addRow(new Object[]{
            user.getUsername(),
            user.getRole().toString()});
        }

        return tableModel;
    }

    public void updateListUI(){


        if(loggedUser.getRole() == Role.CLIENT) {
            orderList = orderService.getOrdersByClient(loggedUser.getUsername());
        }
        else{
            orderList = orderService.getAllOrders();
        }
        List<User> userList = authService.getAllUsers();
        userTable.setModel(createUserModel(userList));
        orderTable.setModel(createOrderModel(orderList));
    }
}
