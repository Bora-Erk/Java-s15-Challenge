package service;

import model.*;

import java.util.List;

public class BookService {
    private Library library;

    public BookService(Library library) {
        this.library = library;
    }

    public void addBook(Book book) {
        library.addBook(book);
    }

    public Book getBookById(String id) {
        return library.getBookById(id);
    }

    public List<Book> searchByTitle(String title) {
        return library.searchByTitle(title);
    }

    public List<Book> searchByAuthor(String authorName) {
        return library.searchByAuthor(authorName);
    }

    public List<Book> searchByCategory(String category) {
        return library.searchByCategory(category);
    }

    public void deleteBook(String bookId) {
        library.deleteBook(bookId);
    }

    public void updateBook(String id, String newTitle, String newCategory) {
        Book book = library.getBookById(id);
        if (book != null) {
            book.updateStatus("available"); // örnek: basit güncelleme
        }
    }
}
