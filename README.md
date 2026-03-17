# 📚 Library Management System

A **role-based Library Management System** built using **Java, JDBC, and MySQL**, designed to efficiently manage book inventory and user transactions with real-world constraints.

---

## 🚀 Features

### 🔐 Authentication System
- User Registration
- Secure Login
- Role-based access:
  - 👨‍💼 Librarian
  - 👤 Customer

---

### 📚 Librarian Features
- ➕ Add new books  
- 📖 View all books  
- 🔄 Update book quantity  
- ❌ Delete books  

---

### 👤 Customer Features
- 📖 View available books  
- 📥 Borrow books  
- 📤 Return books  
- 📊 View borrowed books  

---

### ⚡ Smart System Logic
- ❌ Prevent borrowing when stock = 0  
- ❌ Prevent duplicate borrowing  
- ✅ Auto update stock (borrow ↓ / return ↑)  
- ✅ User-specific operations (no manual name input)  

---

## 🏗️ Project Structure

```
com.librokeep
│
├── config
│   └── DBConnection.java
│
├── dao
│   ├── BookDAO.java
│   ├── UserDAO.java
│   ├── BorrowerDAO.java
│
├── model
│   ├── Book.java
│   ├── User.java
│
├── ui
│   └── Main.java
```

---

## 🧠 System Architecture

### 🔹 Layered Design

| Layer | Description |
|------|------------|
| UI Layer | Handles user interaction (Main.java) |
| DAO Layer | Handles database operations |
| Model Layer | Represents entities (Book, User) |
| Database | MySQL relational database |

---

## 🧩 Modules Description

### 1️⃣ Authentication Module
- Handles login & registration  
- First user becomes **LIBRARIAN**  
- Others become **CUSTOMERS**  

---

### 2️⃣ Librarian Module
- Full control over book inventory  
- Performs CRUD operations  

---

### 3️⃣ Customer Module
- Borrow & return books  
- View personal borrowed books  

---

### 4️⃣ Transaction Module
- Handles borrow/return logic  
- Ensures data synchronization between tables  

---

### 5️⃣ DAO Module
- Executes SQL queries using JDBC  
- Uses PreparedStatements (prevents SQL injection)  

---

### 6️⃣ Validation Module
- Prevents invalid operations:
  - Borrowing unavailable books  
  - Duplicate borrow  
  - Invalid inputs  

---

## 🗄️ Database Design

### 📌 Database Name: `librokeep`

---

### 📘 Books Table

| Column | Type | Description |
|-------|------|------------|
| bid | INT | Book ID (Primary Key) |
| title | VARCHAR(100) | Book title |
| qty | INT | Available quantity |

---

### 👤 Users Table

| Column | Type | Description |
|-------|------|------------|
| uid | INT | User ID (Auto Increment) |
| username | VARCHAR(50) | Unique username |
| password | VARCHAR(255) | User password |
| role | ENUM | LIBRARIAN / CUSTOMER |

---

### 🔄 Borrowers Table

| Column | Type | Description |
|-------|------|------------|
| tid | INT | Transaction ID |
| customer_name | VARCHAR(100) | Username |
| book_id | INT | Book reference |
| borrowed_time | TIMESTAMP | Borrow time |

---

## 🔗 Relationships

- 📘 One Book → Many Borrow Records (1:M)  
- 👤 One User → Many Transactions  

---

## 🛠️ SQL Setup

```sql
CREATE DATABASE librokeep;

USE librokeep;

CREATE TABLE books (
    bid INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    qty INT NOT NULL
);

CREATE TABLE users (
    uid INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    role ENUM('LIBRARIAN','CUSTOMER')
);

CREATE TABLE borrowers (
    tid INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    book_id INT,
    borrowed_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES books(bid)
);
```

---

## ▶️ How to Run

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.librokeep.ui.Main"
```

---

## 🧪 Sample Flow

### 🔐 Login
```
Username: user  
Password: user123  
```

---

### 📚 Borrow Book
```
Enter Book ID: 101  
Book Borrowed Successfully!  
```

---

### 🔁 Return Book
```
Enter Book ID: 101  
Book Returned Successfully!  
```

---

## 🎯 Key Highlights

✔ Role-based system  
✔ Real-world validation  
✔ Database synchronization  
✔ Clean architecture (DAO pattern)  
✔ Secure query handling  

---

## 🔮 Future Enhancements

- 🔍 Search books (LIKE query)  
- 💰 Fine calculation system  
- 🌐 Web-based UI (Spring Boot / React)  
- 📊 Admin analytics dashboard  

---

## 👨‍💻 Authors

- G. Chandan  
- R. Greeshmanth  
- R. Likhith  
- N. Charan  

---

## 📌 Conclusion

This project demonstrates a **real-world database-driven application** using Java and MySQL, focusing on **data integrity, role-based access, and system reliability**.