package model;

import java.time.LocalDate;

public class Book implements model.Borrowable {
    private String id;
    private String name;
    private model.Author author;
    private String status;
    private String category;
    private double price;
    private model.Reader currentOwner;
    private LocalDate dateOfPurchase;

    public Book(String id, String name, model.Author author, String category, double price, LocalDate dateOfPurchase) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price= price;
        this.category = category;
        this.dateOfPurchase = dateOfPurchase;
        this.status = "available";
        this.currentOwner = null;

        author.addBook(this);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public model.Author getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public model.Reader getCurrentOwner() {
        return currentOwner;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void changeOwner(model.Reader reader){
        this.currentOwner = reader;
    }

    public void updateStatus(String status){
        this.status = status;
    }

    public String display() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", currentOwner=" + currentOwner +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
