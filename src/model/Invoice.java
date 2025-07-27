package model;

import model.Book;
import model.Reader;
import java.time.LocalDate;
import java.util.UUID;

public class Invoice {
    private String id;
    private Reader reader;
    private Book book;
    private double amount;
    private LocalDate date;
    private boolean refunded;

    public Invoice(Reader reader, Book book, double amount) {
        this.id = UUID.randomUUID().toString();
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        this.date = LocalDate.now();
        this.refunded = false;
    }

    public void refund() {
        this.refunded = true;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDate() {
        return date;
    }
}

