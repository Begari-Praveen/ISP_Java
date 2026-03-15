package model;

/**
 * Represents an internet plan (Basic / Standard / Premium) in the ISP Management System.
 */
public class Plan {
    private int planId;
    private String planName;
    private int speed;
    private double price;

    public Plan() {
    }

    public Plan(int planId, String planName, int speed, double price) {
        this.planId = planId;
        this.planName = planName;
        this.speed = speed;
        this.price = price;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", planName='" + planName + '\'' +
                ", speed=" + speed +
                ", price=" + price +
                '}';
    }
}
