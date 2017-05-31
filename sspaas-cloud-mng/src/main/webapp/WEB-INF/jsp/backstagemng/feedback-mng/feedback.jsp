<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<title>反馈列表</title>
<link rel="shortcut icon" href="<%=basePath%>/favicon.ico">
<link href="<%=basePath%>/hplus/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="<%=basePath%>/hplus/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<!-- jqgrid-->
<link
	href="<%=basePath%>/hplus/css/plugins/jqgrid/ui.jqgridffe4.css?0820"
	rel="stylesheet">

<!-- treeview -->
<link
	href="<%=basePath%>/hplus/css/plugins/treeview/bootstrap-treeview.css"
	rel="stylesheet">

<link href="<%=basePath%>/css/jquery-ui-1.9.2.custom.css"
	rel="stylesheet">

<!-- Sweet Alert -->
<link href="<%=basePath%>/hplus/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<link href="<%=basePath%>/hplus/css/animate.min.css" rel="stylesheet">
<link href="<%=basePath%>/hplus/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">

<!-- plupload -->
<link rel="stylesheet"
	href="<%=basePath%>/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css">
<script type="text/javascript"
	src="<%=basePath%>/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/plupload/js/i18n/zh_CN.js"></script>

<script src="<%=basePath%>/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=basePath%>/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="<%=basePath%>/hplus/js/plugins/peity/jquery.peity.min.js"></script>
<script
	src="<%=basePath%>/hplus/js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
<script
	src="<%=basePath%>/hplus/js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
<script src="<%=basePath%>/hplus/js/content.min.js?v=1.0.0"></script>
<script
	src="<%=basePath%>/hplus/js/plugins/sweetalert/sweetalert.min.js"></script>
<script
	src="<%=basePath%>/hplus/js/plugins/treeview/bootstrap-treeview.js"></script>
<link rel="stylesheet"
	href="<%=basePath%>/js/zTree_v3-master/css/demo.css" type="text/css">
<link rel="stylesheet"
	href="<%=basePath%>/js/zTree_v3-master/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="<%=basePath%>/js/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/js/jquery-ui-1.9.2.custom.js"></script>
</head>

<body class="gray-bg">
	<script type="text/javascript">
        $(document).ready(function(){
            $.jgrid.defaults.styleUI="Bootstrap";
            $("#list4").jqGrid({
                url:'<%=basePath%>/feedback/selectAllList',
                datatype:"json", //数据来源，本地数据
                //postData:mydata,
                mtype:"GET",//提交方式
//                height:420,//高度，表格高度。可为数值、百分比或'auto'
                //width:1000,//这个宽度不能为百分比
                height:$(window).height()-239,
                autowidth:true,//自动宽
                shrinkToFit:true,
                colModel:[
                    {label:"反馈Id",name:'feedbackId',index:'feedbackId', width:'15%',align:'center',hidden: true},
                    {label:"用户Id",name:'userId',index:'userId', width:'15%',align:'center',hidden: true},
                    {label:"用户名",name:'username',index:'username', width:'15%',align:'center'},
                    {label:"标题",name:'title',index:'title', width:'15%',align:'center'},
                    {label:"内容",name:'content',index:'content', width:'35%',align:'center'},
                    {label:"类型",name:'type',index:'type', width:'35%',align:'center'},
                    {label:"添加时间",name:'addTime',index:'addTime', width:'35%',align:'center',formatter:"date",
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
                    {label:"处理状态",name:'state',index:'state', width:'35%',align:'center',
						formatter:function(cellvalue, options, rowObject){
							if(cellvalue==1){
								return "已处理";
							}else if(cellvalue==2){
								if(rowObject.fstate==1){
									return "<font style='color:red'>未处理</font>";
								}else{
									return "未处理";
								}
							}else{
								return "";
							}
						}
                    },
                    {label:"反馈状态",name:'fstate',index:'fstate', width:'35%',align:'center',
						formatter:function(cellvalue, options, rowObject){
							if(cellvalue==1){
								return "正常";
							}else if(cellvalue==2){
								return "删除";
							}else{
								return "";
							}
						}
                    }
                ],
                pager:'#gridPager',
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
                sortname : 'state',
                //loadonce:true, //设置成true，表示一次性导入数据；默认为false
                //rownumbers: true, //设置成false，就不显示行号；否则反之
                //grouping: true, //默认false 不分组，反之亦然
                rowList:[30,50,80],//用于改变显示行数的下拉列表框的元素数组。
                jsonReader:{
                    id: "feedbackTypeId",//设置返回参数中，表格ID的名字为blackId
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
            
            //类型
            $.ajax({
                url:'<%=basePath %>/feedbackType/getFeedbackType',
                type:"post",
                async : true, //同步异步请求
                cache:false, //是否使用缓存
                datatype:'json',
                success:function(r){
	            	 $("#type").empty(); 
	                 var dataObj=eval("("+r+")");//转换为json对象
	                 $("#type").append("<option value=''>全部</option>");
	                 for(var i=0;i<dataObj.length;i++){
	                     $("#type").append("<option value="+dataObj[i].feedbackTypeId+">"+dataObj[i].name+"</option>");
	                 }
                }
            }); 
            
          //查询条件传值
            $("#serc").click(function(){
            	var username=$("#username").val();
            	var title=$("#title").val();
            	var state=$("#state").val();
            	var fstate=$("#fstate").val();
            	var type=$("#type").val();
            	var content=$("#content").val();

            	$("#list4").jqGrid('setGridParam',{  
            	    datatype:'json',
            	    url:'<%=basePath%>/feedback/selectAllList',
            	    page:1,
            	    postData:{
            	    	'username':username,
            	    	'title':title,
            	    	'state':state,
            	    	'fstate':fstate,
            	    	'type':type,
            	    	'content':content
            	    } 
            	}).trigger("reloadGrid"); //重新载入 
            });
          
          //查询条件传值
            $("#feedbackHandle").click(function(){
           
            	var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            	var model = jQuery("#list4").jqGrid('getRowData', selectedId);
            	
            	if(selectedId<=0){
            		alert("请选择要处理的行数");
            		return;
            	}
            	if(model.fstate=='删除'){
            		alert("反馈已删除，请勿操作");
            		return;
            	}
            	
            	if(model.state=='已处理'){
            		alert("反馈已处理，请勿重复处理");
            		return;
            	}
            	
            	
            	$.ajax({
                    url:'<%=basePath %>/feedback/handleFeedback?feedbackId='+model.feedbackId,
                    type:"post",
                    async : true, //同步异步请求
                    cache:false, //是否使用缓存
                    datatype:'json',
                    success:function(r){
                    	$("#list4").trigger("reloadGrid");  
                    }
                }); 
            });
            
            
        });
      
        
	</script>

	<div class="ibox-title" id="user_button">
		<form class="form-inline">
            <div class="form-group">
			    <label for="exampleInputEmail2">用户名称：</label>
			    <input type="text" class="form-control" id="username" style="width: 200px;">
			</div>
			<div class="form-group">
			    <label for="exampleInputEmail2">标题：</label>
			    <input type="text" class="form-control" id="title" style="width: 200px;">
			</div>
			<div class="form-group">
			    <label for="exampleInputEmail2">内容：</label>
			    <input type="text" class="form-control" id="content" style="width: 200px;">
			</div>
			<div class="form-group">
			    <label for="exampleInputEmail2">类型：</label>
			    <select  data-toggle="modal" class="btn btn-primary" id="type" >
	        	</select>
			</div>
			<div class="form-group">
			    <label for="exampleInputEmail2">处理状态：</label>
				<select  data-toggle="modal" class="btn btn-primary" id="state" >
					<option value=''>全部</option>
					<option value='1'>已处理</option>
					<option value='2'>未处理</option>
	        	</select>
			</div>
			<div class="form-group">
			    <label for="exampleInputEmail2">反馈状态：</label>
			    <select  data-toggle="modal" class="btn btn-primary" id="fstate" >
					<option value=''>全部</option>
					<option value='1'>正常</option>
					<option value='2'>删除</option>
	        	</select>
			</div>
            <button type="button" class="btn btn-default" id="serc">查询</button>
            <button type="button" class="btn btn-default" id="feedbackHandle">反馈处理</button>
       </form>
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


	<!-- 添加反馈类型开始  -->
	<div id="addProductStyle" class="modal fade" tabindex="-1"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="myModalLabel">添加反馈类型</h2>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-sm-5 control-label">名称</label> <input
							type="text" id="name" name="name">
					</div>

					<div class="form-group">
						<label class="col-sm-5 control-label">描述</label>
						<textarea rows="5" cols="22" id="descript" name="descript"></textarea>
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="add"
							onclick="toVaild()">添加</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加反馈类型结束 -->
	
	
	<!-- 添加反馈类型开始  -->
	<div id="editType" class="modal fade" tabindex="-1"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="myModalLabel">修改反馈类型</h2>
				</div>
				<div class="modal-body">
					<input type="hidden" name='feedbackTypeId' id='feedbackTypeId' />

					<div class="form-group">
						<label class="col-sm-5 control-label">名称</label> <input
							type="text" id="up_name" name="name">
					</div>

					<div class="form-group">
						<label class="col-sm-5 control-label">描述</label>
						<textarea rows="5" cols="22" id="up_descript" name="descript"></textarea>
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="add"
							onclick="up_toVaild()">修改</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加反馈类型结束 -->

	<div id="del" title="删除" style="display: none;">
		<!-- 该Div的作用就是当点击jqGrid表格中的修改链接时弹出的dialog, 注意是在上面的Modify(id)函数中给下面的input赋值 -->
		<p style="content: '';">确定要删除该类型？</p>
	</div>

</body>
</html>