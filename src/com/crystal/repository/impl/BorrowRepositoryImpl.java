package com.crystal.repository.impl;

import com.crystal.entity.Book;
import com.crystal.entity.Borrow;
import com.crystal.entity.Reader;
import com.crystal.repository.BorrowRepository;
import com.crystal.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {
    @Override
    public void insert(Integer bookid, Integer readerid, String borrowtime, String returntime) {
        Connection connection = JDBCTools.getConnection();
        String sql="insert into borrow(bookid,readerid,borrowtime,returntime,state) values(?,?,?,?,0)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,bookid);
            preparedStatement.setInt(2,readerid);
            preparedStatement.setString(3,borrowtime);
            preparedStatement.setString(4,returntime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public List<Borrow> findAllByReaderId(Integer id, Integer index, Integer limit) {
//        Connection connection = JDBCTools.getConnection();
//        String sql="select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state " +
//                "from borrow br,book b,reader r where readerid = ? and b.id = br.bookid and r.id = br.readerid limit ?,?";
//
//        List<Borrow> list = null;
//        try {
//            QueryRunner queryRunner = new QueryRunner();
//            list = queryRunner.query(connection,sql,new BeanListHandler<>(Borrow.class),id,index,limit);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            JDBCTools.release(connection,null,null);
//        }
        Connection connection = JDBCTools.getConnection();
        String sql="select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state " +
                "from borrow br,book b,reader r where readerid = ? and b.id = br.bookid and r.id = br.readerid limit ?,?";
        PreparedStatement statement=null;
        ResultSet resultSet = null;

        List<Borrow> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setInt(2,index);
            statement.setInt(3,limit);
            resultSet = statement.executeQuery();
            Book book = null;
            Reader reader = null;
            Borrow borrow = null;
            while (resultSet.next()){
                book = new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                reader = new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                borrow = new Borrow(resultSet.getInt(1),book,reader,resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(10));
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }

        return list;
    }

    @Override
    public int count(Integer readerid) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow br,book b,reader r where readerid = ? and b.id = br.bookid and r.id = br.readerid";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int count=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,readerid);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                count=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }

        return count;
    }

    @Override
    public int countByState(Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow br,book b,reader r where state = ? and b.id = br.bookid and r.id = br.readerid";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int count=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                count=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return count;
    }

    @Override
    public List<Borrow> findAllByState(Integer state, Integer index, Integer limit) {
//        Connection connection = JDBCTools.getConnection();
//        String sql="select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state " +
//                "from borrow br,book b,reader r where state = ? and b.id = br.bookid and r.id = br.readerid limit ?,?";
//
//        ResultSet resultSet = null;
//        List<Map<String,Object>> mapList = null;
//        List<Borrow> list=null;
//        try {
//            QueryRunner queryRunner =new QueryRunner();
//            mapList  = queryRunner.query(connection,sql,new MapListHandler(),state,index,limit);
//
//            for(Map<String,Object> m:mapList){
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCTools.release(connection,null,null);
//        }
        Connection connection = JDBCTools.getConnection();
        String sql="select br.id,b.name,b.author,b.publish,br.borrowtime,br.returntime,r.name,r.tel,r.cardid,br.state " +
                "from borrow br,book b,reader r where state = ? and b.id = br.bookid and r.id = br.readerid limit ?,?";
        PreparedStatement statement=null;
        ResultSet resultSet = null;

        List<Borrow> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,state);
            statement.setInt(2,index);
            statement.setInt(3,limit);
            resultSet = statement.executeQuery();
            Book book = null;
            Reader reader = null;
            Borrow borrow = null;
            while (resultSet.next()){
                book = new Book(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                reader = new Reader(resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                borrow = new Borrow(resultSet.getInt(1),book,reader,resultSet.getString(5),
                        resultSet.getString(6),resultSet.getInt(10));
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }

        return list;
    }

    @Override
    public void handleBorrow(Integer borrowid, Integer state, Integer adminid) {
        Connection connection = JDBCTools.getConnection();
        String sql="update borrow set state=?,adminid=? where id=?";

        PreparedStatement preparedStatement = null;

        System.out.println(state);
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,state);
            preparedStatement.setInt(2,adminid);
            preparedStatement.setInt(3,borrowid);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }
}
