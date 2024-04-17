import models.User;
import services.AuthService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JPanel panel1;
    private AuthService authService;

    public LoginForm(ActionListener listener) {
        authService = AuthService.getInstance();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String token = authService.login(textField1.getText(), new String(passwordField1.getPassword()));
                if(token != null) {
                    App.setAuthToken(token);
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "LoginSuccessful"));
                }
                else
                    listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "LoginFailed"));
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
