package com.crystal.repository.impl;

import com.crystal.entity.Reader;
import com.crystal.repository.ReaderRepository;
import com.crystal.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class ReaderRepositoryImpl implements ReaderRepository {
    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql="select * from reader where username = ? and password = ? ";
        Reader reader = null;

        try {
            QueryRunner queryRunner = new QueryRunner();
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),username,password);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return reader;
    }

    @Override
    public boolean register(Reader reader) {

        //用户已经存在
        if(findById(reader.getId())!=null||findByName(reader.getUsername()).size()>0)
            return false;

        add(reader);
        return true;
    }

    @Override
    public List<Reader> findAll() {
        List<Reader> list=null;
        Connection connection = JDBCTools.getConnection();
        ResultSet resultSet = null;
        String sql = "select * from reader";

        PreparedStatement statement =null;

        try {
            statement= connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                list.add(new Reader(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void add(Reader reader) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into reader(id,username,password,name,tel,cardid,gender) values(?,?,?,?,?,?,?)";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,reader.getId());
            statement.setString(2,reader.getUsername());
            statement.setString(3,reader.getPassword());
            statement.setString(4,reader.getName());
            statement.setString(5,reader.getTel());
            statement.setString(6,reader.getCardid());
            statement.setString(7,reader.getGender());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }


    @Override
    public void deleteById(Integer id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "delete from reader where id = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }

    @Override
    public void modifyById(Integer id, Reader reader) {
       deleteById(id);
       add(reader);
    }

    @Override
    public Reader findById(Integer id) {
        Connection connection =JDBCTools.getConnection();

        String sql ="select * from reader where id = ?";

        PreparedStatement statement = null;

        Reader reader = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
        return reader;
    }

    @Override
    public List<Reader> findByName(String name) {
        Connection connection =JDBCTools.getConnection();

        String sql ="select * from reader where name = ?";

        PreparedStatement statement = null;

        List<Reader> list = null;

        try {
            QueryRunner queryRunner = new QueryRunner();
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Reader.class),name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
        return list;
    }

    @Override
    public int getStateById(Integer id) {
        Reader reader = findById(id);

        if(reader==null)    return -1;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String enterYear = String.valueOf(id).substring(0,4);

        String graduateDay = enterYear+"/01/01";
        String today = dateFormat.format(new Date());

        return today.compareTo(graduateDay);
    }

    @Override
    public List<Reader> getReaderByState(Integer state) {
        List<Reader> list=findAll();
        for(int i=0;i<list.size();i++){
            Reader reader =list.get(i);
            if(getStateById(reader.getId())!=state)
                list.remove(reader);
        }
        return list;
    }

    @Override
    public void delExpired() {
        Connection connection = JDBCTools.getConnection();
        List<Integer> delIdList = null;
        List<Reader> list=findAll();
        for (Reader r:list) {
            if(getStateById(r.getId())!=0)
                delIdList.add (r.getId());
        }
        for(Integer id:delIdList){
            deleteById(id);
        }
    }
}
