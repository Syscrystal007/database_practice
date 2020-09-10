package com.crystal.repository;

import com.crystal.entity.Admin;

import java.util.List;

public interface AdminRepository {
    public Admin login(String username, String password);
    public boolean register(Admin admin);

    public Admin findById(Integer id);
    public List<Admin> findByUsername(String name);

    public List<Admin> findAll();
    public void addAdmin(Admin admin);
    public void deleteAdmin(Integer id);
    public void modifyAdmin(Integer id,Admin admin);
}
