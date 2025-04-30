package Library_Management_System;

import java.util.Date;

public class Transaction {
    private int id;
    private int bookId;
    private int studentId;
    private Date issueDate;
    private Date returnDate;

    public Transaction(int id, int bookId, int studentId, Date issueDate, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


// Getters and Setters
}

