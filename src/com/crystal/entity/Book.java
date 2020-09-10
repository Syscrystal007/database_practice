package com.crystal.entity;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publish;
    private Integer pages;
    private Double price;
    private BookCase bookCase;
    private Storage storage;
    private Integer borrowTimes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookCase getBookCase() {
        return bookCase;
    }

    public void setBookcase(BookCase bookcase) {
        this.bookCase = bookCase;
    }

    public void setBookCase(BookCase bookCase) {
        this.bookCase = bookCase;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Integer getBorrowTimes() {
        return borrowTimes;
    }

    public void setBorrowTimes(Integer borrowTimes) {
        this.borrowTimes = borrowTimes;
    }

    public Book(Integer id, String name, String author, String publish, Integer pages, Double price, BookCase bookcase) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookCase = bookCase;
    }

    public Book(Integer id, String name, BookCase bookCase, Storage storage) {
        this.id = id;
        this.name = name;
        this.bookCase = bookCase;
        this.storage = storage;
    }

    public Book(Integer id, String name, String author, String publish, Integer borrowTimes) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.borrowTimes = borrowTimes;
    }

    public Book(String name, String author, String publish) {
        this.name = name;
        this.author = author;
        this.publish = publish;
    }

    public Book() {}

}
