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
    <title>文件类型列表</title>
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
     

        $(document).ready(function(){
            $.jgrid.defaults.styleUI="Bootstrap";
            $("#list4").jqGrid({
                url:'<%=basePath %>/fileType/selectAllList',
                datatype:"json", //数据来源，本地数据
                //postData:mydata,
                mtype:"GET",//提交方式
//                height:420,//高度，表格高度。可为数值、百分比或'auto'
                //width:1000,//这个宽度不能为百分比
                height:$(window).height()-239,
                autowidth:true,//自动宽
                shrinkToFit:true,
                colModel:[
                    {label:"类型Id",name:'fileTypeId',index:'fileTypeId', width:'15%',align:'center',hidden: true},
                    {label:"名称",name:'name',index:'name', width:'15%',align:'center'},
                    {label:"父级名称",name:'parentName',index:'parentName', width:'35%',align:'center'},
                    {label:"父级Id",name:'parentId',index:'parentId', width:'35%',align:'center',hidden: true},
                    {label:"图标",name:'icon',index:'icon', width:'35%',align:'center',
						formatter:function(cellvalue, options, rowObject){
							if(cellvalue!=null && cellvalue!="" && cellvalue!="undefined"){
								return "<img src='"+cellvalue+"' width='100px' height='100px' alt='图片损坏'/>";
							}else{
								return "";
							}
						}
                    },
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
                    {label:"排序",name:'sort',index:'sort', width:'35%',align:'center'},
                    {label:"状态Id",name:'state',index:'state', width:'35%',align:'center',hidden: true},
                    {label:"状态",name:'state',index:'state', width:'35%',align:'center',
						formatter:function(cellvalue, options, rowObject){
							if(cellvalue==1){
								return "显示";
							}else if(cellvalue==2){
								return "隐藏";
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
                sortname : 'sort',
                //loadonce:true, //设置成true，表示一次性导入数据；默认为false
                //rownumbers: true, //设置成false，就不显示行号；否则反之
                //grouping: true, //默认false 不分组，反之亦然
                rowList:[30,50,80],//用于改变显示行数的下拉列表框的元素数组。
                jsonReader:{
                    id: "fileTypeId",//设置返回参数中，表格ID的名字为blackId
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
            

            //添加文件类型窗口
            $("#btn_add").bind("click",function(){
            	
            	$("#addProductStyle").modal('show');
               
            	 //查询产品类型
                 $.ajax({
                     url:"<%=basePath%>/fileType/getTypeRoot",
                     type:"post",
                     async : true, //同步异步请求
                     cache:false, //是否使用缓存
                     datatype:'json',
                     success:function(r){
                    	 $("#typeId").empty(); 
                         var dataObj=eval("("+r+")");//转换为json对象
                         $("#typeId").append("<option value=0>根目录</option>");
                         for(var i=0;i<dataObj.length;i++){
                             $("#typeId").append("<option value="+dataObj[i].fileTypeId+">"+dataObj[i].name+"</option>");
                         }
                         $("#addProductStyle").modal('show');
                     },
                     error: function(XMLHttpRequest, textStatus, errorThrown) {
                         alert(textStatus);
                         alert(XMLHttpRequest.responseText);
                     }
                 }); 
            });
            
           //修改文件类型窗口
            $("#btn_edit").bind("click",function(){
            	
            	var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            	var model = jQuery("#list4").jqGrid('getRowData', selectedId);
            	
            	if(selectedId<0){
            		alert("请选择要编辑的行数");
            		return;
            	}
            	
            	//查询产品类型
                $.ajax({
                    url:"<%=basePath%>/fileType/getTypeRoot",
                    type:"post",
                    async : true, //同步异步请求
                    cache:false, //是否使用缓存
                    datatype:'json',
                    success:function(r){
                   	   $("#up_typeId").empty(); 
                        var dataObj=eval("("+r+")");//转换为json对象
                        for(var i=0;i<dataObj.length;i++){
                            $("#up_typeId").append("<option value="+dataObj[i].fileTypeId+">"+dataObj[i].name+"</option>");
                        }
                        
                        $("#modifyform").modal('show');
                    	
                         $("#up_name").val(model.name);
                         $("#up_sort").val(model.sort);
                         $("#fileTypeId").val(model.fileTypeId);
                         
                         $("#up_typeId option[value='"+model.parentId+"'").attr("selected", true);
                         if(model.state=="显示"){
                        	 $("input[type=radio][id=up_state][value=1]").attr("checked",'checked')
                         }else if(model.state=="隐藏"){
                        	 $("input[type=radio][id=up_state][value=2]").attr("checked",'checked')
                         }
						 
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(textStatus);
                        alert(XMLHttpRequest.responseText);
                    }
                }); 
                
            });
           
          //删除
            $("#btn_delete").bind("click",function(){
            	
            	var selectedId = $("#list4").jqGrid("getGridParam", "selrow");
            	var model = jQuery("#list4").jqGrid('getRowData', selectedId);
            	
            	if(selectedId<=0){
            		alert("请选择要删除的行数");
            		return;
            	}
            	
            	$("#del").dialog({
                    height:300,
                    width:400,
                    resizable:false,
                    modal:true,  //这里就是控制弹出为模态
                    buttons:{
                        "取消":function(){$(this).dialog("close");},
                        "确定":function(){
                            $.ajax({
                                url:'<%=basePath %>/fileType/deleteType?fileTypeId='+model.fileTypeId,
                                contentType : 'application/json',
                                dataType:'json',
                                success:function(result){
                                	$("#list4").trigger("reloadGrid");  
                                }
                            });
                            $(this).dialog("close");
                        }

                    }
                });
                
            });
            $.ajax({
                url:'<%=basePath %>/fileType/getTypeRoot',
                type:"post",
                async : true, //同步异步请求
                cache:false, //是否使用缓存
                datatype:'json',
                success:function(r){
                	 $("#fileTypeIdVal").empty(); 
                     var dataObj=eval("("+r+")");//转换为json对象
                     $("#fileTypeIdVal").append("<option value=''>所有类型</option>");
                     for(var i=0;i<dataObj.length;i++){
                         $("#fileTypeIdVal").append("<option value="+dataObj[i].fileTypeId+">"+dataObj[i].name+"</option>");
                     }
                     
                }
            }); 
            
            
          //删除
            $("#fileTypeIdVal").change(function(){
            	var parentId = $('#fileTypeIdVal').val();
            	$("#list4").jqGrid('setGridParam',{  
            	    datatype:'json',
            	    url:'<%=basePath %>/fileType/selectAllList?parentId='+parentId,
            	    page:1
            	}).trigger("reloadGrid"); //重新载入 
            });
        });
        
       
        function toVaild(){
        	var name = $('#name').val();
        	if(name.length==0){
        		alert("名称不能为空");
        		return false;
        	}
        	
        	var typeId = $('#typeId').val();
        	if(typeId.length==0){
        		alert("父级不能为空");
        		return false;
        	}
        	
        	var file = $('#file').val();
        	if(file.length==0){
        		alert("图标不能为空");
        		return false;
        	}
        	
        	var sort = $('#sort').val();
        	if(sort.length!=0){
        		if(isNaN(sort)){
            		alert("排序只能输入数字");
            		return false;
            	}
        	}
        	if(sort.length>5){
        		alert("排序限制五位数");
        		return false;
        	}
        	
        	var state = $('#state').val();
        	if(state.length==0){
        		alert("请选择状态");
        		return false;
        	}
        	$("#subForm").submit();
        	return true;
        }
        
        function up_toVaild(){
        	var name = $('#up_name').val();
        	if(name.length==0){
        		alert("名称不能为空");
        		return false;
        	}
        	
        	var typeId = $('#up_typeId').val();
        	if(typeId.length==0){
        		alert("父级不能为空");
        		return false;
        	}
        	
        	var sort = $('#up_sort').val();
        	if(sort.length!=0){
        		if(isNaN(sort)){
            		alert("排序只能输入数字");
            		return false;
            	}
        	}
        	
        	if(sort.length>5){
        		alert("排序限制五位数");
        		return false;
        	}
        	
        	var state = $('#up_state').val();
        	if(state.length==0){
        		alert("请选择状态");
        		return false;
        	}
        	$("#up_subForm").submit();
        	return true;
        }
        
    </script>

        <div class="ibox-title" id="user_button">
        	<select  data-toggle="modal" class="btn btn-primary" id="fileTypeIdVal" >
        	</select>
            <a data-toggle="modal" class="btn btn-primary" id="btn_add">添加类型</a>
            <a data-toggle="modal" class="btn btn-primary" id="btn_edit">编辑类型</a>
            <a data-toggle="modal" id="btn_delete" class="btn btn-primary" >删除类型</a>
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


				<!-- 添加类型  -->
				<form  id="subForm" action="<%=basePath%>/fileType/addType" method="post" enctype="multipart/form-data">
                <div id="addProductStyle" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="myModalLabel">添加文件类型</h2>
                            </div>
                            <div class="modal-body">
                                

                                <div class="form-group">
                                    <label class="col-sm-5 control-label">名称</label>
                                    <input type="text" id="name" name="name">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">父级名称</label>
                                    <select id="typeId" name="parentId">
                                    </select>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">图标</label>
                                    <input type="file" id="file" name="file">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">排序</label>
                                    <input type="text" id="sort" name="sort">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">状态</label>
                                    <input type="radio" id="state" name="state" value="1" checked="checked">显示
                                    <input type="radio" id="state" name="state" value="2">隐藏
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="add" onclick="toVaild()">添加</button>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
                <!-- 添加类型结束 -->

        <div id="del" title="删除" style="display:none;" >  <!-- 该Div的作用就是当点击jqGrid表格中的修改链接时弹出的dialog, 注意是在上面的Modify(id)函数中给下面的input赋值 -->
            <p style="content: '';"> 确定要删除该类型？</p>
        </div>

        
        
        
        <!-- 修改类型  -->
				<form  id="up_subForm" action="<%=basePath%>/fileType/updateType" method="post" enctype="multipart/form-data">
                <div id="modifyform" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true" style="display:none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="myModalLabel">修改文件类型</h2>
                            </div>
                            <div class="modal-body">
                            	<input type="hidden" id="fileTypeId" name="fileTypeId" >
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">名称</label>
                                    <input type="text" id="up_name" name="name">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">父级名称</label>
                                    <select id="up_typeId" name="parentId">
                                    </select>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">图标</label>
                                    <input type="file" id="up_file" name="file">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">排序</label>
                                    <input type="text" id="up_sort" name="sort">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">状态</label>
                                    <input type="radio" id="up_state" name="state" value="1" >显示
                                    <input type="radio" id="up_state" name="state" value="2">隐藏
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="add" onclick="up_toVaild()">修改</button>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
                <!-- 添加类型结束 -->
</body>
</html>