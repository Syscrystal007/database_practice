package com.crystal.repository;
import com.crystal.entity.Book;
import com.crystal.entity.Storage;

import java.util.List;

public interface BookRepository {
    public Book findById(Integer id);
    public List<Book> findAll(int index,int limit);
    public int count();
    public void add(Book book);
    public void deleteById(Integer id);
    public void modifyById(Integer id,Book book);

    public List<Book> getTop(Integer num,Integer level);

    public List<Storage> getAllStorage();
    public Storage getStorageById(Integer id);

}
