package model;

import java.util.ArrayList;
import java.util.List;

public class Author extends model.Person {
    private List<Book> books;
    public Author(int id, String name) {
        super(id, name);
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getBooks(){
        return books;
    }

    public String whoYouAre(){
        return "Author";
    }
}
