<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/9/3
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    request.setAttribute("method",request.getParameter("method"));
%>
<head>
    <c:if test="${method==modify}"><title>修改图书信息</title></c:if>
    <c:if test="${method==add}"><title>添加图书信息</title></c:if>
</head>

<body>
    <form action="/book?method=modify&status=1" method="post">
        编号：<input type="text" name="id" value="${book.id}" readonly/><br/>
        姓名：<input type="text" name="name" value="${book.name}"/><br/>
        作者：<input type="text" name="author" value="${book.author}"/><br/>
        出版社：<input type="text" name="publish" value="${book.publish}"/><br/>
        页数：<input type="text" name="pages" value="${book.pages}"/><br/>
        价格：<input type="text" name="price" value="${book.price}"/><br/>
        书架号：<input type="text" name="bookcaseid" value="${book.bookcaseid}"/><br/>
        分类：<input type="text" name="bookcasename" value="${book.bookcasename}"/><br/>
        <c:if test="${method==modify}"><input type="submit" value="修改"/></c:if>
        <c:if test="${modify==add}"><input type="submit" value="增加"/></c:if>
        <br/>
    </form>
</body>
</html>
