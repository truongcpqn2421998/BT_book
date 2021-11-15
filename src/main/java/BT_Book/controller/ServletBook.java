package BT_Book.controller;

import BT_Book.model.Book;
import BT_Book.model.Category;
import BT_Book.service.book.BookService;
import BT_Book.service.book.IBookService;
import BT_Book.service.category.CategoryService;
import BT_Book.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletBook", value = "/books")
public class ServletBook extends HttpServlet {
    IBookService bookService=new BookService();
    ICategoryService categoryService=new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
                break;
            case "delete":
                deleteBook(request,response);
                break;
            case "edit":
                editForm(request,response);
                break;
            default:
                showAllBook(request,response);

        }
    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Book book=bookService.findById(id);
        request.setAttribute("book",book);
        RequestDispatcher dispatcher=request.getRequestDispatcher("book/edit.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        bookService.remove(id);
        try {
            response.sendRedirect("/books");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList=categoryService.findAll();
        request.setAttribute("categories",categoryList);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("book/create.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllBook(HttpServletRequest request, HttpServletResponse response) {
        List<Book>  books=bookService.findAll();
        request.setAttribute("listBook",books);
        RequestDispatcher dispatcher=request.getRequestDispatcher("book/listBook.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                createBook(request,response);
                break;
            case "delete":
                break;
            case "edit":
                editBook(request,response);
                break;
            default:

        }
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) {
        String name =request.getParameter("name");
        double price=Double.parseDouble(request.getParameter("price"));
        String description=request.getParameter("description");
        String[] categoriesStr= request.getParameterValues("category");
        int[] categories=new int[categoriesStr.length];
        for (int i = 0; i <categoriesStr.length ; i++) {
            categories[i]=Integer.parseInt(categoriesStr[i]);
        }
        Book book=new Book(name,price,description);

    }

    private void createBook(HttpServletRequest request, HttpServletResponse response) {
        String name =request.getParameter("name");
        double price=Double.parseDouble(request.getParameter("price"));
        String description=request.getParameter("description");
        String[] categoriesStr= request.getParameterValues("category");
        int[] categories=new int[categoriesStr.length];
        for (int i = 0; i <categoriesStr.length ; i++) {
            categories[i]=Integer.parseInt(categoriesStr[i]);
        }
        Book book=new Book(name,price,description);
        bookService.save(book,categories);
        try {
            response.sendRedirect("/books");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
