<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Insert title here</title>
<link href="<%=basePath%>/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath%>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath%>/hplus/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath%>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">


<script type="text/javascript"  src="<%=basePath%>/js/jQuery-1.7.1.js"></script>
<script type="text/javascript"  src="<%=basePath%>/js/index.js"></script>
</head>
<body>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm"  action="<%=basePath%>/usermng/background/addUser" method="post">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">登录名：</label>
                                <div class="col-sm-8">
                                    <input id="loginName" name="loginName" class="form-control" type="text"  onblur="checkLoginName(this.value)" onfocus="MyFunctionLogin()" >
                                    <span  id="msgloginName"  class="help-block m-b-none"><i class="fa fa-info-circle"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-8"> 
                                 <input id="password" name="password" class="form-control" type="password"  onblur="checkPassword(this.value)" onfocus="MyFunctionPassword()">
                                   <span id="msgPassword" class="valid-block m-b-none"><i class="fa fa-info-circle"></i></span>
                                </div>
                            </div>
                              <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-8"> 
                                 <input id="fullname" type="text" name="fullname" class="form-control"   onblur="checkName(this.value)" onfocus="MyFunctionFullName()">
                                   <span id="msgfullname" ></span>
                                </div>
                            </div>
       
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-mail：</label>
                                <div class="col-sm-8">
                                    <input  id="email" name="email" class="form-control" type="email"  onblur="checkEmai(this.value)" onfocus="MyFunctionEmail()">
                                          <span id="msgEmai" ></span>
                                    
                                </div>
                            </div>
                                  <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
                                    <input name="mobile"  id ="mobile" onblur="checkMoile(this.value)" onfocus="MyFunctionPhone()" class="form-control" type="text">
                                      <span id="msg" ></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                 <input type="submit" value="提交" > <span id="notnull"></span>
                                </div>
                            </div>
                        </form>
                    </div>



      <script src="<%=basePath%>/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=basePath%>/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath%>/hplus/js/content.min.js?v=1.0.0"></script>
    <script src="<%=basePath%>/hplus/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>/hplus/js/plugins/validate/messages_zh.min.js"></script>
    <script src="<%=basePath%>/hplus/js/demo/form-validate-demo.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>