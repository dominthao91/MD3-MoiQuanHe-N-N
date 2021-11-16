package service;

import config.ConnectionSingleton;
import model.Book;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IManager<Book>{
    private static Connection connection = ConnectionSingleton.getConnection();
    private static CategoryService categoryService = new CategoryService();

public List<Book> findByName(String name){
    List<Book> bookList = new ArrayList<>();
    List<Category>categories ;
    Book book;
    try {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from  book where bName =?");
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("bID");
            double price = resultSet.getDouble("bPrice");
            String note = resultSet.getString("bNote");
            categories = categoryService.findAllCategoryForBook(id);
            book = new Book(id,name,price,note,categories);
            bookList.add(book);
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }return bookList;
}

    @Override
    public List<Book> showAll() {
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("bID");
                String name = resultSet.getString("bName");
                double price = resultSet.getDouble("bPrice");
                String note = resultSet.getString("bNote");
                List<Category> categories = categoryService.findAllCategoryForBook(id);
                Book book = new Book(id, name, price, note,categories);
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return bookList;
    }
        @Override
    public Book findById(int id) {
       List<Category> categories = new ArrayList<>();
       Book book = null;
       try {
           PreparedStatement preparedStatement =
                   connection.prepareStatement("select * from book where bID=?");
           preparedStatement.setInt(1,id);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               String name = resultSet.getString("bName");
               double price = resultSet.getDouble("bPrice");
               String note = resultSet.getString("bNote");
               book = new Book(id,name,price,note);
           }
           PreparedStatement preparedStatement1 = connection.prepareStatement("select  * from book_category where id =?");
           preparedStatement1.setInt(1,id);
           ResultSet resultSet1 = preparedStatement1.executeQuery();
           while (resultSet1.next()){
               int id1 = resultSet1.getInt("id1");
               Category category = categoryService.findById(id1);
               categories.add(category);
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }return book;
        }

    @Override
    public boolean add(Book book, int[] arr) {
       boolean rowAddBook = false;
       int bID = 0;
       try {
           PreparedStatement preparedStatement =
                   connection.prepareStatement("insert into book (name ,price,note) value (?,?,?)", Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1,book.getName());
           preparedStatement.setDouble(2,book.getPrice());
           preparedStatement.setString(3,book.getNote());
           rowAddBook =preparedStatement.executeUpdate()>0;
           ResultSet resultSet =preparedStatement.getGeneratedKeys();
           while (resultSet.next()){
               bID = resultSet.getInt(1);
           }
           PreparedStatement preparedStatement1 =connection.prepareStatement("insert  into book_category value  (?,?,?)");
           for (int a:arr
                ) {
               preparedStatement1.setInt(1,bID);
               preparedStatement.setInt(2,a);
               preparedStatement.executeUpdate();
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }return rowAddBook;
    }

    @Override
    public boolean edit(Book book, int[] arr) {
        boolean rowEditBook = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update book set name = ?,set price =?,set note =? where bID=?");
            preparedStatement.setString(1,book.getName());
            preparedStatement.setDouble(2,book.getPrice());
            preparedStatement.setString(3,book.getNote());
            preparedStatement.setInt(4,book.getId());
            rowEditBook = preparedStatement.executeUpdate()>0;
            PreparedStatement preparedStatement1 = connection.prepareStatement("delete from book_category where bID = ?");
            for (int a:arr
                 ) {
                preparedStatement1.setInt(1,book.getId());
                preparedStatement1.executeUpdate();
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement("select into book_category value (?,?)");
            for (int a:arr
                 ) {
                preparedStatement2.setInt(1,book.getId());
                preparedStatement2.setInt(2,a);
                preparedStatement2.executeUpdate();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return rowEditBook;
    }

    @Override
    public boolean delete(int id) {
       boolean rowDeleteBook = false;
       try {
           PreparedStatement preparedStatement = connection.prepareStatement("delete from book where bID=?");
           preparedStatement.setInt(1,id);
           rowDeleteBook = preparedStatement.executeUpdate()>0;
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }return rowDeleteBook;
    }
}
