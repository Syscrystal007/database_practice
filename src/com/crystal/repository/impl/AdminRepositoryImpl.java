package com.crystal.repository.impl;

import com.crystal.entity.Admin;
import com.crystal.repository.AdminRepository;
import com.crystal.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql="select * from bookadmin where username = ? and password = ? ";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Admin admin = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                admin = new Admin(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return admin;
    }

    @Override
    public boolean register(Admin admin) {
        //用户已经存在
        if(findById(admin.getId())!=null||findByUsername(admin.getUsername()).size()>=2)
            return false;

        Connection connection = JDBCTools.getConnection();
        String sql = "insert into admin(id,username,password) values(?,?,?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,admin.getId());
            statement.setString(2,admin.getUsername());
            statement.setString(3,admin.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
        return true;
    }

    @Override
    public List<Admin> findAll() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookadmin";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Admin> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                list.add(new Admin(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public void addAdmin(Admin admin) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into bookadmin(id,username,password,level) values(?,?,?,?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,admin.getId());
            statement.setString(2,admin.getUsername());
            statement.setString(3,admin.getPassword());
            statement.setInt(4,admin.getLevel());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(Integer id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "delete from bookadmin where id=?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void modifyAdmin(Integer id, Admin admin) {
        deleteAdmin(id);
        addAdmin(admin);
    }

    @Override
    public Admin findById(Integer id) {
        Connection connection =JDBCTools.getConnection();

        String sql ="select * from admin where id = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Admin admin = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                admin = new Admin(id,resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return admin;
    }

    @Override
    public List<Admin> findByUsername(String name) {
        Connection connection =JDBCTools.getConnection();

        String sql ="select * from admin where username = ?";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Admin> list = new ArrayList<>();

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                list.add(new Admin(resultSet.getInt(1),name,resultSet.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return list;
    }



}
