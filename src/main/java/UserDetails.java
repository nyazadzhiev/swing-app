import models.Role;
import models.User;

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

    public UserDetails(User user) {
        for(Role role : Role.values()){
            comboBox.addItem(role);
        }
        AccountLabel.setText(user.getUsername().toString());
        textFieldUsername.setText(user.getUsername());
        textFieldUsername.setEditable(false);
        textFieldPassword.setText(user.getPassword());
        comboBox.setSelectedItem(user.getRole());

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete(user.getUsername());
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updatedPassword = textFieldPassword.getText();
                Role updatedRole = (Role)comboBox.getSelectedItem();

            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
