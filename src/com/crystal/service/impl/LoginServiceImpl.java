package com.crystal.service.impl;

import com.crystal.repository.AdminRepository;
import com.crystal.repository.ReaderRepository;
import com.crystal.repository.impl.AdminRepositoryImpl;
import com.crystal.repository.impl.ReaderRepositoryImpl;
import com.crystal.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password, String type) {
        Object object = null;

        switch (type){
            case "reader":
                object = readerRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username,password);
                break;
        }

        return object;
    }
}
