<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String basePath = request.getContextPath();
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>app用户列表</title>
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
	                    <a data-toggle="modal" class="btn btn-primary" id="but_edit">冻结/封号</a>
                    
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
                
                <!-- 冻结/封号 开始 -->
                <div id="editUser" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="up_myModalLabel">冻结/封号</h2>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">用户状态</label>
                                    <select id="up_userStatus">
                                        <option value="1">正常</option>
                                        <option value="2">冻结</option>
                                        <option value="3">封号</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="edit">修改</button>
                            </div>
                            <input type="hidden" id="up_userId" name="userId" >
                        </div>
                    </div>
                </div>
                <!-- 冻结/封号 结束 -->
                
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
            url:'<%=basePath %>/appUser/appUserList',
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
                    label:'用户状态',
                    name:'state',
                    index:'state',
                    align:'center',
                    width:'150%',
                    formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (cellvalue == 1){
                            return "正常";
                        } else if (cellvalue == 2){
                            return "冻结";
                        } else if (cellvalue == 3){
                        	return "封号";
                        }
                    }
                },
                {
                    label:'用户状态',
                    name:'status',
                    index:'status',
                    align:'center',
                    width:'150%',
                    hidden:true,
                },
                {
                    label:'用户注册时间',
                    name:'registerTime',
                    index:'registerTime',
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
                    label:'年龄',
                    name:'age',
                    index:'age',
                    width:'100%',
                    align:'center',
                },
                {
                    label:'性别',
                    name:'sex',
                    index:'sex',
                    align:'center',
                    width:'100%',
                    formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (cellvalue == 0){
                            return "未知";
                        } else if (cellvalue == 1){
                            return "男";
                        } else if (cellvalue == 2){
                        	return "女";
                        }
                    }
                },
                {
                    label:'用户头像',
                    name:'icon',
                    index:'icon',
                    align:'center',
                    formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
						if(cellvalue!=null && cellvalue!="" && cellvalue!="undefined"){
							return "<img src='"+cellvalue+"' width='100px' height='100px' alt='图片损坏'/>";
						}else{
							return "";
						}
					}
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
                    label:'总容量',
                    name:'totalCapacity',
                    index:'totalCapacity',
                    align:'center',
                },
                {
                    label:'已用容量',
                    name:'userdCapacity',
                    index:'userdCapacity',
                    align:'center',
                }
            ],
            pager:'#pager_role',
            viewrecords: true,
            rownumbers:true,
            sortname: "register_time",
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

  	//冻结/封号
    $("#but_edit").bind("click",function(){
        //事件初始化
        $("#edit").unbind("click");

        var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
        var product= jQuery("#table_role").jqGrid('getRowData', selectedId);
        if (selectedId != null && selectedId.length>0){
            //显示修改框
            $("#editUser").modal('show');
            $("#up_userId").val(product.userId);
            $("#up_userStatus option[value='"+product.status+"']").attr("selected", true);
            //校验数据
            $("#edit").bind("click",function(){
                $.ajax({
                    url:"<%=basePath%>/appUser/editUser",
                    async : false,
                    datatype:'json',
                    type:'get',
                    data:{
                        userId:$("#up_userId").val(),
                        state:$("#up_userStatus").val()
                    },
                    success:function(d){
                        if(eval(d)=='success'){
                            $("#editUser").modal('hide');
                            swal("修改","修改成功！","success");
                            $("#table_role").trigger("reloadGrid");
                        }else{
                            swal("修改失败","系统繁忙！","error");
                        }
                    },error:function(){
                        swal("修改失败","网络异常！","error");
                    }
                });
            });
        }else{
            swal("冻结/封号","请选择一个用户！","info");
        }
    });
  	
  	//查询条件传值
    $("#serc").click(function(){
    	var username=$("#username").val();
    	var email=$("#email").val();
    	var phone=$("#phone").val();

    	/* if((typeof(username)=="undefined" || username=="") && (typeof(email)=="undefined" || email=="") &&
    			(typeof(phone)=="undefined" || phone=="")){
            swal("查询条件","请输入查询条件","error");
            return;
        } */
    	
    	$("#table_role").jqGrid('setGridParam',{
    	    datatype:'json',
    	    url:'<%=basePath%>/appUser/appUserList',
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