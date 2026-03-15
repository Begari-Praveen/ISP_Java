package dao;

import database.DBConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the customers table.
 */
public class CustomerDAO {

    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, address, phone, email, plan_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.setInt(5, customer.getPlanId());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error adding customer: " + e.getMessage());
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT customer_id, name, address, phone, email, plan_id FROM customers";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setPlanId(rs.getInt("plan_id"));
                list.add(c);
            }
        } catch (Exception e) {
            System.err.println("Error fetching customers: " + e.getMessage());
        }
        return list;
    }

    public Customer getCustomerById(int customerId) {
        String sql = "SELECT customer_id, name, address, phone, email, plan_id FROM customers WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setCustomerId(rs.getInt("customer_id"));
                    c.setName(rs.getString("name"));
                    c.setAddress(rs.getString("address"));
                    c.setPhone(rs.getString("phone"));
                    c.setEmail(rs.getString("email"));
                    c.setPlanId(rs.getInt("plan_id"));
                    return c;
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching customer: " + e.getMessage());
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name = ?, address = ?, phone = ?, email = ?, plan_id = ? WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setString(4, customer.getEmail());
            stmt.setInt(5, customer.getPlanId());
            stmt.setInt(6, customer.getCustomerId());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error updating customer: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            return false;
        }
    }
}
