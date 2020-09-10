package com.crystal.repository;

import com.crystal.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readerid,String borrowtime,String returntime);
    public List<Borrow> findAllByReaderId(Integer id, Integer index, Integer limit);
    public int count(Integer readerid);
    public int countByState(Integer state);
    public List<Borrow> findAllByState(Integer state, Integer index, Integer limit);

    public void handleBorrow(Integer borrowid, Integer state, Integer adminid);
}
