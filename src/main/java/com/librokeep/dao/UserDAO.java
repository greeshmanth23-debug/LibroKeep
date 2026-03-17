package com.librokeep.dao;

import com.librokeep.config.DBConnection;
import com.librokeep.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // ================= LOGIN =================
    public User authenticate(String username, String password) {

        String query = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("uid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= REGISTER =================
    public void registerUser(String username, String password) {

        String checkLibrarian = "SELECT COUNT(*) FROM users WHERE role='LIBRARIAN'";
        String insertUser = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkLibrarian);
             ResultSet rs = checkStmt.executeQuery()) {

            String role = "CUSTOMER";

            // 🔥 Check if librarian already exists
            if (rs.next()) {
                int count = rs.getInt(1);

                if (count == 0) {
                    role = "LIBRARIAN"; // First user becomes librarian
                }
            }

            PreparedStatement insertStmt = conn.prepareStatement(insertUser);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password);
            insertStmt.setString(3, role);

            insertStmt.executeUpdate();

            System.out.println("✅ Registered Successfully as " + role);

        } catch (Exception e) {
            System.out.println("❌ Username already exists or error occurred!");
        }
    }
}