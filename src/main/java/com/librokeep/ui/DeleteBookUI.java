package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.dao.BookDAO;

public class DeleteBookUI extends JFrame {

    JTextField bidField;
    JButton deleteBtn;

    BookDAO bookDAO = new BookDAO();

    public DeleteBookUI() {

        setTitle("Delete Book");
        setSize(300, 150);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Enter Book ID:"));
        bidField = new JTextField();
        add(bidField);

        deleteBtn = new JButton("Delete");
        add(deleteBtn);

        deleteBtn.addActionListener(e -> deleteBook());

        setVisible(true);
    }

    private void deleteBook() {
        try {
            int bid = Integer.parseInt(bidField.getText());

            bookDAO.deleteBook(bid);

            JOptionPane.showMessageDialog(this, "Book Deleted!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }
}