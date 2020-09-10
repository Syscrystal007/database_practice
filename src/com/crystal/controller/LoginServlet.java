package com.crystal.controller;

import com.crystal.entity.Admin;
import com.crystal.entity.Reader;
import com.crystal.service.BookService;
import com.crystal.service.LoginService;
import com.crystal.service.impl.BookServiceImpl;
import com.crystal.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService=new LoginServiceImpl();
    private BookService bookService = new BookServiceImpl();
    @Override
    //处理登录的业务逻辑
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        Object object = loginService.login(username,password,type);

        if(object != null){
            HttpSession session = req.getSession();
            switch (type){
                case "reader":
                    Reader reader = (Reader)object;
                    session.setAttribute("reader",reader);
                    //跳转到读者的首页
                    resp.sendRedirect("/book?page=1&type=admin");
                    break;
                case "admin":
                    Admin admin =(Admin)object;
                    session.setAttribute("admin",admin);
                    resp.sendRedirect("/admin?page=1");
                    break;
            }
        }else{
            resp.sendRedirect("login.jsp");
        }
    }
}
