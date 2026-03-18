package com.librokeep.dao;

import com.librokeep.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowerDAO {

    // 📌 Borrow Book
    public void borrowBook(String name, int bookId) {

        String checkQuery = "SELECT qty FROM books WHERE bid=?";
        String insertQuery = "INSERT INTO borrowers (customer_name, book_id) VALUES (?, ?)";
        String updateQuery = "UPDATE books SET qty = qty - 1 WHERE bid=?";

        try (Connection conn = DBConnection.getConnection()) {

            // Check quantity
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int qty = rs.getInt("qty");

                if (qty <= 0) {
                    System.out.println("Book Out of Stock!");
                    return;
                }

                // Insert borrower record
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, name);
                insertStmt.setInt(2, bookId);
                insertStmt.executeUpdate();

                // Decrease quantity
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                System.out.println("Book Borrowed Successfully!");

            } else {
                System.out.println("Book Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 📌 Return Book
    public void returnBook(String username, int bookId) {

        String check = "SELECT * FROM borrowers WHERE customer_name=? AND book_id=?";
        String delete = "DELETE FROM borrowers WHERE customer_name=? AND book_id=?";
        String updateQty = "UPDATE books SET qty = qty + 1 WHERE bid=?";

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement checkStmt = conn.prepareStatement(check);
            checkStmt.setString(1, username);
            checkStmt.setInt(2, bookId);

            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ You didn't borrow this book!");
                return;
            }

            PreparedStatement deleteStmt = conn.prepareStatement(delete);
            deleteStmt.setString(1, username);
            deleteStmt.setInt(2, bookId);
            deleteStmt.executeUpdate();

            PreparedStatement updateStmt = conn.prepareStatement(updateQty);
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();

            System.out.println("✅ Book Returned Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewMyBooks(String username) {

        String query = "SELECT b.bid, b.title " +
                "FROM borrowers br " +
                "JOIN books b ON br.book_id = b.bid " +
                "WHERE br.customer_name=?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            boolean found = false;

            System.out.println("\n===== MY BORROWED BOOKS =====");

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("bid") + " | " +
                                rs.getString("title"));
            }

            if (!found) {
                System.out.println("No books borrowed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getMyBooks(String username) {

        List<Object[]> list = new ArrayList<>();

        String query = "SELECT tid, book_id, borrowed_time FROM borrowers WHERE customer_name=?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Object[] {
                        rs.getInt("tid"),
                        rs.getInt("book_id"),
                        rs.getTimestamp("borrowed_time")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}