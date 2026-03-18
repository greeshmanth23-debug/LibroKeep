package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.dao.BookDAO;

public class UpdateBookUI extends JFrame {

    JTextField bidField, qtyField;
    JButton updateBtn;

    BookDAO bookDAO = new BookDAO();

    public UpdateBookUI() {

        setTitle("Update Book Quantity");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Book ID:"));
        bidField = new JTextField();
        add(bidField);

        add(new JLabel("New Quantity:"));
        qtyField = new JTextField();
        add(qtyField);

        updateBtn = new JButton("Update");
        add(updateBtn);

        updateBtn.addActionListener(e -> updateBook());

        setVisible(true);
    }

    private void updateBook() {
        try {
            int bid = Integer.parseInt(bidField.getText());
            int qty = Integer.parseInt(qtyField.getText());

            bookDAO.updateBookQty(bid, qty);

            JOptionPane.showMessageDialog(this, "Book Updated!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }
}