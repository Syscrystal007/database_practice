package com.crystal.entity;

import java.util.List;

public class BookCase {
    private Integer id;
    private String name;
    private List<Book> books;   //一对多关系

    public BookCase(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookCase() {}

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
