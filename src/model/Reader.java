package model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends model.Person {
    private List<Book> books;
    private List<Book> borrowedBooks;
    public Reader(int id, String name) {
        super(id, name);
        this.books = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void purchaseBook(Book book) {
        books.add(book);
        book.changeOwner(this);

    }


    public String whoYouAre(){
        return "Reader";
    }
}
