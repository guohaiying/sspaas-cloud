<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String basePath = request.getContextPath();
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>app用户登录记录</title>
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

    <style>
        /* Additional style to fix warning dialog position */
        #alertmod_table_list_2 {
            top: 900px !important;
        }
    </style>
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
    <script src="<%=basePath %>/js/jquery-ui-1.9.2.custom.js"></script>
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title" id="user_button">
               		<form class="form-inline">
			            <div class="form-group">
						    <label for="exampleInputEmail2">用户昵称：</label>
						    <input type="text" class="form-control" id="username" style="width: 200px;">
						</div>
						<div class="form-group">
						    <label for="exampleInputEmail2">邮箱：</label>
						    <input type="text" class="form-control" id="email" style="width: 200px;">
						</div>
						<div class="form-group">
						    <label for="exampleInputEmail2">手机号：</label>
						    <input type="text" class="form-control" id="phone" style="width: 200px;">
						</div>
	           			<button type="button" class="btn btn-primary" id="serc">查询</button>
        			</form>
                </div>
                
                <div class="ibox-content">
                    <!-- 数据表格 -->
                    <div class="jqGrid_wrapper" id="divWidth">
                        <table id="table_role"></table>
                        <div id="pager_role"></div>
                    </div>
            	</div>
        </div>
    </div>
</div>
    </div>

<script type="text/javascript">

    $(function(){
        $.jgrid.defaults.styleUI="Bootstrap";
        //分页查询数据(自定义结果)
        $("#table_role").jqGrid({
            url:'<%=basePath %>/appUser/loginRecord',
            datatype:"json",
            mtype:"GET",
            height:$(window).height()-239,
            autowidth:true,
            shrinkToFit:true,
            rowNum:8,
            rowList:[8,20,30,50],
            colModel:[
                {
                    label:'userId',
                    name:'userId',
                    index:'userId',
                    sortable:false,
                    hidden:true,
                    width:'10%',
                    align:'center',
                },
                {
                    label:'昵称',
                    name:'username',
                    index:'username',
                    align:'center',
                },
                {
                    label:'手机号',
                    name:'phone',
                    index:'phone',
                    width:'150%',
                    align:'center',
                },
                {
                    label:'邮箱',
                    name:'email',
                    index:'email',
                    width:'200%',
                    align:'center',
                },
                {
                    label:'用户身份',
                    name:'type',
                    index:'type',
                    align:'center',
                    formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (cellvalue == 0){
                            return "未知";
                        } else if (cellvalue == 1){
                            return "普通用户";
                        } else if (cellvalue == 2){
                        	return "商家";
                        }else{
                        	return "";
                        }
                    }
                },
                {
                    label:'登录时间',
                    name:'loginTime',
                    index:'loginTime',
                    width:'200%',
                    align:'center',
                    formatter:"date",
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
                {
                    label:'退出时间',
                    name:'quitTime',
                    index:'quitTime',
                    width:'200%',
                    align:'center',
                    formatter:"date",
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
                {
                    label:'登录地点',
                    name:'loginPlace',
                    index:'loginPlace',
                    width:'100%',
                    align:'center',
                },
                {
                    label:'登录ip',
                    name:'loginIp',
                    index:'loginIp',
                    align:'center',
                },
                {
                    label:'设备',
                    name:'equipment',
                    index:'equipment',
                    align:'center',
                    formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (cellvalue == 0){
                            return "未知";
                        } else if (cellvalue == 1){
                            return "网页";
                        } else if (cellvalue == 2){
                        	return "ios";
                        } else if (cellvalue == 3){
                        	return "android";
                        }
                    }
                },
                {
                    label:'设备标识',
                    name:'uuid',
                    index:'uuid',
                    align:'center',
                }
            ],
            pager:'#pager_role',
            viewrecords: true,
            rownumbers:true,
            sortname: "login_time",
            sortorder: "desc",
            emptyrecords : "没有符合条件的数据",
            jsonReader:{
                id:"userId",
                root: "list",
                page: "page",
                total: "total",
                records: "records",
                repeatitems : false
            },
            add:false,
            edit:false,
        });
        $("#table_role").jqGrid("navGrid","#pager_role",{edit:false,add:false,del:false,search:false},{reloadAfterSubmit:true,});

        $(window).bind("resize",function(){
            var width=$("#divWidth").width();
            $("#table_role").setGridWidth(width);
        });
    });
  	
  	//查询条件传值
    $("#serc").click(function(){
    	var username=$("#username").val();
    	var email=$("#email").val();
    	var phone=$("#phone").val();

    	if((typeof(username)=="undefined" || username=="") && (typeof(email)=="undefined" || email=="") &&
    			(typeof(phone)=="undefined" || phone=="")){
            swal("查询条件","请输入查询条件","error");
            return;
        }
    	
    	$("#table_role").jqGrid('setGridParam',{
    	    datatype:'json',
    	    url:'<%=basePath%>/appUser/loginRecord',
    	    page:1,
    	    postData:{
    	    	'username':username,
    	    	'email':email,
    	    	'phone':phone
    	    }
    	}).trigger("reloadGrid"); //重新载入
    });
</script>

</body>
</html>