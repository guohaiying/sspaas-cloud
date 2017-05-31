<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div>
 <form action="<%=basePath%>/usermng/updateUser.do" method="post" >
  <input type="hidden"  name=userId value="${user.userId}"/>
    <table align="center" style="_margin-top:1%;">
    <tr>
    <th>登录账号</th>
    <th><input type="text" name=loginName value= "${user.loginName}"> </th>
    </tr>
     <tr>
    <th>用户密码</th>
    <th><input type="text"  name=password value="${user.password}"></th>
    </tr>
       <tr>
    <th>用户姓名</th>
    <th> <input type="text"  name=fullname value="${user.fullname}"> </th>
    </tr>
      <tr>
    <th>手机号</th>
    <th> <input type="text"  name=mobile value="${user.mobile}"> </th>
    </tr>
      <tr>
    <th>电子邮箱</th>
    <th> <input type="text" name=email value="${user.email}">  </th>
    </tr>
    <tr>
    <th><input type="submit" value="提 交 "/></th>
    </tr>
    </table>
    </form>
   </div>
</body>
</html>