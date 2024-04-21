import models.*;
import services.AuthService;
import services.OrderService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private AuthService authService;

    private static String authToken;

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String token) {
        authToken = token;
    }

    private DashBoard dashBoard;

    public App() {
        authService = AuthService.getInstance();
        String token =  authService.login("niazi", "niazi");
        App.setAuthToken(token);
        setTitle("App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);

        JPanel homePanel = createScreen("Home");
        cardPanel.add(homePanel, "Home");

        JPanel loginPanel = createScreen("Login");
        cardPanel.add(loginPanel, "Login");

        JPanel registerPanel = createScreen("Register");
        cardPanel.add(registerPanel, "Register");

        //JPanel dashboardPanel = createScreen("Dashboard");
        //cardPanel.add(dashboardPanel, "Dashboard");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        authService.logout(App.getAuthToken());
        App.setAuthToken(null);
    }

    private JPanel createScreen(String screen) {
        JPanel panel;
        switch (screen) {
            case "Login":
                panel = new JPanel(new BorderLayout());
                LoginForm loginForm = new LoginForm(this);

                panel.add(loginForm.getPanel1(), BorderLayout.CENTER);
                return panel;
            case "Register":
                panel = new JPanel(new BorderLayout());
                RegisterForm registerForm = new RegisterForm(this);

                panel.add(registerForm.getPanel1(), BorderLayout.CENTER);
                return panel;
            case "Dashboard":
                panel = new JPanel(new BorderLayout());
                String token = App.getAuthToken();
                User user =  authService.getUserBySessionToken(token);
                DashBoard dashBoard = new DashBoard(user);
                this.dashBoard = dashBoard;

                panel.add(dashBoard.getPanel1(this), BorderLayout.CENTER);
                return panel;
            case "Home":
                panel = new JPanel(new BorderLayout());
                HomeScreen homeScreen = new HomeScreen(this);

                panel.add(homeScreen.getPanel1(), BorderLayout.CENTER);
                return panel;

            default: return null;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "LoginSuccessful" :
            case "RegisterSuccessful" :
                JPanel dashboardPanel = createScreen("Dashboard");
                cardPanel.add(dashboardPanel, "Dashboard");
                cardLayout.show(cardPanel, "Dashboard");
                break;
            case "LoginFailed":
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                break;
            case "RegisterFailed":
                JOptionPane.showMessageDialog(this, "Error", "Register Failed", JOptionPane.ERROR_MESSAGE);
                break;
            case "LoginScreen":
                cardLayout.show(cardPanel, "Login");
                break;
            case "RegisterScreen":
                cardLayout.show(cardPanel, "Register");
                break;
            case "LogoutSuccessful":
                cardLayout.show(cardPanel,"Home");
                break;
            case "LogoutFailed":
                JOptionPane.showMessageDialog(this,"LogoutFailed","Error",JOptionPane.ERROR_MESSAGE);
                break;
            case "RefreshDashboard" :
                dashBoard.updateListUI();
                cardLayout.show(cardPanel, "Dashboard");
                break;

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
