<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String basePath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>云盘后台|首页</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="hplus/css/animate.min.css" rel="stylesheet">
    <link href="hplus/css/style.min.css" rel="stylesheet">
    <script src="<%=basePath%>/hplus/js/jquery.min.js?v=2.1.4"></script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" width="200" height="200" class="img-circle"
                                   src="images/login/head_120.png"/></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                               <span class="clear">
                               <span class="block m-t-xs"><strong
                                       class="font-bold">${sessionScope.userinfo.fullname}</strong></span>
                               <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                               </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="<%=basePath%>/usermng/background/exitLogin">安全退出</a>
                            </li>
                            </li>
                        </ul>
                    </div>
                    <div class="logo-element">云盘
                    </div>
                	<li>
                    	<a class="J_menuItem" href="indexdate"><i class="fa fa-home"></i> <span class="nav-label">主页</span></a>
                    </li>
                    
                    
                    
                     <li><a href='#'><i class='fa fa-edit'></i> <span class='nav-label'>文件管理</span><span class='fa arrow'></span></a>
                    	<ul class='nav nav-second-level'>
                    		<li><a class='J_menuItem' href="file/fileList">文件管理</a></li>
                    		<li><a class='J_menuItem' href="fileType/fileTypeList">文件类型管理</a></li>
                    		<li><a class='J_menuItem' href="report/toReportList">举报资源审查</a></li>
                    		<li><a class='J_menuItem' href="reportType/toReportTypeList">举报类型管理</a></li>
                    	</ul>
                    </li>
                    
                    <li><a href='#'><i class='fa fa-edit'></i> <span class='nav-label'>容量管理</span><span class='fa arrow'></span></a>
                    	<ul class='nav nav-second-level'>
                    		<li><a class='J_menuItem' href="capacity/capacityJsp">容量类型管理</a></li>
<!--                     		<li><a class='J_menuItem' href="userpurchasecapacity/usercapacityJsp">用户订单管理</a></li> -->
                    	</ul>
                    </li>
                    
                    
                    
                    <li><a href='#'><i class='fa fa-edit'></i> <span class='nav-label'>反馈管理</span><span class='fa arrow'></span></a>
                    	<ul class='nav nav-second-level'>
                    		<li><a class='J_menuItem' href="feedback/feedBackList">反馈管理</a></li>
                    		<li><a class='J_menuItem' href="feedbackType/feedBackTypeList">反馈类型管理</a></li>
                    	</ul>
                    </li>
                    
                    <li><a href='#'><i class='fa fa-edit'></i> <span class='nav-label'>用户管理</span><span class='fa arrow'></span></a>
                    	<ul class='nav nav-second-level'>
                    		<li><a class='J_menuItem' href="appUser/allappuser">用户管理</a></li>
                    		<li><a class='J_menuItem' href="appUser/userLoginRecord">登录记录</a></li>
                    	</ul>
                    </li>
                    
                    <li><a href='#'><i class='fa fa-edit'></i> <span class='nav-label'>版本管理</span><span class='fa arrow'></span></a>
                    	<ul class='nav nav-second-level'>
                    		<li><a class='J_menuItem' href="version/version">版本管理</a></li>
                    	</ul>
                    </li>
                    
                    <li><a href='#'><i class='fa fa-edit'></i> <span class='nav-label'>系统管理</span><span class='fa arrow'></span></a>
                    	<ul class='nav nav-second-level'>
                    		<li><a class='J_menuItem' href="usermng/background/findAllUser">用户管理</a></li>
<!--                     		<li><a class='J_menuItem' href="role/roleSys">角色管理</a></li> -->
                    		<li><a class='J_menuItem' href="usermng/background/updateUserPassword">修改用户密码</a></li>
                    	</ul>
                    </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
                        class="fa fa-bars"></i> </a>
                    <form role="s" class="navbar-form-custom" method="post" action="404.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="wd"
                                   id="top-search">
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="<%=basePath%>/usermng/background/exitLogin" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <!-- 右侧iframe部分 -->
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="indexdate" frameborder="0"
                    data-id="index_v1.html" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">云盘后台管理 &copy; 2015-2016 <a href="http://sspaas.com/" target="_blank">sspaas</a>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <!--右侧边栏结束-->

</div>
<script src="hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="hplus/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="hplus/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="hplus/js/plugins/layer/layer.min.js"></script>
<script src="hplus/js/hplus.min.js?v=4.1.0"></script>
<script type="text/javascript" src="hplus/js/contabs.min.js"></script>
<script src="hplus/js/plugins/pace/pace.min.js"></script>
</body>
</html>