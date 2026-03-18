package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.dao.BorrowerDAO;
import com.librokeep.model.User;

public class BorrowUI extends JFrame {

    JTextField bidField;
    JButton borrowBtn;

    BorrowerDAO borrowerDAO = new BorrowerDAO();
    User user;

    public BorrowUI(User user) {

        this.user = user;

        setTitle("Borrow Book");
        setSize(300, 150);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Enter Book ID:"));
        bidField = new JTextField();
        add(bidField);

        borrowBtn = new JButton("Borrow");
        add(borrowBtn);

        borrowBtn.addActionListener(e -> borrowBook());

        setVisible(true);
    }

    private void borrowBook() {
        try {
            int bid = Integer.parseInt(bidField.getText());

            borrowerDAO.borrowBook(user.getUsername(), bid);

            JOptionPane.showMessageDialog(this, "Book Borrowed!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }
}