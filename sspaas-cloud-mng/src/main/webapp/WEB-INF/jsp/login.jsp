<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<link rel=”icon” href="/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="/favicon.ico" mce_href="/favicon.ico" type="image/x-icon">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>云盘后台管理|登陆</title>

<link href="<%=basePath%>/css/login/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>/css/login/signin.css" rel="stylesheet">
<script src="<%=basePath%>/hplus/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript">
function changeImg() {
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
  }
//时间戳   
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
function chgUrl(url) {
  var timestamp = (new Date()).valueOf();
  //url = url.substring(0, 18);
  if ((url.indexOf("&") >= 0)) {
    url = url + "×tamp=" + timestamp;
  } else {
    url = url + "?timestamp=" + timestamp;
  }
  return url;
}
</script>
<!-- 解决登录失效时,登录界面打开位置异常 -->
<script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body>
<h1>云盘后台管理<sup>v0.10_Alpha</sup></h1>
<div class="signin">
	<div class="signin-head"><img src="<%=basePath%>/images/login/login_user.png" alt="" class="img-circle"></div>
	<form class="form-signin" action="<%=basePath%>/usermng/login" method="post" role="form">
		<input type="text" name="loginName" class="form-control" id="username-from" placeholder="用户名" required autofocus  value="${loginname}"/>
		<input type="password" name="password" class="form-control" id="password-from" placeholder="密码" required   value="${password}" />
		
		<a onclick="changeImg()"><img id="imgObj" class="codeimg" alt="验证码" src="<%=basePath%>/code" /></a>
		<input type="text" name="loginCode" class="cod	eclass" placeholder="验证码" required />
		<a onclick="changeImg()" href="#">换一张</a>
		
		<button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
        <%--<center><a href="<%=basePath%>/addUser">用户注册</a></center>--%>
		<label class="checkbox">
			<!--<input type="checkbox" value="remember-me"> 记住我   -->
		</label>
		<div style="margin-top: 1px;text-align:center;">
			<span style="color: red;font-size: 12px;font-weight: bold;">${requestScope.message }</span>
		</div>
	</form>
</div>
<!-- 
<div style="width: 100px;height: 30px;margin-left: 800px;margin-top: 290px;">
	
	</div> -->
<div style="text-align:center;">

</div>
</body>
</html>