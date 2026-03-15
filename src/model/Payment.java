package model;

/**
 * Represents a payment that a customer makes for a bill.
 */
public class Payment {
    private int paymentId;
    private int billId;
    private double amount;
    private String paymentDate;
    private String status; // Completed, Failed, Pending

    public Payment() {
    }

    public Payment(int paymentId, int billId, double amount, String paymentDate, String status) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", billId=" + billId +
                ", amount=" + amount +
                ", paymentDate='" + paymentDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
