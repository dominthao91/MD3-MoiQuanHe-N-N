package controller;

import model.Book;
import model.Category;
import service.BookService;
import service.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "BookServlet", value = "/Books")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
//                showEditForm(request,response);
                break;
            case "delete":
//                deleteBook(request,response);
                break;
            default:
                listBooks(request,response);
                break;
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
            List<Category> categories = categoryService.showAll();
            RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
            request.setAttribute("ct",categories);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) {
        List<Book>bookList;
        String name = request.getParameter("names");
        if (name!=null && name!=""){
            bookList = bookService.findByName(name);
        }else {
            bookList = bookService.showAll();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/list.jsp");
        request.setAttribute("bl",bookList);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action ="";
        }
        switch (action){
            case "create":
                createBook(request,response);5
                break;
            case "edit":
//                updateBook(request,response);
                break;
        }
    }

    private void createBook(HttpServletRequest request, HttpServletResponse response) {
    String name = request.getParameter("name");
    double price = Double.parseDouble(request.getParameter("price"));
    String note = request.getParameter("note");
    }
}
