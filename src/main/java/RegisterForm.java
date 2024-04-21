
import models.Office;
import models.Person;
import models.Role;
import services.AuthService;
import services.OrderService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisterForm {
    private JPanel panel1;
    private JTextField username;
    private JPasswordField password;
    private JTextField name;
    private JTextField phoneNumber;
    private JTextField address;
    private JButton Register;
    private JComboBox comboBox1;
    private JComboBox comboboxOffice;
    private AuthService authService;
    private OrderService orderService;

    public RegisterForm(ActionListener listener) {
        authService = AuthService.getInstance();
        orderService = new OrderService();
        comboBox1.addItem(Role.CLIENT);
        comboBox1.addItem(Role.COURIER);
        for (Office office : orderService.getAllOffices()){
            comboboxOffice.addItem(office);
        }
        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = username.getText();
                String passWord = new String(password.getPassword());
                String namee = name.getText();
                String phone = phoneNumber.getText();
                String home = address.getText();

                Role role =  (Role)comboBox1.getSelectedItem();
                Office office = (Office) comboboxOffice.getSelectedItem();

                boolean isSuccessfull = authService
                        .register(userName, passWord, new Person(namee, phone, home, 0), role, office);

                if(isSuccessfull) {
                    String token = authService.login(userName, passWord);
                    App.setAuthToken(token);
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RegisterSuccessful"));
                }
                else
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RegisterFailed"));
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
