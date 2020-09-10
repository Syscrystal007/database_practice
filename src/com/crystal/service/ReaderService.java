package com.crystal.service;

import com.crystal.entity.Reader;

import java.util.List;

public interface ReaderService {
    public void addReader(Reader reader);
    public void deleteById(Integer id);
    public void modifyById(Integer id,Reader reader);
    //public int countOnline();

    public List<Reader> findAllReader();

    public int checkValidById(Integer id);
    public List<Reader> getReaderByState(Integer state);
}
