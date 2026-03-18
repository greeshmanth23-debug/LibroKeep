package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.dao.BorrowerDAO;
import com.librokeep.model.User;

public class ReturnUI extends JFrame {

    JTextField bidField;
    JButton returnBtn;

    BorrowerDAO borrowerDAO = new BorrowerDAO();
    User user;

    public ReturnUI(User user) {

        this.user = user;

        setTitle("Return Book");
        setSize(300, 150);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Enter Book ID:"));
        bidField = new JTextField();
        add(bidField);

        returnBtn = new JButton("Return");
        add(returnBtn);

        returnBtn.addActionListener(e -> returnBook());

        setVisible(true);
    }

    private void returnBook() {
        try {
            int bid = Integer.parseInt(bidField.getText());

            borrowerDAO.returnBook(user.getUsername(), bid);

            JOptionPane.showMessageDialog(this, "Book Returned!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }
}