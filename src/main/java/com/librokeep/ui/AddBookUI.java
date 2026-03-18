package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.dao.BookDAO;
import com.librokeep.model.Book;

public class AddBookUI extends JFrame {

    JTextField idField, titleField, qtyField;
    JButton addBtn;

    BookDAO bookDAO = new BookDAO();

    public AddBookUI() {

        setTitle("Add Book");
        setSize(300, 250);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Book ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        add(qtyField);

        addBtn = new JButton("Add");
        add(addBtn);

        addBtn.addActionListener(e -> addBook());

        setVisible(true);
    }

    private void addBook() {
        try {
            int id = Integer.parseInt(idField.getText());
            String title = titleField.getText();
            int qty = Integer.parseInt(qtyField.getText());

            bookDAO.addBook(new Book(id, title, qty));

            JOptionPane.showMessageDialog(this, "Book Added!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }
    }
}