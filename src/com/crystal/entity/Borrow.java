package com.crystal.entity;

public class Borrow {
    private Integer id;
    private Book book;
    private Reader reader;
    private String borrowtime;
    private String returntime;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Borrow(Integer id, Book book, Reader reader, String borrowtime, String returntime, Integer state) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.borrowtime = borrowtime;
        this.returntime = returntime;
        this.state = state;
    }

    public Borrow() {}

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                '}';
    }
}
