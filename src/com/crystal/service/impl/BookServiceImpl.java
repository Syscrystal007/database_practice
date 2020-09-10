package com.crystal.service.impl;

import com.crystal.entity.Book;
import com.crystal.entity.Borrow;
import com.crystal.entity.Storage;
import com.crystal.repository.BookRepository;
import com.crystal.repository.BorrowRepository;
import com.crystal.repository.impl.BookRepositoryImpl;
import com.crystal.repository.impl.BorrowRepositoryImpl;
import com.crystal.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    private final int LIMIT=10;

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll(int page) {
        int idx=(page-1)*LIMIT;
        List<Book> list = bookRepository.findAll(idx,LIMIT);
        return list;
    }
    public int getPages(){
        int cnt=bookRepository.count();
        int page=cnt/LIMIT;
        if(cnt%LIMIT!=0)    page++;
        return page;
    }

    @Override
    public int getBorrowPages(Integer readerid) {
        int cnt=borrowRepository.count(readerid);
        int page=cnt/LIMIT;
        if(cnt%LIMIT!=0)    page++;
        return page;
    }

    @Override
    public int getBorrowPagesByState(Integer state) {
        int cnt = borrowRepository.countByState(state);
        int page=cnt/LIMIT;
        if(cnt%LIMIT!=0)    page++;
        return page;
    }

    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        //借书时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime= dateFormat.format(date);

        //还书时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+14);   //声明类型，再传入
        Date date2 = calendar.getTime();
        String returnTime = dateFormat.format(date2);

        borrowRepository.insert(bookid,readerid,borrowTime,returnTime);
    }

    @Override
    public List<Borrow> findAllBorrowByReaderId(Integer readerid, Integer page) {
        //业务将page换成index和limit
        Integer index = LIMIT*(page-1);
        return borrowRepository.findAllByReaderId(readerid,index,LIMIT);
    }

    @Override
    public List<Borrow> findAllBorrowByState(Integer state,Integer page) {
        Integer index = LIMIT*(page-1);
        return borrowRepository.findAllByState(state,index,LIMIT);
    }

    @Override
    public void handleBorrow(Integer borrowid, Integer state, Integer adminid) {
        borrowRepository.handleBorrow(borrowid,state,adminid);
    }

    @Override
    public void addBook(Book book) {
        bookRepository.add(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void modifyById(Integer id, Book book) {
        bookRepository.modifyById(id,book);
    }

    public List<Book> getTop(Integer num,Integer level){return bookRepository.getTop(num,level);}


    @Override
    public List<Storage> getAllStorage() {
        return bookRepository.getAllStorage();
    }

    @Override
    public Storage getStorageById(Integer id) {
        return bookRepository.getStorageById(id);
    }


}
