package com.librokeep.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.librokeep.dao.BorrowerDAO;
import com.librokeep.model.User;

import java.util.List;

public class MyBooksUI extends JFrame {

    JTable table;
    DefaultTableModel model;

    BorrowerDAO borrowerDAO = new BorrowerDAO();

    public MyBooksUI(User user) {

        setTitle("My Borrowed Books");
        setSize(500, 300);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Transaction ID");
        model.addColumn("Book ID");
        model.addColumn("Borrowed Time");

        List<Object[]> data = borrowerDAO.getMyBooks(user.getUsername());

        for (Object[] row : data) {
            model.addRow(row);
        }

        add(new JScrollPane(table));

        setVisible(true);
    }
}