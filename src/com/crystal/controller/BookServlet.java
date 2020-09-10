package com.crystal.controller;

import com.crystal.entity.*;
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

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private int PCNT = 8;
    private BookService bookService=new BookServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String pageStr = req.getParameter("page");
        String type = req.getParameter("type");

        //管理员和学生都回到书本管理界面
        if(method ==null)   method="findAllBooks";
        //初始回到第一页
        if(pageStr==null) pageStr="1";
        //判断用户类型
        if(type==null)  type="reader";

        switch (method){
            //读者或管理员查看所有书的信息
            case "findAllBooks":
                Integer page = Integer.parseInt(pageStr);
                List<Book> list = bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",PCNT);
                req.setAttribute("pages",bookService.getPages());
                req.setAttribute("currentPage",page);

                String url=null;
                if(type=="admin"){
                    req.setAttribute("idx",1);
                    req.setAttribute("idy",1);
                    url="index.jsp";
                }
                else url="reader.jsp";

                req.getRequestDispatcher(url).forward(req,resp);
                break;
                //管理员删除书籍
            case "deleteBook":
                Integer id = Integer.parseInt(req.getParameter("id"));
                bookService.deleteById(id);
                resp.sendRedirect("/book?page=1");
                break;
                //管理员增加书籍
            case "addBook":
                id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String author = req.getParameter("author");
                String publish = req.getParameter("publish");
                Integer pages= Integer.parseInt(req.getParameter("pages"));
                Double price = Double.parseDouble(req.getParameter("price"));

                BookCase bookCase = new BookCase(Integer.parseInt(req.getParameter("bookcaseid")),req.getParameter("bookcasename"));
                Book book = new Book(id,name,author,publish,pages,price,bookCase);
                bookService.addBook(book);
                break;
            //管理员修改书籍
            case "modifyBook":
                id = Integer.parseInt(req.getParameter("id"));
                name = req.getParameter("name");
                author = req.getParameter("author");
                publish = req.getParameter("publish");
                pages = Integer.parseInt(req.getParameter("pages"));
                price = Double.parseDouble(req.getParameter("price"));
                bookCase = new BookCase(Integer.parseInt(req.getParameter("bookcaseid")),req.getParameter("bookcasename"));
                book = new Book(id,name,author,publish,pages,price,bookCase);
                bookService.modifyById(id,book);
                break;
            //读者提交借阅申请，添加借阅记录
            case "addBorrow":
                HttpSession session = req.getSession();
                Reader reader = (Reader)session.getAttribute("reader");
                //添加借书请求
                id = Integer.parseInt(req.getParameter("id"));
                bookService.addBorrow(id,reader.getId());
                resp.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
            //管理员查询某种状态的图书(用以审核、查看违约和同意归还)
            case "findBorrowByState":
                page=Integer.parseInt(req.getParameter("page"));
                Integer state = Integer.parseInt(req.getParameter("state"));
                List<Borrow> borrowList = bookService.findAllBorrowByState(0,page);

                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",PCNT);
                req.setAttribute("pages",bookService.getBorrowPagesByState(state));
                req.setAttribute("currentPage",page);
                //跳转到审核界面，管理员同意或拒绝
                req.setAttribute("idx",3);
                req.setAttribute("idy",1);
                req.setAttribute("idz",state);
                req.getRequestDispatcher("bookadmin.jsp").forward(req,resp);
                break;
            //学生查询自己所有的借阅记录
            case "findAllBorrow":
                page=Integer.parseInt(req.getParameter("page"));
                session = req.getSession();
                reader = (Reader)session.getAttribute("reader");
                borrowList=bookService.findAllBorrowByReaderId(reader.getId(),page);

                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",PCNT);
                req.setAttribute("pages",bookService.getBorrowPagesByState(1));
                req.setAttribute("currentPage",page);
                //管理员同意归还
                req.getRequestDispatcher("return.jsp").forward(req,resp);
                break;
            //管理员同意或拒绝，修改借阅记录的状态
            case "handleBorrow":
                Integer new_state = Integer.parseInt(req.getParameter("state"));
                Integer borrowId = Integer.parseInt(req.getParameter("id"));
                page = Integer.parseInt(pageStr);
                session = req.getSession();
                Admin admin = (Admin)session.getAttribute("admin");
                bookService.handleBorrow(borrowId,new_state,admin.getId());

                if(new_state!=3){
                    resp.sendRedirect("/book?page="+page);
                }else{
                    resp.sendRedirect("/book?method=getBorrowed&page="+page);
                }
                break;
            //查询借阅排行前几(且达到一定借阅量)的图书
            case "findTop":
                int num=5,lvl=20;

                List<Book> topList = bookService.getTop(num,lvl);
                req.setAttribute("list",topList);
                req.setAttribute("dataPrePage",PCNT);
                req.setAttribute("pages", (num % PCNT==0)?num/PCNT:(num/PCNT+1));
                req.getRequestDispatcher("rank.jsp").forward(req,resp);
                break;
        }


    }

}
