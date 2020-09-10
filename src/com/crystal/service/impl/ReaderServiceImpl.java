package com.crystal.service.impl;

import com.crystal.entity.Reader;
import com.crystal.repository.ReaderRepository;
import com.crystal.repository.impl.ReaderRepositoryImpl;
import com.crystal.service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    @Override
    public void addReader(Reader reader) {
        readerRepository.add(reader);
    }

    @Override
    public void deleteById(Integer id) {
        readerRepository.deleteById(id);
    }

    @Override
    public void modifyById(Integer id, Reader reader) {
        readerRepository.modifyById(id,reader);
    }

    @Override
    public List<Reader> findAllReader() {
        return readerRepository.findAll();
    }

    @Override
    public int checkValidById(Integer id) {
        return readerRepository.getStateById(id);
    }

    @Override
    public List<Reader> getReaderByState(Integer state) {
        return readerRepository.getReaderByState(state);
    }


}
