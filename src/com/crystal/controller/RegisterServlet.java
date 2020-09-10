package com.crystal.controller;

import com.crystal.entity.Admin;
import com.crystal.entity.Reader;
import com.crystal.service.RegisterService;
import com.crystal.service.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private RegisterService registerService = new RegisterServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        if(type==null)  type="reader";
        Integer id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String tel = req.getParameter("tel");

        switch (type){
            case "reader":
                String cardid = req.getParameter("cardid");
                String gender = req.getParameter("gender");
                String name = req.getParameter("name");
                Reader reader = new Reader(id,username,password,name,tel,cardid,gender);
                if(registerService.register(reader,type)){
                    HttpSession session = req.getSession();
                    session.setAttribute("reader",reader);
                    resp.sendRedirect("index.jsp");
                }else{
                    //js实现
                    resp.sendRedirect("register.jsp");
                }
                break;
            case "admin":
                Admin admin = new Admin(id,username,password);
                if(registerService.register(admin,type)){
                    HttpSession session = req.getSession();
                    session.setAttribute("admin",admin);
                    resp.sendRedirect("check.jsp");
                }else{
                //js实现
                    resp.sendRedirect("register.jsp");
                }
                break;
        }
    }
}
