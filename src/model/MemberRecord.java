package model;

import java.io.Reader;
import java.time.LocalDate;

public class MemberRecord {
    private String memberId;
    private String type; // Örn: "standard", "premium"
    private String name;
    private String address;
    private String phoneNo;

    private Reader member;
    private LocalDate dateOfMembership;
    private int numBooksIssued;
    private final int MAX_BOOK_LIMIT = 5;

    public MemberRecord(String memberId, String type, String name, String address, String phoneNo, Reader member, LocalDate dateOfMembership) {
        this.memberId = memberId;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.member = member;
        this.dateOfMembership = dateOfMembership;
        this.numBooksIssued = 0;
    }

    public MemberRecord(model.Reader reader, LocalDate now) {
    }

    // Getters and Setters
    public String getMemberId() {
        return memberId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Reader getMember() {
        return member;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getNumBooksIssued() {
        return numBooksIssued;
    }

    public int getMaxBookLimit() {
        return MAX_BOOK_LIMIT;
    }

    public boolean canBorrowMoreBooks() {
        return numBooksIssued < MAX_BOOK_LIMIT;
    }

    public void incBooksIssued() {
        if (canBorrowMoreBooks()) {
            numBooksIssued++;
        }
    }

    public void decBooksIssued() {
        if (numBooksIssued > 0) {
            numBooksIssued--;
        }
    }

    @Override
    public String toString() {
        return "Üye ID: " + memberId + ", İsim: " + name + ", Tür: " + type +
                ", Telefon: " + phoneNo + ", Adres: " + address +
                ", Kitap Sayısı: " + numBooksIssued + "/" + MAX_BOOK_LIMIT;
    }
}

