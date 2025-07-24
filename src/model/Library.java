package model;


import java.util.*;

public class Library {
    private Map<String, Book> books;
    private Map<String, model.Reader> readers;
    private Map<String, model.MemberRecord> memberRecords;
    private List<model.Invoice> invoices;

    public Library() {
        this.books = new HashMap<>();
        this.readers = new HashMap<>();
        this.memberRecords = new HashMap<>();
        this.invoices = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public Book getBookById(String id) {
        return books.get(id);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getName().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    public void deleteBook(String bookId) {
        books.remove(bookId);
    }

    public void addReader(model.Reader reader) {
        readers.put(reader.getName(), reader);
        memberRecords.put(reader.getName(), new model.MemberRecord(reader, java.time.LocalDate.now()));
    }

    public Reader getReader(String name) {
        return readers.get(name);
    }

    public model.MemberRecord getMemberRecord(String name) {
        return memberRecords.get(name);
    }

    public void addInvoice(model.Invoice invoice) {
        invoices.add(invoice);
    }

    public List<model.Invoice> getInvoices() {
        return invoices;
    }
}


