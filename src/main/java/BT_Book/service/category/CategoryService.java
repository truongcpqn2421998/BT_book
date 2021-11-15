package BT_Book.service.category;

import BT_Book.config.connectionSingleton;
import BT_Book.model.Book;
import BT_Book.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{
    Connection connection= connectionSingleton.getConnection();
    @Override
    public List<Category> findAll() {
        List<Category> categoryList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from category");
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String description=rs.getString("description");
                Category category=new Category(id,name,description);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public void save(Category category) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("insert into categoty(name,category) value (?,?);");
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Category findById(int id) {
        return null;
    }

    public List<Category> checkCategory(int id) {
        List<Category> categoryList=new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("call checkCategory(?);");
            callableStatement.setInt(1,id);
            ResultSet rs=callableStatement.executeQuery();
            while (rs.next()){
                String name=rs.getString("name");
                Category category=new Category(name);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public void update(int id, Category category) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("update category set name=?,description=? where id=?");
            preparedStatement.setString(1,"name");
            preparedStatement.setString(2,"description");
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("delete from category where id=?;");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
