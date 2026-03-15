package model;

/**
 * Represents a monthly bill that the customer must pay.
 */
public class Bill {
    private int billId;
    private int customerId;
    private double amount;
    private String dueDate;
    private String status; // Pending, Paid

    public Bill() {
    }

    public Bill(int billId, int customerId, double amount, String dueDate, String status) {
        this.billId = billId;
        this.customerId = customerId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", dueDate='" + dueDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
