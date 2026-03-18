package com.librokeep.ui;

import javax.swing.*;
import java.awt.*;

import com.librokeep.model.User;

public class MenuUI extends JFrame {

    User user;

    public MenuUI(User user) {

        this.user = user;

        setTitle("Welcome " + user.getUsername() + " (" + user.getRole() + ")");
        setSize(350, 450);
        setLayout(new GridLayout(7, 1)); // increased rows

        // Buttons
        JButton viewBtn = new JButton("View Books");
        JButton addBtn = new JButton("Add Book");
        JButton updateBtn = new JButton("Update Quantity");
        JButton deleteBtn = new JButton("Delete Book");
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton myBooksBtn = new JButton("My Borrowed Books"); // ✅ NEW
        JButton exitBtn = new JButton("Logout");

        // Always available
        add(viewBtn);

        if (user.getRole().equals("LIBRARIAN")) {
            add(addBtn);
            add(updateBtn);
            add(deleteBtn);
        } else {
            add(borrowBtn);
            add(returnBtn);
            add(myBooksBtn); // ✅ ADD HERE
        }

        add(exitBtn);

        // Actions
        viewBtn.addActionListener(e -> new ViewBooksUI());

        addBtn.addActionListener(e -> new AddBookUI());
        updateBtn.addActionListener(e -> new UpdateBookUI());
        deleteBtn.addActionListener(e -> new DeleteBookUI());

        borrowBtn.addActionListener(e -> new BorrowUI(user));
        returnBtn.addActionListener(e -> new ReturnUI(user));

        myBooksBtn.addActionListener(e -> new MyBooksUI(user)); // ✅ NEW

        exitBtn.addActionListener(e -> {
            new LoginUI();
            dispose();
        });

        setVisible(true);
    }
}