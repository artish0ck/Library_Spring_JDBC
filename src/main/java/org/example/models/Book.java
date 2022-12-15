package org.example.models;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;
    private Integer holderId;
    @NotEmpty(message = "Book title can`t be empty")
    @Size(min = 2, max = 150, message = "Book title should be from 2 to 200 symbols long")
    private String bookTitle;

    @NotEmpty(message = "Book author field can`t be empty")
    @Size(min = 2, max = 150, message = "Book author should be from 2 to 200 symbols long")
    private String bookAuthor;

    //    @Digits(integer=4, fraction=0, message = "Publication year should be digits")
    private int bookPublicationYear;


    public Book() {
    }

    public Book(int bookId, Integer holderId, String bookTitle, String bookAuthor, int bookPublicationYear) {
        this.bookId = bookId;
        this.holderId = holderId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublicationYear = bookPublicationYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Integer getHolderId() {
        return holderId;
    }

    public void setHolderId(Integer holderId) {
        this.holderId = holderId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPublicationYear() {
        return bookPublicationYear;
    }

    public void setBookPublicationYear(int bookPublicationYear) {
        this.bookPublicationYear = bookPublicationYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", holderId=" + holderId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublicationYear=" + bookPublicationYear +
                '}';
    }
}
