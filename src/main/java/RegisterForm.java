import models.Person;
import models.Role;
import services.AuthService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private JPanel panel1;
    private JTextField username;
    private JPasswordField password;
    private JTextField name;
    private JTextField phoneNumber;
    private JTextField address;
    private JButton Register;
    private JComboBox comboBox1;
    private AuthService authService;

    public RegisterForm(ActionListener listener) {
        authService = AuthService.getInstance();
        comboBox1.addItem(Role.CLIENT);
        comboBox1.addItem(Role.COURIER);

        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = username.getText();
                String passWord = new String(password.getPassword());
                String namee = name.getText();
                String phone = phoneNumber.getText();
                String home = address.getText();

                Role role =  (Role)comboBox1.getSelectedItem();

                boolean isSuccessfull = authService
                        .register(userName, passWord, new Person(namee, phone, home, "", "", 0), role);

                if(isSuccessfull)
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RegisterSuccessful"));
                else
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RegisterFailed"));
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
