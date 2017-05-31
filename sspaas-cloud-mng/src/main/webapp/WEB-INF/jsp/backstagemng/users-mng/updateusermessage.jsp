<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%
String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"  src="<%=basePath%>/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"  src="<%=basePath%>/js/index.js"></script>
<script>
	function validate() {
		var password=document.getElementById("password").value;
		var psw = document.getElementById("newPassword").value;
		var psw1 = document.getElementById("passwordnew").value;
	      if(password==""||psw==""&&psw1==""){
	    	  return false; 
	      }	else if (psw==psw1 && psw.length<6) {
			  alert("密码长度不足6位");
			  return false;
		  } else if (psw==psw1 && psw.length==psw1.length && psw.length>=6) {
			//alert("密码输入正确");
			return true;
		  } else {
			alert("新密码两次输入不一样，请重新输入");
			return false;
		  }
	}
</script>
</head>
<body>
	<div>
		欢迎 ${sessionScope.userinfo.fullname} 上次登录时间为 ${sessionScope.userinfo.lastLoginTime}
	</div>

	<form name="form1" action="<%=basePath%>/usermng/background/updateUserPass" method="post"  onSubmit="return validate()">
	 <input type="hidden" name="userId" value="${sessionScope.userinfo.userId}">
		<table align="center" style="_margin-top: 1%;" border="1">
			<tr>
				<th>原密码</th>
				<th>
					<input type="password" name="password"  id="password"  onblur="checkpassword1(value,'${sessionScope.userinfo.userId}')"/>
					<span id="msgPassword" style="color: red;"></span>
				</th>
			</tr>
			<tr>
				<th>新密码</th>
				<th>
					<input type="password" name="newPassword" id="newPassword"/>
				</th>
			</tr>
			<tr>
				<th>再次输入新密码</th>
				<th>
					<input type="password" name="passwordnew" id="passwordnew"/>
				</th>
			</tr>
			<tr>
				<th>
					<input type="submit" value="提交">
				</th>
			</tr>
		</table>
	</form>
</body>
</html>