package service;

import config.ConnectionSingleton;
import model.Category;

import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IManager<Category>{
    private Connection connection = ConnectionSingleton.getConnection();

    public List<Category>findAllCategoryForBook(int id){
        List<Category>categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from book_category where  bID =?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int cid = resultSet.getInt("cID");
                Category category = findById(cid);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return categories;
    }
    @Override
    public List<Category> showAll() {
        List<Category> categories = new ArrayList<>();
        try {


        PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("cID");
            String name = resultSet.getString("cName");
            String note = resultSet.getString("cNote");
            Category category = new Category(id, name, note);
            categories.add(category);
        }
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
    Category category = null;
    try {
        PreparedStatement preparedStatement =
                connection.prepareStatement("select  * from category where cID = ?");
    preparedStatement.setInt(1,id);
    ResultSet resultSet =preparedStatement.executeQuery();
    while (resultSet.next()){
        String name = resultSet.getString("cName");
        String note = resultSet.getString("cNote");
        category = new Category(id,name,note);
    }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }return category;
    }

    @Override
    public boolean add(Category category, int[] arr) {
       boolean rowAdd = false;
       try {
           PreparedStatement preparedStatement =
                   connection.prepareStatement("insert into category (name ,note)value (?,?)");
           preparedStatement.setString(1,category.getName());
           preparedStatement.setString(2,category.getNote());
           rowAdd =preparedStatement.executeUpdate()>0;
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }return rowAdd;
    }

    @Override
    public boolean edit(Category category, int[] arr) {
      boolean rowEdit = false;
      try {
          PreparedStatement preparedStatement =
                  connection.prepareStatement("update category set name =?,set note = ? where cID =?");
          preparedStatement.setString(1,category.getName());
          preparedStatement.setString(1,category.getNote());
          preparedStatement.setInt(1,category.getId());
          rowEdit = preparedStatement.executeUpdate()>0;
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }return rowEdit;
    }

    @Override
    public boolean delete(int id) {
        boolean rowDelete= false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("delete category where cID = ?");
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }

}
