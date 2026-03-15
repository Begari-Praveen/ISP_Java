package main;

import dao.BillDAO;
import dao.ConnectionDAO;
import dao.CustomerDAO;
import dao.PaymentDAO;
import dao.PlanDAO;
import model.Bill;
import model.Connection;
import model.Customer;
import model.Payment;
import model.Plan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Simple console-based ISP Management System.
 *
 * This program uses JDBC to store and read data from a MySQL database.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final PlanDAO planDAO = new PlanDAO();
    private static final ConnectionDAO connectionDAO = new ConnectionDAO();
    private static final BillDAO billDAO = new BillDAO();
    private static final PaymentDAO paymentDAO = new PaymentDAO();

    public static void main(String[] args) {
        printBanner();
        while (true) {
            printMainMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> customerMenu();
                case 2 -> planMenu();
                case 3 -> connectionMenu();
                case 4 -> billingMenu();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void printBanner() {
        System.out.println("====================================");
        System.out.println("  ISP MANAGEMENT SYSTEM (Console)   ");
        System.out.println("====================================");
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("MAIN MENU");
        System.out.println("1) Customer Management");
        System.out.println("2) Internet Plan Management");
        System.out.println("3) Connection Management");
        System.out.println("4) Billing & Payments");
        System.out.println("0) Exit");
    }

    private static void customerMenu() {
        while (true) {
            System.out.println();
            System.out.println("CUSTOMER MENU");
            System.out.println("1) Add customer");
            System.out.println("2) View all customers");
            System.out.println("3) Update customer");
            System.out.println("4) Delete customer");
            System.out.println("0) Back");
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> viewCustomers();
                case 3 -> updateCustomer();
                case 4 -> deleteCustomer();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void planMenu() {
        while (true) {
            System.out.println();
            System.out.println("PLAN MENU");
            System.out.println("1) Add plan");
            System.out.println("2) View all plans");
            System.out.println("3) Update plan");
            System.out.println("4) Delete plan");
            System.out.println("0) Back");
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> addPlan();
                case 2 -> viewPlans();
                case 3 -> updatePlan();
                case 4 -> deletePlan();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void connectionMenu() {
        while (true) {
            System.out.println();
            System.out.println("CONNECTION MENU");
            System.out.println("1) Create connection request");
            System.out.println("2) View all connections");
            System.out.println("3) Change connection status");
            System.out.println("0) Back");
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> createConnection();
                case 2 -> viewConnections();
                case 3 -> changeConnectionStatus();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void billingMenu() {
        while (true) {
            System.out.println();
            System.out.println("BILLING & PAYMENT MENU");
            System.out.println("1) Generate monthly bill");
            System.out.println("2) View all bills");
            System.out.println("3) Record payment");
            System.out.println("4) View all payments");
            System.out.println("0) Back");
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> generateBill();
                case 2 -> viewBills();
                case 3 -> recordPayment();
                case 4 -> viewPayments();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void addCustomer() {
        System.out.println("\nADD NEW CUSTOMER");
        String name = readLine("Name: ");
        String address = readLine("Address: ");
        String phone = readLine("Phone: ");
        String email = readLine("Email: ");
        viewPlans();
        int planId = readInt("Select plan id (or 0 for none): ");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setPlanId(planId);

        if (customerDAO.addCustomer(customer)) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add customer.");
        }
    }

    private static void viewCustomers() {
        System.out.println("\nALL CUSTOMERS");
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer c : customers) {
            System.out.printf("ID: %d | Name: %s | Phone: %s | PlanID: %d\n", c.getCustomerId(), c.getName(), c.getPhone(), c.getPlanId());
        }
    }

    private static void updateCustomer() {
        System.out.println("\nUPDATE CUSTOMER");
        int customerId = readInt("Customer ID to update: ");
        Customer existing = customerDAO.getCustomerById(customerId);
        if (existing == null) {
            System.out.println("Customer not found.");
            return;
        }

        String name = readLine("New name (blank to keep): ");
        if (!name.isBlank()) {
            existing.setName(name);
        }
        String address = readLine("New address (blank to keep): ");
        if (!address.isBlank()) {
            existing.setAddress(address);
        }
        String phone = readLine("New phone (blank to keep): ");
        if (!phone.isBlank()) {
            existing.setPhone(phone);
        }
        String email = readLine("New email (blank to keep): ");
        if (!email.isBlank()) {
            existing.setEmail(email);
        }
        viewPlans();
        int planId = readInt("New plan id (0 to keep current): ");
        if (planId != 0) {
            existing.setPlanId(planId);
        }

        if (customerDAO.updateCustomer(existing)) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }

    private static void deleteCustomer() {
        System.out.println("\nDELETE CUSTOMER");
        int customerId = readInt("Customer ID to delete: ");
        if (customerDAO.deleteCustomer(customerId)) {
            System.out.println("Customer deleted.");
        } else {
            System.out.println("Failed to delete customer.");
        }
    }

    private static void addPlan() {
        System.out.println("\nADD NEW PLAN");
        String name = readLine("Plan name: ");
        int speed = readInt("Speed (Mbps): ");
        double price = readDouble("Price (₹): ");

        Plan plan = new Plan();
        plan.setPlanName(name);
        plan.setSpeed(speed);
        plan.setPrice(price);

        if (planDAO.addPlan(plan)) {
            System.out.println("Plan added successfully.");
        } else {
            System.out.println("Failed to add plan.");
        }
    }

    private static void viewPlans() {
        System.out.println("\nAVAILABLE PLANS");
        List<Plan> plans = planDAO.getAllPlans();
        if (plans.isEmpty()) {
            System.out.println("No plans found.");
            return;
        }
        for (Plan p : plans) {
            System.out.printf("ID: %d | %s | %d Mbps | ₹%.2f\n", p.getPlanId(), p.getPlanName(), p.getSpeed(), p.getPrice());
        }
    }

    private static void updatePlan() {
        System.out.println("\nUPDATE PLAN");
        int planId = readInt("Plan ID to update: ");
        Plan existing = planDAO.getPlanById(planId);
        if (existing == null) {
            System.out.println("Plan not found.");
            return;
        }

        String name = readLine("New plan name (blank to keep): ");
        if (!name.isBlank()) {
            existing.setPlanName(name);
        }
        String speedInput = readLine("New speed (blank to keep): ");
        if (!speedInput.isBlank()) {
            existing.setSpeed(Integer.parseInt(speedInput));
        }
        String priceInput = readLine("New price (blank to keep): ");
        if (!priceInput.isBlank()) {
            existing.setPrice(Double.parseDouble(priceInput));
        }

        if (planDAO.updatePlan(existing)) {
            System.out.println("Plan updated successfully.");
        } else {
            System.out.println("Failed to update plan.");
        }
    }

    private static void deletePlan() {
        System.out.println("\nDELETE PLAN");
        int planId = readInt("Plan ID to delete: ");
        if (planDAO.deletePlan(planId)) {
            System.out.println("Plan deleted.");
        } else {
            System.out.println("Failed to delete plan.");
        }
    }

    private static void createConnection() {
        System.out.println("\nCREATE CONNECTION REQUEST");
        viewCustomers();
        int customerId = readInt("Customer ID: ");
        viewPlans();
        int planId = readInt("Plan ID: ");

        Connection connection = new Connection();
        connection.setCustomerId(customerId);
        connection.setPlanId(planId);
        connection.setStatus("Pending");
        connection.setActivationDate(LocalDate.now().toString());

        if (connectionDAO.addConnection(connection)) {
            System.out.println("Connection request created (Pending).");
        } else {
            System.out.println("Failed to create connection.");
        }
    }

    private static void viewConnections() {
        System.out.println("\nALL CONNECTIONS");
        List<Connection> list = connectionDAO.getAllConnections();
        if (list.isEmpty()) {
            System.out.println("No connections found.");
            return;
        }
        for (Connection c : list) {
            System.out.printf("ID: %d | Customer: %d | Plan: %d | Status: %s | Activated: %s\n",
                    c.getConnectionId(), c.getCustomerId(), c.getPlanId(), c.getStatus(), c.getActivationDate());
        }
    }

    private static void changeConnectionStatus() {
        System.out.println("\nCHANGE CONNECTION STATUS");
        int connectionId = readInt("Connection ID: ");
        System.out.println("Statuses: Pending, Active, Suspended, Disconnected");
        String status = readLine("New status: ");
        if (connectionDAO.updateConnectionStatus(connectionId, status)) {
            System.out.println("Connection status updated.");
        } else {
            System.out.println("Failed to update connection status.");
        }
    }

    private static void generateBill() {
        System.out.println("\nGENERATE BILL");
        viewCustomers();
        int customerId = readInt("Customer ID for billing: ");
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        Plan plan = planDAO.getPlanById(customer.getPlanId());
        if (plan == null) {
            System.out.println("Customer does not have a plan assigned.");
            return;
        }

        Bill bill = new Bill();
        bill.setCustomerId(customerId);
        bill.setAmount(plan.getPrice());
        bill.setDueDate(LocalDate.now().plusDays(30).format(DateTimeFormatter.ISO_DATE));
        bill.setStatus("Pending");

        if (billDAO.addBill(bill)) {
            System.out.println("Bill generated: " + plan.getPlanName() + " - ₹" + plan.getPrice());
        } else {
            System.out.println("Failed to generate bill.");
        }
    }

    private static void viewBills() {
        System.out.println("\nALL BILLS");
        List<Bill> list = billDAO.getAllBills();
        if (list.isEmpty()) {
            System.out.println("No bills found.");
            return;
        }
        for (Bill b : list) {
            System.out.printf("ID: %d | Customer: %d | Amount: ₹%.2f | Due: %s | Status: %s\n",
                    b.getBillId(), b.getCustomerId(), b.getAmount(), b.getDueDate(), b.getStatus());
        }
    }

    private static void recordPayment() {
        System.out.println("\nRECORD PAYMENT");
        viewBills();
        int billId = readInt("Bill ID to pay: ");
        Bill bill = billDAO.getBillById(billId);
        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        Payment payment = new Payment();
        payment.setBillId(billId);
        payment.setAmount(bill.getAmount());
        payment.setPaymentDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        payment.setStatus("Completed");

        if (paymentDAO.addPayment(payment) && billDAO.updateBillStatus(billId, "Paid")) {
            System.out.println("Payment recorded and bill marked as Paid.");
        } else {
            System.out.println("Failed to record payment.");
        }
    }

    private static void viewPayments() {
        System.out.println("\nALL PAYMENTS");
        List<Payment> list = paymentDAO.getAllPayments();
        if (list.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }
        for (Payment p : list) {
            System.out.printf("ID: %d | Bill: %d | Amount: ₹%.2f | Date: %s | Status: %s\n",
                    p.getPaymentId(), p.getBillId(), p.getAmount(), p.getPaymentDate(), p.getStatus());
        }
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter a number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Please enter a number: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
