package com.crystal.controller;

import com.crystal.entity.Reader;
import com.crystal.service.ReaderService;
import com.crystal.service.impl.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reader")
public class ReaderServlet extends HttpServlet {
    private ReaderService readerService = new ReaderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method==null) method="findAllStudent";
        switch (method){
            case "findAllReader":
                List<Reader> readerlist = readerService.findAllReader();
                req.setAttribute("list",readerlist);
                req.getRequestDispatcher("bookadmin.jsp").forward(req,resp);
                break;
            case "addReader":
                Integer id = Integer.parseInt(req.getParameter("id"));
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String tel = req.getParameter("tel");
                String cardid = req.getParameter("cardid");
                String gender = req.getParameter("gender");
                String name = req.getParameter("name");
                Reader reader = new Reader(id,username,password,name,tel,cardid,gender);
                readerService.addReader(reader);
                break;
            case "deleteReader":
                id = Integer.parseInt(req.getParameter("id"));
                readerService.deleteById(id);
                break;
            case "renewReader":

                break;
            case "modifyReader":
                id = Integer.parseInt(req.getParameter("id"));
                Integer preid = Integer.parseInt(req.getParameter("preid"));
                username = req.getParameter("username");
                password = req.getParameter("password");
                tel = req.getParameter("tel");
                cardid = req.getParameter("cardid");
                gender = req.getParameter("gender");
                name = req.getParameter("name");
                reader = new Reader(id,username,password,name,tel,cardid,gender);
                readerService.modifyById(preid,reader);
                break;

            case "findAllAccount":
                break;
            case "addAccount":
                break;
            case "deleteAccount":
                break;
            case "modifyAccount":
                break;

        }
    }

}
