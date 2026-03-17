package com.librokeep.dao;

import com.librokeep.config.DBConnection;
import com.librokeep.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        String query = "INSERT INTO books VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, book.getBid());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getQty());

            stmt.executeUpdate();
            System.out.println("Book Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        String query = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("bid") + " | " +
                        rs.getString("title") + " | " +
                        rs.getInt("qty")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Book> getAll() {

        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("bid"),
                        rs.getString("title"),
                        rs.getInt("qty")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }
    public void updateBookQty(int bid, int qty) {
        String query = "UPDATE books SET qty=? WHERE bid=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, qty);
            stmt.setInt(2, bid);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("Book Updated Successfully!");
            else
                System.out.println("Book Not Found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bid) {
        String query = "DELETE FROM books WHERE bid=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bid);

            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("Book Deleted Successfully!");
            else
                System.out.println("Book Not Found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}