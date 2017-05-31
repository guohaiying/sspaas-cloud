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
    <title>举报资源列表</title>
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
     

        $(document).ready(function(){
            $.jgrid.defaults.styleUI="Bootstrap";
            $("#list4").jqGrid({
                url:'<%=basePath %>/report/reportList',
                datatype:"json", //数据来源，本地数据
                //postData:mydata,
                mtype:"GET",//提交方式
//                height:420,//高度，表格高度。可为数值、百分比或'auto'
                //width:1000,//这个宽度不能为百分比
                height:$(window).height()-239,
                autowidth:true,//自动宽
                shrinkToFit:true,
                colModel:[
                    {label:"举报资源Id",name:'reportId',index:'reportId', width:'15%',align:'center',hidden: true},
                    {label:"用户Id",name:'userId',index:'userId', width:'15%',align:'center',hidden: true},
                    {label:"举报用户名称",name:'userName',index:'userName', width:'35%',align:'center'},
                    {label:"举报类型",name:'reportTypeName',index:'reportTypeName', width:'35%',align:'center',hidden:true},
                    {label:"文件Id",name:'fileId',index:'fileId', width:'35%',align:'center',hidden:true},
                    {label:"文件名称",name:'fileName',index:'fileName', width:'35%',align:'center'},
                    {label:"文件类型Id",name:'fileTypeId',index:'fileTypeId', width:'35%',align:'center',hidden: true},
                    {label:"文件类型",name:'fileTypeName',index:'fileTypeName', width:'35%',align:'center'},
                    {label:"举报内容",name:'descript',index:'descript', width:'35%',align:'center'},
                    {label:"举报类型",name:'reportTypeName',index:'reportTypeName', width:'35%',align:'center'},
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
                    {label:"状态",name:'state',index:'state', width:'15%',align:'center',hidden: true},
                    {label:"状态",name:'state',index:'state', width:'35%',align:'center',
						formatter:function(cellvalue, options, rowObject){
							if(cellvalue==1){
								return "已处理";
							}else if(cellvalue==2){
								return "未处理";
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
                sortname : 'addTime',
                //loadonce:true, //设置成true，表示一次性导入数据；默认为false
                //rownumbers: true, //设置成false，就不显示行号；否则反之
                //grouping: true, //默认false 不分组，反之亦然
                rowList:[30,50,80],//用于改变显示行数的下拉列表框的元素数组。
                jsonReader:{
                    id: "reportId",//设置返回参数中，表格ID的名字为blackId
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
            
            //审核文件类型窗口
            $("#btn_audit").bind("click",function(){
            	
            	var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            	var model = jQuery("#list4").jqGrid('getRowData', selectedId);
            	
            	if(selectedId<=0){
            		alert("请选择要编辑的行数");
            		return;
            	}
            	var fileId = model.fileId;
				$.ajax({
					url:'<%=basePath %>/report/findReportFile',
	                type:"post",
	                data:"fileId="+fileId,
	                async : true, 
	                cache:false, 
	                datatype:'json',
	                success:function(data){
	                	$("#reportFileUserId").val(data.userId);
	                	$("#reportFileUserName").text(data.userName);
	                	var userState = $("#reportFileUserState > option");
	                	for( var i=0;i < userState.length;i++){
	                		
	                		 if(userState[i].value==data.userState){
	                			 userState[i].selected = true;
	                	    }  
	                	 }  
	                	 var fileState =$("#reportFileFileState > option");
	                	 for( var i=0;i < fileState.length;i++){
		                		
	                		 if(fileState[i].value==data.fileState){
	                			 fileState[i].selected = true;
	                	    }  
		               	}  
	                	$("#reportFileOldName").text(data.oldName);
	                	$("#reportFileUploadName").text(data.uploadName);
	                	$("#reportFileFilePath").attr('href',data.filePath);
	                     
	                }
				});   
				var reportState = $("#reportState > option");
				for(var i=0;i<reportState.length;i++){
					if(reportState[i].text==model.state){
						reportState[i].selected = true;
           	    }  
				}
				$("#fileId").val(fileId);
				$("#reportId").val(model.reportId);
            	$("#modifyform").modal('show');
            });
          //查询条件传值
            $("#serc").click(function(){
            	var userName=$("#userName").val();
            	var fileName=$("#fileName").val();
            	var reportTypeId=$("#reportTypeVal").val();
            	var fileTypeId=$("#fileTypeVal").val();
				var state = $("#stateVal").val();
            	$("#list4").jqGrid('setGridParam',{  
            	    datatype:'json',
            	    url:'<%=basePath%>/report/reportList',
            	    page:1,
            	    postData:{
            	    	'userName':userName,
            	    	'fileName':fileName,
            	    	'reportTypeId':reportTypeId,
            	    	'fileTypeId':fileTypeId,
            	    	'state':state
            	    } 
            	}).trigger("reloadGrid"); //重新载入 
            });
            
            //全部举报类型
            $.ajax({
                url:'<%=basePath %>/reportType/getAllReportType',
                type:"post",
                async : true, //同步异步请求
                cache:false, //是否使用缓存
                datatype:'json',
                success:function(data){
                	var dataObj = data;
                	 $("#reportTypeVal").empty(); 
                     $("#reportTypeVal").append("<option value=''>全部举报类型</option>");
                     for(var i=0;i<dataObj.length;i++){
                         $("#reportTypeVal").append("<option value="+dataObj[i].reportTypeId+">"+dataObj[i].name+"</option>");
                     }
                     
                }
            }); 
            //全部文件类型
            $.ajax({
                url:'<%=basePath %>/report/findAllFileType',
                type:"post",
                async : true, //同步异步请求
                cache:false, //是否使用缓存
                datatype:'json',
                success:function(data){
                	var dataObj = data;
                	 $("#fileTypeVal").empty(); 
                     $("#fileTypeVal").append("<option value=''>全部文件类型</option>");
                     for(var i=0;i<dataObj.length;i++){
                         $("#fileTypeVal").append("<option value="+dataObj[i].fileTypeId+">"+dataObj[i].name+"</option>");
                     }
                     
                }
            }); 
          
            //点击文件类型
          $("#fileTypeVal").change(function(){
        	  var fileTypeId = $('#fileTypeVal').val();
          	  var state= $('#stateVal').val();
          	  var reportTypeId = $('#reportTypeVal').val();
            	$("#list4").jqGrid('setGridParam',{  
            	    datatype:'json',
            	    url:'<%=basePath %>/report/reportList?state='+state+'&reportTypeId='+reportTypeId+'&fileTypeId='+fileTypeId,
            	    page:1
            	}).trigger("reloadGrid"); //重新载入 
            });
            //点击举报类型执行函数
          $("#reportTypeVal").change(function(){
        	  var fileTypeId = $('#fileTypeVal').val();
          	  var state= $('#stateVal').val();
           	  var reportTypeId = $('#reportTypeVal').val();
            	$("#list4").jqGrid('setGridParam',{  
            	    datatype:'json',
            	    url:'<%=basePath %>/report/reportList?state='+state+'&reportTypeId='+reportTypeId+'&fileTypeId='+fileTypeId,
            	    page:1
            	}).trigger("reloadGrid"); //重新载入 
            });
            //点击状态
          $("#stateVal").change(function(){
        	  var fileTypeId = $('#fileTypeVal').val();
          	  var state= $('#stateVal').val();
          	  var reportTypeId = $('#reportTypeVal').val();
            	$("#list4").jqGrid('setGridParam',{  
            	    datatype:'json',
            	    url:'<%=basePath %>/report/reportList?state='+state+'&reportTypeId='+reportTypeId+'&fileTypeId='+fileTypeId,
            	    page:1
            	}).trigger("reloadGrid"); //重新载入 
            });
        });
        
        function toAudit(){
        	$("#subForm").submit();
        	return true;
        }
        
    </script>

        <div class="ibox-title">
        <form class="form-inline">
        	<select  data-toggle="modal" class="btn btn-primary" id="reportTypeVal"  >
        	</select>
        	<select  data-toggle="modal" class="btn btn-primary" id="fileTypeVal"  >
        	</select>
            <div class="form-group">
			    <label for="exampleInputEmail2">用户名称：</label>
			    <input type="text" class="form-control" id="userName" style="width: 200px;">
			</div>
			<div class="form-group">
			    <label for="exampleInputEmail2">文件名称：</label>
			    <input type="text" class="form-control" id="fileName" style="width: 200px;">
			</div>
			<select  data-toggle="modal" class="btn btn-primary" id="stateVal"  >
        		<option value="" >全部状态 </option>
        		<option value="1">已处理</option>
        		<option value="2">未处理 </option>
        	</select>
            <button type="button" class="btn btn-default" id="serc">查询</button>
            <button type="button" class="btn btn-default" id="btn_audit">资源处理</button>
        	
        	<!-- <select  data-toggle="modal" class="btn btn-primary" id="fileVal" >
        	</select> -->
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

<div id="userTree" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
<ul id="treeDemo" class="ztree" style="height: 300px;"></ul>
</div>
	</div>
</div>
<!-- 审查文件-->
	<form  id="subForm" action="<%=basePath%>/report/auditReportFile" method="post" >
	     <div id="modifyform" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true" >
	         <div class="modal-dialog">
	             <div class="modal-content">
	                 <div class="modal-header">
	                     <h2 class="modal-title" id="myModalLabel">审查文件</h2>
	                 </div>
	                 <div class="modal-body">
	                 			<div class="form-group">
	                     			 <input type="hidden" name="userId" id="reportFileUserId" >
	                     	 		<input type="hidden" name="reportId" id="reportId" >
	                     	 		<input type="hidden" name="fileId" id="fileId" >
	                   			  </div>
	                 	<table width="80%">
	                 		<tr>
	                 			<td >
				                     <div class="form-group">
				                         <label class="col-sm-5 control-label">用户名</label>
				                         <label class="col-sm-5 control-label" id="reportFileUserName"></label>
				                     </div>
	                 			</td>
	                 		</tr>
	                 		<tr>
	                 			<td >
			                     <div class="form-group" >
			                         <label class="col-sm-5 control-label">用户状态</label>
			                        	<select  name="userState"  id="reportFileUserState">
			                        		<option  value="1">正常</option>
			                        		<option  value="2">冻结</option>
			                        		<option  value="3">封号</option>
			                        	</select>
			                     </div>
	                 			
	                 			</td>
	                 		</tr>
	                 		<tr>
	                 			<td >
			                     <div class="form-group">
			                         <label class="col-sm-5 control-label">文件名称</label>
			                         <label class="col-sm-5 control-label" id="reportFileOldName"></label>
			                     </div>
	                 			</td>
	                 		</tr>
	                 		<tr>
	                 			<td >
				                     <div class="form-group">
				                         <label class="col-sm-5 control-label">储存名称</label>
				                         <label class="col-sm-5 control-label" id="reportFileUploadName"></label>
				                     </div>
	                 			
	                 			</td>
	                 		</tr>
	                 		<tr>
	                 			<td >
	                 			
			                     <div class="form-group">
			                         <label class="col-sm-5 control-label" >文件状态</label>
			                         <select name="fileState"  id="reportFileFileState" >
			                        		<option  value="1">正常</option>
			                        		<option  value="2">删除</option>
			                         </select>
			                     </div>
	                 			</td>
	                 		</tr>
	                 		<tr>
	                 			<td >
			                     <div class="form-group">
			                         <label class="col-sm-5 control-label">处理状态</label>
			                         <select  name="reportState" id="reportState" >
			                        		<option  value="1">已处理</option>
			                        		<option  value="2">未处理</option>
			                         </select>
			                     </div>
	                 			</td>
	                 		</tr>
	                 		
	                 		
	                 	</table>
	                     <div class="form-group">
	                    	<a href="#" data-toggle="modal" class="btn btn-primary" id="reportFileFilePath" >文件下载</a>
	                     </div>
	                 	
	                    
	                 </div>
	                 <div class="modal-footer">
	                     <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	                     <button type="button" class="btn btn-primary" id="add" onclick="toAudit()">修改</button>
	                 </div>
	             </div>
	         </div>
	     </div>
	</form>
</body>
</html>