package dao;

import database.DBConnection;
import model.Plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for the plans table.
 */
public class PlanDAO {

    public boolean addPlan(Plan plan) {
        String sql = "INSERT INTO plans (plan_name, speed, price) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plan.getPlanName());
            stmt.setInt(2, plan.getSpeed());
            stmt.setDouble(3, plan.getPrice());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error adding plan: " + e.getMessage());
            return false;
        }
    }

    public List<Plan> getAllPlans() {
        List<Plan> list = new ArrayList<>();
        String sql = "SELECT plan_id, plan_name, speed, price FROM plans";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Plan p = new Plan();
                p.setPlanId(rs.getInt("plan_id"));
                p.setPlanName(rs.getString("plan_name"));
                p.setSpeed(rs.getInt("speed"));
                p.setPrice(rs.getDouble("price"));
                list.add(p);
            }
        } catch (Exception e) {
            System.err.println("Error fetching plans: " + e.getMessage());
        }
        return list;
    }

    public Plan getPlanById(int planId) {
        String sql = "SELECT plan_id, plan_name, speed, price FROM plans WHERE plan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, planId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Plan p = new Plan();
                    p.setPlanId(rs.getInt("plan_id"));
                    p.setPlanName(rs.getString("plan_name"));
                    p.setSpeed(rs.getInt("speed"));
                    p.setPrice(rs.getDouble("price"));
                    return p;
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching plan: " + e.getMessage());
        }
        return null;
    }

    public boolean updatePlan(Plan plan) {
        String sql = "UPDATE plans SET plan_name = ?, speed = ?, price = ? WHERE plan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plan.getPlanName());
            stmt.setInt(2, plan.getSpeed());
            stmt.setDouble(3, plan.getPrice());
            stmt.setInt(4, plan.getPlanId());
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error updating plan: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePlan(int planId) {
        String sql = "DELETE FROM plans WHERE plan_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, planId);
            return stmt.executeUpdate() == 1;
        } catch (Exception e) {
            System.err.println("Error deleting plan: " + e.getMessage());
            return false;
        }
    }
}
