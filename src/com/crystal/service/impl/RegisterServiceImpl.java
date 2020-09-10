package com.crystal.service.impl;
import com.crystal.entity.Admin;
import com.crystal.entity.Reader;
import com.crystal.repository.AdminRepository;
import com.crystal.repository.ReaderRepository;
import com.crystal.repository.impl.AdminRepositoryImpl;
import com.crystal.repository.impl.ReaderRepositoryImpl;
import com.crystal.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();

    @Override
    public boolean register(Object user, String type) {
        boolean success = false;
        switch (type){
            case "reader":
                success = readerRepository.register((Reader) user);
                break;
            case "admin":
                success = adminRepository.register((Admin) user);
                break;
        }
        return success;
    }
}
