package dao;

import database.DBConnection;
import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the bills table.
 */
public class BillDAO {

    public boolean addBill(Bill bill) {
        String sql = "INSERT INTO bills (customer_id, amount, due_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bill.getCustomerId());
            stmt.setDouble(2, bill.getAmount());
            stmt.setString(3, bill.getDueDate());
            stmt.setString(4, bill.getStatus());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error adding bill: " + e.getMessage());
            return false;
        }
    }

    public List<Bill> getAllBills() {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT bill_id, customer_id, amount, due_date, status FROM bills";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Bill b = new Bill();
                b.setBillId(rs.getInt("bill_id"));
                b.setCustomerId(rs.getInt("customer_id"));
                b.setAmount(rs.getDouble("amount"));
                b.setDueDate(rs.getString("due_date"));
                b.setStatus(rs.getString("status"));
                list.add(b);
            }
        } catch (Exception e) {
            System.err.println("Error fetching bills: " + e.getMessage());
        }
        return list;
    }

    public Bill getBillById(int billId) {
        String sql = "SELECT bill_id, customer_id, amount, due_date, status FROM bills WHERE bill_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bill b = new Bill();
                    b.setBillId(rs.getInt("bill_id"));
                    b.setCustomerId(rs.getInt("customer_id"));
                    b.setAmount(rs.getDouble("amount"));
                    b.setDueDate(rs.getString("due_date"));
                    b.setStatus(rs.getString("status"));
                    return b;
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching bill: " + e.getMessage());
        }
        return null;
    }

    public boolean updateBillStatus(int billId, String status) {
        String sql = "UPDATE bills SET status = ? WHERE bill_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, billId);
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error updating bill status: " + e.getMessage());
            return false;
        }
    }
}
