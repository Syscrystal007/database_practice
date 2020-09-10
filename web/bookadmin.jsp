<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/9/10
  Time: 7:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="1.css" type="text/css">
    <!--     <script type="text/javascript" src="jquery.min.js" ></script>-->
    <c:if test="${idy==1}"><title>图书管理</title></c:if>
    <c:if test="${idy==2}"><title>学生管理</title></c:if>
    <c:if test="${idy==3}"><title>人员管理</title></c:if>
</head>
<body>
    <script type="text/javascript" src="js/switchBody.js"></script>
    <div class="bg hide handel"></div>
    <div class="add_text hide handel">
        <form action="#" method="post" id="newone">
            <p>书名:<input name="book" placeholder=""></p>
            <p>作者:<input name="author" placeholder=""></p>
            <p>价格:<input name="price" placeholder=""></p>
            <p>分类:
                <select name="classify">
                    <option id="计算机" value="计算机">计算机</option>
                    <option id="文学" value="文学">文学</option>
                    <option id="小说" value="小说">小说</option>
                    <option id="教材" value="教材">教材</option>
                    <option id="黑客" value="黑客">黑客</option>

                </select>
            </p>
            <p id="butt">
                <button class="confirm">确定</button>
                <button class="cancel">取消</button>
            </p>
        </form>
    </div>
    <div class="all">
        <div class="head">
            <span class="title">图书管理系统</span>

            <a class="choice" onclick="switchX(1)">信息管理</a>
            <a class="choice" onclick="switchX(2)">借阅排行榜</a>
            <a class="choice" onclick="switchX(3)">借阅审核</a>


            <span class="login">
             <a href="#4">欢迎你</a><span>boy</span>
             <a href="#5">注销</a>
             <a href="#4">修改密码</a>
         </span>
        </div>
        <div class="middle">
            <div class="left">
                <div class="category ${idy=1}?'chosen':''" onclick="switchY(1)">图书管理</div>
                <div class="category ${idy=2}?'chosen':''" onclick="switchY(2)">读者管理</div>
                <div class="category ${idy=3}?'chosen':''" onclick="switchY(3)">人员管理</div>
            </div>
            <div class="right ${idx=1}?'':'hide'">
                <div class="find">
                    <span><input id="find_text" type="text" placeholder="  书名" value=""></span>
                    <button id="find_btn" onclick="fd()">查找</button>
                </div>
                <div class="content">
                    <table class="tb">
                        <tr class="tb_head">
                            <th class="col_1">图书编号</th>
                            <th class="col_2">书名</th>
                            <th class="col_3">作者</th>
                            <th class="col_4">价格</th>
                            <th class="col_5">类别</th>
                            <th class="col_6">上架时间</th>
                            <th class="col_7">库存</th>
                            <th class="operate">操作</th>
                        </tr>
                        <c:forEach items="${list}" var="book">
                            <tr>
                                <td class="col_1">${book.id}</td>
                                <td class="col_2">${book.name}</td>
                                <td class="col_3">${book.author}</td>
                                <td class="col_4">${book.author}</td>
                                <td class="col_5">${book.bookcase.name}</td>
                                <td class="col_6">${book.importTime}</td>
                                <td class="col_7">${book.storage.left}</td>
                                <td class="operate">
                                    <button class="view">查看</button>
                                    <button class="edit" onclick="redact(this)">编辑</button>
                                    <button class="del" onclick="dell(this)">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div id="addBtn" onclick="addBook(this)"></div>
                    <div class="page">
                        <ul>
                            <li><a href="#" class="page_edge">上一页</a></li>
                            <li class="page1"><span>1</span></li>
                            <li><a href="#" class="pagepa hide">2</a></li>
                            <li><a href="#" class="page_edge">下一页</a></li>
                        </ul>
                    </div>
                </div>
            </div>
</body>
</html>
