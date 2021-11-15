package BT_Book.service.book;

import BT_Book.config.connectionSingleton;
import BT_Book.model.Book;
import BT_Book.model.Category;
import BT_Book.service.category.CategoryService;
import BT_Book.service.category.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService{
    ICategoryService categoryService=new CategoryService();
    List<Book> books=new ArrayList<>();
    Connection connection= connectionSingleton.getConnection();
    @Override
    public List<Book> findAll() {
        List<Book> books=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from book;");
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                double price=rs.getDouble("price");
                String description=rs.getString("description");
                List<Category> categoryList=categoryService.checkCategory(id);
                Book book=new Book(id,name,price,description,categoryList);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public void save(Book book) {

    }


    @Override
    public void save(Book book ,int[] categories) {
        try {

            PreparedStatement preparedStatement= connection.prepareStatement("insert into book(name,price,description) value (?,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setDouble(2,book.getPrice());
            preparedStatement.setString(3,book.getDescription());
            int row=preparedStatement.executeUpdate();
            ResultSet rs=preparedStatement.getGeneratedKeys();
            int book_id=0;
            if(rs.next())
                book_id=rs.getInt(1);
            if(row==1){
                 preparedStatement=connection.prepareStatement("insert into book_category(id_book,id_category) value (?,?);");
                for (int id_category: categories
                     ) {
                    preparedStatement.setInt(1,book_id);
                    preparedStatement.setInt(2,id_category);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public Book findById(int id) {
        Book book=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from book where id=?;");
            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String name=rs.getString("name");
                double price=rs.getDouble("price");
                String description=rs.getString("description");
                book=new Book(id,name,price,description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void update(int id, Book book) {

    }

    @Override
    public void update(int id, Book book,int[] categories) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("update book set name=?,price=?,description=? where id=?;");
            preparedStatement.setString(1,book.getName());
            preparedStatement.setDouble(2,book.getPrice());
            preparedStatement.setString(3,book.getDescription());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1= connection.prepareStatement("delete from book_category where id_book=?;");
            preparedStatement1.setInt(1,id);
            preparedStatement1.executeUpdate();
            PreparedStatement  preparedStatement2=connection.prepareStatement("insert into book_category(id_book,id_category) value (?,?);");

            for (int category_id: categories
                 ) {
                preparedStatement2.setInt(1,id);
                preparedStatement2.setInt(2,category_id);
                preparedStatement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void remove(int id) {
        try {
            CallableStatement callableStatement=connection.prepareCall("call deleteBook(?)");
            callableStatement.setInt(1,id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
