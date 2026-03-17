package com.librokeep.ui;

import com.librokeep.dao.BookDAO;
import com.librokeep.dao.BorrowerDAO;
import com.librokeep.dao.UserDAO;
import com.librokeep.model.Book;
import com.librokeep.model.User;

import java.util.List;
import java.util.Scanner;

public class Main {

    static String currentUser; // 🔥 logged-in user
    static Scanner sc = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static BookDAO bookDAO = new BookDAO();
    static BorrowerDAO borrowerDAO = new BorrowerDAO();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");   
            

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    loginFlow();
                    break;

                case 2:
                    registerFlow();
                    break;

                case 3:
                    System.out.println("Exiting Application...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= REGISTER =================
    public static void registerFlow() {

        System.out.println("\n===== REGISTER =====");

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        userDAO.registerUser(username, password);
    }

    // ================= LOGIN =================
    public static void loginFlow() {

        System.out.println("\n===== LOGIN =====");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userDAO.authenticate(username, password);

        if (user == null) {
            System.out.println("❌ Invalid login!");
            return;
        }

        // 🔥 store logged-in user
        currentUser = user.getUsername();

        System.out.println("✅ Welcome " + currentUser + " (" + user.getRole() + ")");

        if (user.getRole().equals("LIBRARIAN")) {
            librarianMenu();
        } else {
            customerMenu();
        }
    }

    // ================= LIBRARIAN =================
    public static void librarianMenu() {
        while (true) {
            System.out.println("\n===== LIBRARIAN MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book Quantity");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");

            try {
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Enter Book ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();

                        System.out.print("Enter Quantity: ");
                        int qty = Integer.parseInt(sc.nextLine());

                        bookDAO.addBook(new Book(id, title, qty));
                        break;

                    case 2:
                        List<Book> books = bookDAO.getAll();
                        books.forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter Book ID: ");
                        int uid = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter New Quantity: ");
                        int uqty = Integer.parseInt(sc.nextLine());

                        bookDAO.updateBookQty(uid, uqty);
                        break;

                    case 4:
                        System.out.print("Enter Book ID: ");
                        int did = Integer.parseInt(sc.nextLine());

                        bookDAO.deleteBook(did);
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Invalid input!");
            }
        }
    }

    // ================= CUSTOMER =================
    public static void customerMenu() {
        while (true) {
            System.out.println("\n===== CUSTOMER MENU =====");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");   
            System.out.println("4. My Borrowed Books");
            System.out.println("5. Exit");

            try {
                System.out.print("Enter choice: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        List<Book> books = bookDAO.getAll();
                        books.forEach(System.out::println);
                        break;

                    case 2:
                        System.out.print("Enter Book ID: ");
                        int bid = Integer.parseInt(sc.nextLine());

                        // 🔥 use logged-in user
                        borrowerDAO.borrowBook(currentUser, bid);
                        break;

                    case 3:
                        System.out.print("Enter Book ID: ");
                        int rb = Integer.parseInt(sc.nextLine());

                        // 🔥 return using username + book
                        borrowerDAO.returnBook(currentUser, rb);
                        break;
                    case 4:
                        borrowerDAO.viewMyBooks(currentUser);
                        break;
                        
                    case 5:
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Invalid input!");
            }
        }
    }
}