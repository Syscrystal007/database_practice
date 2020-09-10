package com.crystal.repository;

import com.crystal.entity.Reader;

import java.util.List;

public interface ReaderRepository {
    public Reader login(String username,String password);
    public boolean register(Reader reader);
    public List<Reader> findAll();
    public void add(Reader reader);
    public void deleteById(Integer id);
    public void modifyById(Integer id,Reader reader);

    public Reader findById(Integer id);
    public List<Reader> findByName(String name);

    public int getStateById(Integer id);
    public List<Reader> getReaderByState(Integer state);
    public void delExpired();

}
