<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>读者注册</title>
</head>
<link rel="icon" href="images/search.gif" type="img/x-ico" />
<link href="css/register.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery-3.3.1.min.js"></script>
<body>
<div id="top"></div>
<div id="main">
    <img src="images/login.jpg" id="main_bg"/>
    <div id="login_block">
        <form action="/register" method="post" id="loginForm">
            <table border="0" border="solid 1px yellow">
                <tr>
                    <td class="title">学号:</td>
                    <td class="input">
                        <input type="text" name="id" id="id" class="login_input"/>
                    </td>
                </tr>
                <tr>
                    <td class="title">用户名:</td>
                    <td class="input">
                        <input type="text" name="username" id="username" class="login_input"/>
                    </td>
                </tr>
                <tr>
                    <td class="title">性别:</td>
                    <td class="input">
                        <input type="radio" name="sex" value="男" checked="checked"/>&nbsp;&nbsp;男&nbsp;&nbsp;
                        <input type="radio" name="sex" value="女"/>&nbsp;&nbsp;女
                    </td>
                </tr>
                <tr>
                    <td class="title">密码:</td>
                    <td class="input">
                        <input type="password" name="password" id="password" class="login_input"/>
                    </td>
                </tr>
                <tr>
                    <td class="title">真实姓名:</td>
                    <td class="input">
                        <input type="text" name="name" id="name" class="login_input"/>
                    </td>
                </tr>
                <tr>
                    <td class="title">证件号码:</td>
                    <td class="input">
                        <input type="text" name="cardid" id="cardid" class="login_input"/>
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <td class="title">证件照片:</td>--%>
<%--                    <td class="input">--%>
<%--                        <input type="password" name="card" id="upload" class="login_input"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <td class="title">联系电话:</td>
                    <td class="input">
                        <input type="text" name="tel" id="tel" class="login_input"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input class="btn" type="submit" value="注册"/>
                        <div class="btn" id="reset">重置</div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="footer">
    <div class="foot_content">
        CopyRight &copy; 2008 www.**********.com 南京市*****有限公司
    </div>
    <div class="foot_content">
        版权所有 Library V1.0
    </div>
</div>
</body>
</html>