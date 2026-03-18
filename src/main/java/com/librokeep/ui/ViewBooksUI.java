package com.librokeep.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.librokeep.dao.BookDAO;
import com.librokeep.model.Book;

public class ViewBooksUI extends JFrame {

    JTable table;
    DefaultTableModel model;

    BookDAO bookDAO = new BookDAO();

    public ViewBooksUI() {

        setTitle("View Books");
        setSize(500, 300);
        setLayout(new BorderLayout());

        // Columns
        String[] columns = {"Book ID", "Title", "Quantity"};

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();

        setVisible(true);
    }

    private void loadData() {

        List<Book> books = bookDAO.getAll();

        for (Book b : books) {
            Object[] row = {
                    b.getBid(),
                    b.getTitle(),
                    b.getQty()
            };
            model.addRow(row);
        }
    }
}