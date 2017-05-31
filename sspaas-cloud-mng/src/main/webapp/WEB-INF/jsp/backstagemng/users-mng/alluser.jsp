<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <title>后台用户列表</title>
    <link rel="shortcut icon" href="<%=basePath %>/favicon.ico">
    <link href="<%=basePath %>/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <!-- jqgrid-->
    <link href="<%=basePath %>/hplus/css/plugins/jqgrid/ui.jqgridffe4.css?0820" rel="stylesheet">

    <!-- treeview -->
    <link href="<%=basePath %>/hplus/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">

    <link href="<%=basePath%>/css/jquery-ui-1.9.2.custom.css" rel="stylesheet">

    <!-- Sweet Alert -->
    <link href="<%=basePath %>/hplus/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="<%=basePath %>/hplus/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath %>/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">

    <!-- plupload -->
    <link rel="stylesheet" href="<%=basePath %>/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css">
    <script type="text/javascript" src="<%=basePath %>/plupload/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
    <script type="text/javascript" src="<%=basePath %>/plupload/js/i18n/zh_CN.js"></script>

    <script src="<%=basePath %>/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=basePath %>/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath %>/hplus/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="<%=basePath %>/hplus/js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
    <script src="<%=basePath %>/hplus/js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
    <script src="<%=basePath %>/hplus/js/content.min.js?v=1.0.0"></script>
    <script src="<%=basePath %>/hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="<%=basePath %>/hplus/js/plugins/treeview/bootstrap-treeview.js"></script>
    <link rel="stylesheet" href="<%=basePath %>/js/zTree_v3-master/css/demo.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath %>/js/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=basePath %>/js/zTree_v3-master/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/jquery-ui-1.9.2.custom.js"></script>
</head>

<body class="gray-bg">
    <script type="text/javascript">
        function Modify(){

            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var role= jQuery("#list4").jqGrid('getRowData', selectedId);

            $("#del").dialog({
                height:300,
                width:400,
                resizable:false,
                modal:true,  //这里就是控制弹出为模态
                buttons:{
                    "取消":function(){$(this).dialog("close");},
                    "确定":function(){
                        $.ajax({
                            url:'<%=basePath %>/usermng/background/delUser?userId='+role.userId,
                            contentType : 'application/json',
                            dataType:'json',
                            success:function(result){
                                location.reload() ;
                            }
                        });
                        $(this).dialog("close");
                    }

                }
            });
        }

        function updateUser() {
            //单击修改链接的操作
            //事件初始化
//            $("#edit").unbind("click");

            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var role = jQuery("#list4").jqGrid('getRowData', selectedId);

            var model = jQuery("#list4").jqGrid('getRowData', role.userId);
            $("#txloginName").val(model.loginName);
            $("#txtfullname").val(model.fullname);
            $("#txtmobile").val(model.mobile);
            $("#userRole option[value='"+model.roleId+"']").attr("selected", true);
                $("#modifyform").dialog({
                height: 300,
                width: 400,
                resizable: false,
                modal: true,  //这里就是控制弹出为模态
                buttons: {
                    "取消": function () {
                        $(this).dialog("close");
                    },
                    "确定": function () {
                        var loginName = $("#txloginName").val();
                        var fullname = $("#txtfullname").val();
                        var mobile = $("#txtmobile").val();
                        var roleId = $("#userRole").val();
                        $.ajax({
                            url: '<%=basePath %>/usermng/background/updateUser',
                            async : false,
                            datatype:'json',
                            type:'get',
                            data: {
                                userId:role.userId,
                                loginName: loginName,
                                fullname: fullname,
                                mobile: mobile,
                                roleId: roleId,
                            },
                            success: function (result) {
                                location.reload();
                            }
                        });
                        $(this).dialog("close");
                    }

                }
            });
        }

        function addUser() {
            window.location.href="<%=basePath %>/usermng/background/addUserToPage";
        }

        function updateRole(){
            var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            var role= jQuery("#list4").jqGrid('getRowData', selectedId);
            document.getElementById("userId").value = role.userId;
            document.forms[0].submit();

        }

        $(document).ready(function(){
            $.jgrid.defaults.styleUI="Bootstrap";
            $("#list4").jqGrid({
                url:'<%=basePath %>/usermng/background/select',
                datatype:"json", //数据来源，本地数据
                //postData:mydata,
                mtype:"GET",//提交方式
//                height:420,//高度，表格高度。可为数值、百分比或'auto'
                //width:1000,//这个宽度不能为百分比
                height:$(window).height()-239,
                autowidth:true,//自动宽
                shrinkToFit:true,
                colModel:[
                    {label:"用户Id",name:'userId',index:'userId', width:'15%',align:'center',hidden: true},
                    {label:"用户名",name:'loginName',index:'loginName', width:'15%',align:'center'},
                    {label:"用户姓名",name:'fullname',index:'fullname', width:'35%',align:'center'},
                    {label:"手机号",name:'mobile',index:'mobile', width:'35%',align:'center'},
                    {label:"登录时间",name:'loginTime',index:'loginTime', width:'35%',align:'center',formatter:"date",
                        formatter:function(cellvalue, options, rowObject){
                            if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                                return "";
                            }
                            var date = new Date(cellvalue);
                            var minutes;
                            if (date.getMinutes()<10) {
                                minutes='0'+date.getMinutes();
                            }else{
                                minutes=date.getMinutes();
                            }
                            return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+minutes+":"+date.getSeconds();
                        }
                    },
                    {label:"上次登录时间",name:'lastLoginTime',index:'lastLoginTime', width:'35%',align:'center',formatter:"date",
                        formatter:function(cellvalue, options, rowObject){
                            if (typeof(cellvalue)=="undefined" || typeof(cellvalue)=="" || cellvalue==0){
                                return "";
                            }
                            var date = new Date(cellvalue);
                            var minutes;
                            if (date.getMinutes()<10) {
                                minutes='0'+date.getMinutes();
                            }else{
                                minutes=date.getMinutes();
                            }
                            return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+minutes+":"+date.getSeconds();
                        }
                    },
                    {label:"角色",name:'roleName',index:'roleName', width:'35%',align:'center'},
                    {label:"角色id",name:'roleId',index:'roleId', width:'35%',align:'center',hidden: true},
                    /*{label:"编辑",name:'userId',index:'userId', width:'25%', align:"center",formatter: function (cellvalue) { return "<a href=\"#\" style=\"color:#f60\" onclick=\"updateUser('" + cellvalue + "')\">编辑用户</a>" }},
                    {label:"删除",name:'userId',index:'userId', width:'25%', align:"center",formatter: function (cellvalue) { return "<a href=\"#\" style=\"color:#f60\" onclick=\"Modify('" + cellvalue + "')\">删除用户</a>" }},
                    {label:"修改角色",name:'userId',index:'userId', width:'25%', align:"center",formatter: function (cellvalue) { return "<a href=\"#\" style=\"color:#f60\" onclick=\"updateRole('" + cellvalue + "')\">修改用户角色</a>" }},*/
                    /*{label:"修改权限",name:'userId',index:'userId', width:'25%', align:"center",formatter: function (cellvalue) { return "<a href=\"#\" style=\"color:#f60\" onclick=\"updateRight('" + cellvalue + "')\">修改用户权限</a>" }},*/
                ],
                pager:'#gridPager',
                //pager:$('#gridPager'),
                //rownumbers:true,//添加左侧行号
                //altRows:true,//设置为交替行表格,默认为false
                //sortname:'createDate',
                //sortorder:'asc',
                viewrecords: true,//是否在浏览导航栏显示记录总数
                rowNum:30, //每页显示记录数
                //rowTotal:2, //表色一次性导入数据的最大行数
                //viewrecords: true, //是否显示行数
                rownumbers: true,
                sortorder: "desc",
                // sortname : 'inv.invitation_time',
                //loadonce:true, //设置成true，表示一次性导入数据；默认为false
                //rownumbers: true, //设置成false，就不显示行号；否则反之
                //grouping: true, //默认false 不分组，反之亦然
                rowList:[30,50,80],//用于改变显示行数的下拉列表框的元素数组。
                jsonReader:{
                    id: "userId",//设置返回参数中，表格ID的名字为blackId
                    root: "list", // json中代表实际模型数据的入口
                    page: "page", // json中代表当前页码的数据
                    total: "total", // json中代表页码总数的数据
                    records: "records", // json中代表数据行总数的数据
                    repeatitems : false
                },
                add:false,
                edit:false,
            });
            $("#list4").jqGrid("navGrid","#gridPager",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

            $(window).bind("resize",function(){
                var width=$("#divWidth").width();
                $("#list4").setGridWidth(width);
            });
        });
    </script>

        <div class="ibox-title" id="user_button">
            <a data-toggle="modal" class="btn btn-primary" onclick="addUser()">添加用户</a>
            <a data-toggle="modal" class="btn btn-primary" id="btn_edit" onclick="updateUser()">编辑用户</a>
            <a data-toggle="modal" id="btn_delete" class="btn btn-primary" onclick="Modify()">删除用户</a>
            <%--<a data-toggle="modal" id="btn_authorize" class="btn btn-primary" onclick="updateRole()">修改用户角色</a>--%>
        </div>

<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-content">
                    <div class="jqGrid_wrapper" id="divWidth">
                        <table id="list4"></table>
                        <div id="gridPager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

        <div id="del" title="删除该用户" style="display:none;">  <!-- 该Div的作用就是当点击jqGrid表格中的修改链接时弹出的dialog, 注意是在上面的Modify(id)函数中给下面的input赋值 -->
            <p style="content: '';"> 确定要删除用户？</p>
        </div>

        <div id="modifyform" title="修改用户信息" style="display:none;">  <!-- 该Div的作用就是当点击jqGrid表格中的修改链接时弹出的dialog, 注意是在上面的Modify(id)函数中给下面的input赋值 -->
            <p>登录名：<input type="text" size="20" id="txloginName" /></p>
            <p>真实姓名：<input type="text" id="txtfullname" /></p>
            <p>手机号：<input type="text" id="txtmobile" /></p>
            <p>用户角色：
                <select id="userRole">
                    <%--<option value="${requestScope.userRole.roleId}">${requestScope.userRole.roleName}</option>--%>
                    <c:forEach items="${requestScope.role}" var="item">
                        <option value="${item.roleId}">${item.roleName}</option>
                    </c:forEach>
                </select>
            </p>
        </div>

		<form action="allRole" method="post">
	        <input type="hidden" name="userId" id ="userId">
        </form>
</body>
</html>