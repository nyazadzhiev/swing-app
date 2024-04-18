import models.Role;
import models.User;
import services.AuthService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDetails {
    private  JPanel panel;
    private JLabel AccountLabel;
    private JTextField textFieldPassword;
    private JTextField textFieldUsername;
    private JComboBox comboBox;
    private JButton deleteButton;
    private JButton updateButton;
    private AuthService authService;

    public UserDetails(User user, ActionListener listener) {
        for(Role role : Role.values()){
            comboBox.addItem(role);
        }
        authService = AuthService.getInstance();
        AccountLabel.setText(user.getUsername().toString());
        textFieldUsername.setText(user.getUsername());
        textFieldUsername.setEditable(false);
        textFieldPassword.setText(user.getPassword());
        comboBox.setSelectedItem(user.getRole());

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authService.deleteUser(user.getUsername());
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RefreshDashboard"));
                SwingUtilities.getWindowAncestor(panel).dispose();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updatedPassword = textFieldPassword.getText();
                authService.updateUser(user.getUsername(), updatedPassword);
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RefreshDashboard"));
                SwingUtilities.getWindowAncestor(panel).dispose();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
