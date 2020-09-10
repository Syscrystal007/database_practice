package com.crystal.entity;

public class Storage {
    private Book book;
    private Integer total;
    private Integer lent;
    private Integer lost;
    private Integer availble;

    //available=total-lent-lost

    public Storage(Book book, Integer total, Integer lent, Integer lost) {
        this.book = book;
        this.lent = lent;
        this.lost = lost;
        this.total = total;
        availble = total-lent-lost;
        if(availble<0) availble=0;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getLent() {
        return lent;
    }

    public void setLent(Integer lent) {
        this.lent = lent;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
