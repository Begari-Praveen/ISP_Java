# ISP Management System (Java + MySQL)

This project is a simple **ISP Management System** written in **Java** that uses **MySQL** for data storage.

The system simulates how real companies like **Jio**, **Airtel**, and **BSNL** manage:

- Customers
- Internet plans
- Connections (Pending / Active / Suspended / Disconnected)
- Monthly bills
- Payments

It is built as a console application and uses **JDBC** to talk to the MySQL database.

---

## What Is an ISP?

An **ISP (Internet Service Provider)** is a company that gives people and businesses access to the internet.

Examples:

- **Jio**
- **Airtel**
- **BSNL**

They provide services like:

- Internet connections
- WiFi
- Broadband
- Fiber (high-speed) connections

To manage thousands of customers, these companies use software called an **ISP Management System**.

---

##  What an ISP Management System Does

This system helps companies manage:

- **Customers** (name, phone, email, address)
- **Internet plans** (speed and price)
- **Connections** (install or cancel service)
- **Monthly bills** (amount due)
- **Payments** (when customers pay their bills)

It keeps everything organized so the company can see who is using what service, who needs billing, and who has paid.

---

##  Project Features

### Customer Management

Admin can:

- Add a customer
- View all customers
- Update a customer's details
- Delete a customer

Customer details include:

- Customer ID
- Name
- Address
- Phone number
- Email

### Internet Plan Management

Admin can:

- Add new plans
- View all plans
- Update plans
- Delete plans

Example plans:

| Plan Name | Speed   | Price |
|----------|--------|------|
| Basic    | 50 Mbps | ₹499 |
| Standard | 100 Mbps | ₹799 |
| Premium  | 300 Mbps | ₹1499 |

### Connection Management

When a customer chooses a plan, a **connection request** is created.

Connection statuses:

- Pending
- Active
- Suspended
- Disconnected

Admin can:

- Approve connections
- Suspend connections
- Disconnect connections

### Billing System

Each month the system can generate a bill for a customer.

A bill includes:

- Bill ID
- Customer ID
- Amount
- Due date
- Status (Pending / Paid)

### Payment Management

Customers pay their bill. The system records:

- Payment ID
- Bill ID
- Amount
- Payment date
- Status (Completed / Pending / Failed)

Admin can see payment history.

---

##  Database Design (MySQL)

Tables:

- `customers`
- `plans`
- `connections`
- `bills`
- `payments`

Use the file `database.sql` to create the database and tables.

---

## Project Folder Structure

```
ISP-Management-System
│
├── src
│   ├── model
│   │   ├── Customer.java
│   │   ├── Plan.java
│   │   ├── Connection.java
│   │   ├── Bill.java
│   │   └── Payment.java
│   
│   ├── dao
│   │   ├── CustomerDAO.java
│   │   ├── PlanDAO.java
│   │   ├── ConnectionDAO.java
│   │   ├── BillDAO.java
│   │   └── PaymentDAO.java
│   
│   ├── database
│   │   └── DBConnection.java
│   
│   └── main
│       └── Main.java
│
└── database.sql
```

---

##  How to Run (Windows)

### 1) Install MySQL

Install MySQL Server and create a user (example uses `root` / `password`).

### 2) Create Database & Tables

Run the SQL script:

```sh
mysql -u root -p < database.sql
```

### 3) Update DB Connection

Open `src/database/DBConnection.java` and set:

- `URL` (database URL)
- `USERNAME`
- `PASSWORD`

### 4) Compile and Run

From the root folder:

```sh
javac -d out src\main\Main.java src\model\*.java src\dao\*.java src\database\DBConnection.java
java -cp out main.Main
```

---

##  Workflow (What Happens Step by Step)

1. Admin creates internet plans (Basic / Standard / Premium).
2. Customer registers with name, phone, email.
3. Customer selects a plan.
4. A connection request is created (Pending).
5. Admin approves the connection (status becomes Active).
6. System generates a monthly bill.
7. Customer pays the bill.
8. System records the payment.

---

##  Example Output

When running `Main`, you will see menus like:

```
ISP MANAGEMENT SYSTEM (Console)

MAIN MENU
1) Customer Management
2) Internet Plan Management
3) Connection Management
4) Billing & Payments
0) Exit
```

If you choose `1` and add a customer, it prints:

```
Customer added successfully.
```

---

##  What This Project Shows on a Resume

- Java programming
- Object-oriented design
- Database design (MySQL)
- JDBC (Java database connection)
- Backend system workflows (customers, billing, payments)

---

##  Extra Features You Can Add (Advanced)

- Admin login (username/password)
- Search customers by name
- Filter connections by status
- View unpaid bills only
- Generate reports (PDF/CSV)
- Web interface (Servlets/JSP or JavaFX)

---

If you'd like, I can also help you add a simple web interface (Servlet + JSP) or a desktop UI using Java Swing.
