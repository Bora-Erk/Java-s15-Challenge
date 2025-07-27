package app;

import model.*;
import service.BookService;

import java.time.LocalDate;
import model.Reader;
import model.Book;
import model.Invoice;
import model.Library;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();
    private static final BookService bookService = new BookService(library);

    public static void main(String[] args) {
        System.out.println("KÜTÜPHANE OTOMASYON SİSTEMİ");

        while (true) {
            showMainMenu();
            int choice = readInt("Seçiminiz: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> deleteBook();
                case 4 -> listBooksByCategory();
                case 5 -> listBooksByAuthor();
                case 6 -> borrowBook();
                case 7 -> returnBook();
                case 8 -> System.out.println("Çıkış yapılıyor...");
                default -> System.out.println("Geçersiz seçim!");
            }

            if (choice == 8) break;
        }
    }

    private static void showMainMenu() {
        System.out.println("""
                \n--- ANA MENÜ ---
                1. Kitap Ekle
                2. Kitap Ara
                3. Kitap Sil
                4. Kategoriye Göre Kitapları Listele
                5. Yazara Göre Kitapları Listele
                6. Kitap Ödünç Al
                7. Kitap Geri Teslim Et
                8. Çıkış
                """);
    }

    private static void addBook() {
        System.out.println("\n--- Kitap Ekle ---");
        String title = readLine("Kitap adı: ");
        String category = readLine("Kategori: ");
        String authorName = readLine("Yazar adı: ");

        Author author = new Author(authorName);
        Book book = new Book(UUID.randomUUID().toString(), title, author, category, LocalDate.now());

        bookService.addBook(book);
        System.out.println("Kitap başarıyla eklendi!");
    }

    private static void searchBook() {
        System.out.println("\n--- Kitap Ara ---");
        String title = readLine("Kitap adının tamamı ya da parçası: ");
        List<Book> results = bookService.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("Sonuç bulunamadı.");
        } else {
            results.forEach(Main::printBook);
        }
    }

    private static void deleteBook() {
        System.out.println("\n--- Kitap Sil ---");
        String id = readLine("Silinecek kitabın ID'si: ");
        bookService.deleteBook(id);
        System.out.println("🗑️ Kitap silindi (Eğer varsa).");
    }

    private static void listBooksByCategory() {
        System.out.println("\n--- Kategoriye Göre Listele ---");
        String category = readLine("Kategori adı: ");
        List<Book> books = bookService.searchByCategory(category);

        if (books.isEmpty()) {
            System.out.println("Bu kategoriye ait kitap bulunamadı.");
        } else {
            books.forEach(Main::printBook);
        }
    }

    private static void listBooksByAuthor() {
        System.out.println("\n--- Yazara Göre Listele ---");
        String authorName = readLine("Yazar adı: ");
        List<Book> books = bookService.searchByAuthor(authorName);

        if (books.isEmpty()) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        } else {
            books.forEach(Main::printBook);
        }
    }

    private static void borrowBook() {
        System.out.println("\n--- Kitap Ödünç Al ---");
        String readerName = readLine("Kullanıcı adı: ");
        String bookId = readLine("Kitap ID: ");

        Reader reader = library.getReader(readerName);
        if (reader == null) {
            reader = new Reader(1, readerName);
            library.addReader(reader);
        }

        Book book = library.getBookById(bookId);
        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        MemberRecord record = library.getMemberRecord(readerName);
        if (!record.canBorrowMoreBooks()) {
            System.out.println("Maksimum kitap limitine ulaşıldı!");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Kitap şu anda ödünç alınmış.");
            return;
        }

        book.changeOwner(reader);
        book.updateStatus("borrowed");
        reader.borrowBook(book);
        record.incBooksIssued();

        Invoice invoice = new Invoice(reader, book, 10.0);
        library.addInvoice(invoice);

        System.out.println("Kitap ödünç alındı. Fatura No: " + invoice.getId());
    }

    private static void returnBook() {
        System.out.println("\n--- Kitap İade Et ---");
        String readerName = readLine("Kullanıcı adı: ");
        String bookId = readLine("Kitap ID: ");

        Reader reader = library.getReader(readerName);
        Book book = library.getBookById(bookId);

        if (reader == null || book == null || !reader.getBorrowedBooks().contains(book)) {
            System.out.println("İade işlemi geçersiz.");
            return;
        }

        book.updateStatus("available");
        book.changeOwner(null);
        reader.returnBook(book);
        MemberRecord record = library.getMemberRecord(readerName);
        record.decBooksIssued();

        for (Invoice invoice : library.getInvoices()) {
            if (invoice.getReader() == reader && invoice.getBook() == book && !invoice.isRefunded()) {
                invoice.refund();
                System.out.println("İade işlemi tamamlandı. Fatura No: " + invoice.getId());
                break;
            }
        }

        System.out.println("Kitap başarıyla iade edildi.");
    }

    private static void printBook(Book book) {
        System.out.printf("[%s] %s - %s (%s) Durum: %s\n",
                book.getId(), book.getName(), book.getAuthor().getName(), book.getCategory(), book.getStatus());
    }

    private static int readInt(String label) {
        System.out.print(label);
        while (!scanner.hasNextInt()) {
            System.out.print("Lütfen sayı giriniz: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static String readLine(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }
}

