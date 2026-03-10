/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.loanerasystem;
import java.sql.*;
/**
 *
 * @author Ong
 */
public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/loan_risk";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean insertBorrower(String name, double income, double debt, String employment,
                                         double requestedAmount, int score, String riskLevel, boolean eligible) {
        String sql = "INSERT INTO borrowers (name, income, debt, employment_status, requested_amount, score, risk_level, eligible) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, income);
            stmt.setDouble(3, debt);
            stmt.setString(4, employment);
            stmt.setDouble(5, requestedAmount);
            stmt.setInt(6, score);
            stmt.setString(7, riskLevel);
            stmt.setBoolean(8, eligible);

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String checkLogin(String username, String password) {
        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role"); // 'admin' or 'officer'
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
