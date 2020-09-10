package com.crystal.controller;
import com.crystal.entity.Admin;
import com.crystal.entity.Book;
import com.crystal.entity.BookCase;
import com.crystal.entity.Borrow;
import com.crystal.service.BookService;
import com.crystal.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category=req.getParameter("category");
        if(category==null) category="book";

        switch (category){
            case "book":
                req.getRequestDispatcher("/book").forward(req,resp);
                break;
            case "student":
                req.getRequestDispatcher("/reader").forward(req,resp);
                break;
            case "staff":
                req.getRequestDispatcher("/staff").forward(req,resp);
                break;
        }
    }
}
