package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the connections table.
 */
public class ConnectionDAO {

    public boolean addConnection(model.Connection connection) {
        String sql = "INSERT INTO connections (customer_id, plan_id, status, activation_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, connection.getCustomerId());
            stmt.setInt(2, connection.getPlanId());
            stmt.setString(3, connection.getStatus());
            stmt.setString(4, connection.getActivationDate());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error adding connection: " + e.getMessage());
            return false;
        }
    }

    public boolean updateConnectionStatus(int connectionId, String status) {
        String sql = "UPDATE connections SET status = ? WHERE connection_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, connectionId);
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error updating connection status: " + e.getMessage());
            return false;
        }
    }

    public List<model.Connection> getAllConnections() {
        List<model.Connection> list = new ArrayList<>();
        String sql = "SELECT connection_id, customer_id, plan_id, status, activation_date FROM connections";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.Connection c = new model.Connection();
                c.setConnectionId(rs.getInt("connection_id"));
                c.setCustomerId(rs.getInt("customer_id"));
                c.setPlanId(rs.getInt("plan_id"));
                c.setStatus(rs.getString("status"));
                c.setActivationDate(rs.getString("activation_date"));
                list.add(c);
            }
        } catch (Exception e) {
            System.err.println("Error fetching connections: " + e.getMessage());
        }
        return list;
    }

    public model.Connection getConnectionById(int connectionId) {
        String sql = "SELECT connection_id, customer_id, plan_id, status, activation_date FROM connections WHERE connection_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, connectionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    model.Connection c = new model.Connection();
                    c.setConnectionId(rs.getInt("connection_id"));
                    c.setCustomerId(rs.getInt("customer_id"));
                    c.setPlanId(rs.getInt("plan_id"));
                    c.setStatus(rs.getString("status"));
                    c.setActivationDate(rs.getString("activation_date"));
                    return c;
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching connection: " + e.getMessage());
        }
        return null;
    }
}
