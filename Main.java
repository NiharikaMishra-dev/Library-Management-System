package Library_Management_System;

import Library_Management_System.*;

import java.util.*;
import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        StudentDAO studentDAO = new StudentDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Register Student");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Overdue Books");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter number of copies: ");
                        int copies = scanner.nextInt();
                        scanner.nextLine();

                        Book book = new Book(0, title, author, copies);
                        bookDAO.addBook(book);
                        System.out.println("✅ Book added.");
                        break;

                    case 2:
                        System.out.print("Enter keyword: ");
                        String keyword = scanner.nextLine();
                        List<Book> foundBooks = bookDAO.searchBooks(keyword);
                        System.out.println("📚 Found Books:");
                        for (Book b : foundBooks) {
                            System.out.println(b.getId() + ": " + b.getTitle() + " by " + b.getAuthor());
                        }
                        break;

                    case 3:
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        Student student = new Student(0, name, email);
                        studentDAO.addStudent(student);
                        System.out.println("✅ Student registered.");
                        break;

                    case 4:
                        System.out.print("Enter book ID: ");
                        int bookId = scanner.nextInt();
                        System.out.print("Enter student ID: ");
                        int studentId = scanner.nextInt();
                        scanner.nextLine();

                        if (bookDAO.findBookById(bookId) == null || studentDAO.findStudentById(studentId) == null) {
                            System.out.println("❌ Book or student not found.");
                            break;
                        }

                        Transaction trans = new Transaction(0, bookId, studentId, new Date(), null);
                        transactionDAO.issueBook(trans);
                        System.out.println("✅ Book issued.");
                        break;

                    case 5:
                        System.out.print("Enter transaction ID: ");
                        int tid = scanner.nextInt();
                        transactionDAO.returnBook(tid, new Date());
                        System.out.println("✅ Book returned.");
                        break;

                    case 6:
                        List<Transaction> allTrans = transactionDAO.getAllTransactions();
                        Date now = new Date();
                        System.out.println("⚠️ Overdue Transactions:");
                        for (Transaction t : allTrans) {
                            if (t.getReturnDate() == null) {
                                long diff = now.getTime() - t.getIssueDate().getTime();
                                if (diff > 14L * 24 * 60 * 60 * 1000) {
                                    System.out.println("Transaction ID " + t.getId() + " is overdue.");
                                }
                            }
                        }
                        break;

                    case 7:
                        System.out.println("👋 Goodbye!");
                        return;

                    default:
                        System.out.println("❌ Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("🚫 Error: " + e.getMessage());
            }
        }
    }
}
