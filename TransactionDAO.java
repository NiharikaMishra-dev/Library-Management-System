package Library_Management_System;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class TransactionDAO {
    public void issueBook(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transactions (book_id, student_id, issue_date) VALUES (?, ?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getBookId());
            stmt.setInt(2, transaction.getStudentId());
            stmt.setDate(3, new java.sql.Date(transaction.getIssueDate().getTime()));
            stmt.executeUpdate();
        }
    }

    public void returnBook(int transactionId, Date returnDate) throws SQLException {
        String sql = "UPDATE transactions SET return_date = ? WHERE id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(returnDate.getTime()));
            stmt.setInt(2, transactionId);
            stmt.executeUpdate();
        }
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (Connection conn = DBconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("student_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("return_date")
                );
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}

