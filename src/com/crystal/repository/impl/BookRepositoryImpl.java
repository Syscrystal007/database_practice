package com.crystal.repository.impl;

import com.crystal.entity.Book;
import com.crystal.entity.BookCase;
import com.crystal.entity.Storage;
import com.crystal.repository.BookRepository;
import com.crystal.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book findById(Integer id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id and book.id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Book book = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               book = new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),new BookCase(resultSet.getInt(9),resultSet.getString(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return book;
    }

    @Override
    public List<Book> findAll(int index,int limit) {

//        List<Book> list = new ArrayList<>();
//        try {
//            QueryRunner queryRunner = new QueryRunner();
//            list = queryRunner.query(connection,sql,new BeanListHandler<>(Book.class),index,limit);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JDBCTools.release(connection,null,null);
//        }
//        return list;

        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Book(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getInt(5),resultSet.getDouble(6),
                        new BookCase(resultSet.getInt(9),resultSet.getString(10))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        System.out.println(list);
        return list;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*)from book";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int count=0;
        try {
            preparedStatement=connection.prepareStatement(sql);
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
    public void add(Book book) {
        Connection connection = JDBCTools.getConnection();
        String sql="insert into book(id,name,author,publish,prices,pages,bookcaseid) values(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,book.getId());
            preparedStatement.setString(2,book.getName());
            preparedStatement.setString(3,book.getAuthor());
            preparedStatement.setString(4,book.getPublish());
            preparedStatement.setDouble(5,book.getPrice());
            preparedStatement.setInt(6,book.getPages());
            preparedStatement.setInt(7,book.getBookCase().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = JDBCTools.getConnection();
        String sql="delete from book where id = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public void modifyById(Integer id,Book book) {
        Connection connection = JDBCTools.getConnection();
        String sql="update book set id=?,name=?,author=?,publish=?,pages=?,price=?,bookcaseid=?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getPublish());
            preparedStatement.setInt(4,book.getPages());
            preparedStatement.setDouble(5,book.getPrice());
            preparedStatement.setInt(6,book.getBookCase().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,null);
        }
    }

    @Override
    public List<Book> getTop(Integer num,Integer level) {
        Connection connection = JDBCTools.getConnection();

        String sql = null;
        if(num==null&&level==null)  return null;
        else if(level==null)
            sql="select * from book order by borrowTimes limit 0,?";
        else if(num==null)
            sql="select * from book where borrowTimes >=? order by borrowTimes";
        else
            sql="select * from book where borrowTimes >=? order by borrowTimes limit 0,?";

        List<Book> list = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
           if(level==null)  statement.setInt(1,num);
           else if(num==null)  statement.setInt(1,level);
           else{
               statement.setInt(1,level);
               statement.setInt(2,num);
           }
           resultSet = statement.executeQuery();
           while(resultSet.next()){
               list.add(new Book(resultSet.getInt(1),resultSet.getString(2),
                       resultSet.getString(3),resultSet.getString(4),
                       resultSet.getInt(9)));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }

        return list;
    }



    @Override
    public List<Storage> getAllStorage() {
        List<Storage> list = null;

        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookflow bf,book b where bf.id=b.id";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        Integer avail=0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
               list.add(new Storage(new Book(resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)),
                       resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return list;
    }

    @Override
    public Storage getStorageById(Integer id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "selelct * from bookflow where id=?";
        PreparedStatement statement =null;
        ResultSet resultSet = null;
        Storage storage = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                storage=new Storage(new Book(resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)),
                        resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return storage;
    }
}
