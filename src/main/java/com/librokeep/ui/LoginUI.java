package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.dao.UserDAO;
import com.librokeep.model.User;

public class LoginUI extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn;

    UserDAO userDAO = new UserDAO();

    public LoginUI() {

        setTitle("Library Login");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginBtn = new JButton("Login");
        add(loginBtn);
        JButton registerBtn = new JButton("Register");
        add(registerBtn);

        // Button click event
        registerBtn.addActionListener(e -> {
            new RegisterUI();
        });
        loginBtn.addActionListener(e -> login());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = userDAO.authenticate(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

            // 🔥 Open Menu UI
            new MenuUI(user);  

            // 🔥 Close login window
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials!");
        }
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}