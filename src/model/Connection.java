package model;

/**
 * Represents an internet connection request and status for a customer.
 */
public class Connection {
    private int connectionId;
    private int customerId;
    private int planId;
    private String status; // Pending, Active, Suspended, Disconnected
    private String activationDate;

    public Connection() {
    }

    public Connection(int connectionId, int customerId, int planId, String status, String activationDate) {
        this.connectionId = connectionId;
        this.customerId = customerId;
        this.planId = planId;
        this.status = status;
        this.activationDate = activationDate;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "connectionId=" + connectionId +
                ", customerId=" + customerId +
                ", planId=" + planId +
                ", status='" + status + '\'' +
                ", activationDate='" + activationDate + '\'' +
                '}';
    }
}
