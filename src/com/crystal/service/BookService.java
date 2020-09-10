package com.crystal.service;

import com.crystal.entity.Book;
import com.crystal.entity.Borrow;
import com.crystal.entity.Storage;

import java.util.List;

public interface BookService {
    public Book findById(Integer id);
    public List<Book> findAll(int page);
    public int getPages();
    public int getBorrowPages(Integer readerid);
    public int getBorrowPagesByState(Integer state);
    public void addBorrow(Integer bookid,Integer readerid);
    public List<Borrow> findAllBorrowByReaderId(Integer readerid, Integer page);
    public List<Borrow> findAllBorrowByState(Integer state,Integer page);

    public void handleBorrow(Integer borrowid, Integer state, Integer adminid);

    public void addBook(Book book);
    public void deleteById(Integer id);
    public void modifyById(Integer id,Book book);

    public List<Book> getTop(Integer num,Integer level);

    public List<Storage> getAllStorage();
    public Storage getStorageById(Integer id);


}
