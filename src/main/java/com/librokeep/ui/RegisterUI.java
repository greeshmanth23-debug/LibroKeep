package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.dao.UserDAO;

public class RegisterUI extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton registerBtn;

    UserDAO userDAO = new UserDAO();

    public RegisterUI() {

        setTitle("Register");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        registerBtn = new JButton("Register");
        add(registerBtn);
        JButton backBtn = new JButton("Back to Login");
        add(backBtn);

        registerBtn.addActionListener(e -> register());
        backBtn.addActionListener(e -> {
            new LoginUI();
            dispose();
        });

        setVisible(true);
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        userDAO.registerUser(username, password);

        JOptionPane.showMessageDialog(this, "User Registered!");
        dispose(); // close window
    }
}