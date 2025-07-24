import java.time.LocalDate;

public class Book implements Borrowable {
    private int id;
    private String name;
    private Author author;
    private String status;
    private double price;
    private Reader currentOwner;
    private LocalDate dateOfPurchase;

    public Book(int id, String name, Author author, double price, LocalDate dateOfPurchase) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price= price;
        this.dateOfPurchase = dateOfPurchase;
        this.status = "available";
        this.currentOwner = null;

        author.addBook(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public Reader getCurrentOwner() {
        return currentOwner;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void changeOwner(Reader reader){
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
