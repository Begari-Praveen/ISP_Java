package dao;

import database.DBConnection;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the payments table.
 */
public class PaymentDAO {

    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payments (bill_id, amount, payment_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payment.getBillId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentDate());
            stmt.setString(4, payment.getStatus());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error adding payment: " + e.getMessage());
            return false;
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT payment_id, bill_id, amount, payment_date, status FROM payments";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Payment p = new Payment();
                p.setPaymentId(rs.getInt("payment_id"));
                p.setBillId(rs.getInt("bill_id"));
                p.setAmount(rs.getDouble("amount"));
                p.setPaymentDate(rs.getString("payment_date"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }
        } catch (Exception e) {
            System.err.println("Error fetching payments: " + e.getMessage());
        }
        return list;
    }

    public List<Payment> getPaymentsByBillId(int billId) {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT payment_id, bill_id, amount, payment_date, status FROM payments WHERE bill_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Payment p = new Payment();
                    p.setPaymentId(rs.getInt("payment_id"));
                    p.setBillId(rs.getInt("bill_id"));
                    p.setAmount(rs.getDouble("amount"));
                    p.setPaymentDate(rs.getString("payment_date"));
                    p.setStatus(rs.getString("status"));
                    list.add(p);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching payments: " + e.getMessage());
        }
        return list;
    }
}
