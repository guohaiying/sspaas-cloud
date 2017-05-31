<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String basePath = request.getContextPath();
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>版本管理</title>
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
    <script src="<%=basePath %>/js/jquery-form.js"></script>
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title" id="user_button">
	                    <a data-toggle="modal" class="btn btn-primary" id="but_edit">编辑</a>
	                    <a data-toggle="modal" class="btn btn-primary" id="but_add">添加</a>
	                    <a data-toggle="modal" class="btn btn-primary" id="but_delete">删除</a>
                </div>
                
                <!-- 编辑版本 开始 -->
                <form id="upCapacityForm">
                <div id="editCapacity" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="up_myModalLabel">修改版本信息</h2>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">版本号</label>
                                    <input type="text" id="up_version" name="version">
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-5 control-label">上传文件</label>
                                    <input type="file" id="file" name="file">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">状态</label>
                                    <input type="radio" id="up_state1" name="state" checked="checked" value="1">可用
                                    <input type="radio" id="up_state2" name="state" value="2">不可用
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="edit">修改</button>
                            </div>
                            <input type="hidden" id="up_id" name="id" >
                        </div>
                    </div>
                </div>
                </form>
                <!-- 编辑版本 结束 -->
                
                <!-- 添加版本 开始 -->
                <form id="addCapacityForm">
                <div id="addCapacity" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="myModalLabel">添加版本信息</h2>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">版本号</label>
                                    <input type="text" id="version" name="version">
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-5 control-label">上传文件</label>
                                    <input type="file" id="file" name="file">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">状态</label>
                                    <input type="radio" id="state" name="state" checked="checked" value="1">可用
                                    <input type="radio" id="state" name="state" value="2">不可用
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="add">添加</button>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
                <!-- 添加版本 结束 -->
                
                <!-- 删除版本 开始 -->
                <div id="del" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title">确定删除此版本信息？</h2>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="delPro">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 删除容版本结束 -->
                
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
            url:'<%=basePath %>/version/versionList',
            datatype:"json",
            mtype:"GET",
            height:$(window).height()-239,
            autowidth:true,
            shrinkToFit:true,
            rowNum:8,
            rowList:[8,20,30,50],
            colModel:[
                {
                    label:'id',
                    name:'id',
                    index:'id',
                    sortable:false,
                    hidden:true,
                    width:'10%',
                    align:'center',
                },
                {
                    label:'版本号',
                    name:'version',
                    index:'version',
                    align:'center',
                },
                {
                    label:'下载地址',
                    name:'url',
                    index:'url',
                    align:'center',
                },
                {
                    label:'状态',
                    name:'state',
                    index:'state',
                    align:'center',
                    width:'150%',
                    formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (cellvalue == 1){
                            return "可用";
                        } else if (cellvalue == 2){
                            return "不可用";
                        } else{
                        	return "";
                        }
                    }
                },
                {
                    label:'更新时间',
                    name:'updateTime',
                    index:'updateTime',
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
                }
            ],
            pager:'#pager_role',
            viewrecords: true,
            rownumbers:true,
            sortname: "version",
            sortorder: "asc",
            emptyrecords : "没有符合条件的数据",
            jsonReader:{
                id:"id",
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
        
      //添加版本信息窗口
        $("#but_add").bind("click",function(){
            $("#addCapacity").modal('show');
            $("#add").unbind("click");
            
            $("#add").bind("click",function(){
            	
            	 //开始ajax操作  
                $("#addCapacityForm").ajaxSubmit({  
                    type: "POST",//提交类型  
                    dataType: "json",//返回结果格式  
                    url: "<%=basePath%>/version/addVersion",//请求地址  
   					data:$("#addCapacityForm").serialize(),
                    success: function (data) {//请求成功后的函数  
                    	if(eval(data)==1){
                        	$("#addCapacity").modal('hide');
                            swal("添加","添加成功！");
                            $("#table_role").trigger("reloadGrid");
                        }else if(eval(r)==2){
                        	$("#addCapacity").modal('hide');
                            swal("添加失败","初始化赠送类型已存在，请勿再次添加！","error");
                        }else {
                            swal("添加失败","系统繁忙！","error");
                        }
                    },  
                    error: function (data) { swal("添加失败","网络异常！","error"); },//请求失败的函数  
                    async: true  
                });  
            });
        });
        
        
      //编辑版本信息
       $("#but_edit").bind("click",function(){
            //事件初始化
            $("#edit").unbind("click");

            var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
            var product= jQuery("#table_role").jqGrid('getRowData', selectedId);
            if (selectedId != null && selectedId.length>0){
                //显示修改框
                $("#editCapacity").modal('show');
    	        $("#up_version").val(product.version);
    	        $("#up_id").val(product.id);
    	        if(product.state=="可用"){
    	        	$("#up_state1").prop("checked",'checked'); 
    	        	$("#up_state2").removeAttr("checked"); 
    	        }else if(product.state=="不可用"){
    	        	$("#up_state1").removeAttr("checked"); 
    	        	$("#up_state2").prop("checked",'checked'); 
    	        }
    	        
                //校验数据
                $("#edit").bind("click",function(){
                	
                	//开始ajax操作  
                    $("#upCapacityForm").ajaxSubmit({  
                         type: "POST",//提交类型  
                         dataType: "json",//返回结果格式  
                         async : true,
                         url: "<%=basePath%>/version/editVersion",//请求地址  
                         data:$("#upCapacityForm").serialize(),
                         success: function (d) {//请求成功后的函数  
                         	if(d=='success'){
                                 $("#editCapacity").modal('hide');
                                 swal("修改","修改成功！","success");
                                 $("#table_role").trigger("reloadGrid");
                             }else{
                                 swal("修改失败","系统繁忙！","error");
                             }
               
                         },  
                         error: function (data) { swal("修改失败","网络异常！","error"); }
                    });  
                }); 
            }else{
                swal("修改版本信息","请选择一个版本信息！","info");
            }
        });
      	
      	 	
      	
        $("#but_delete").bind("click",function(){
            var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
            var product= jQuery("#table_role").jqGrid('getRowData', selectedId);
            if (selectedId != null && selectedId.length>0){
                $("#del").modal('show');
                $("#delPro").bind("click",function(){
                    $.ajax({
                        url:"<%=basePath%>/version/deleteVersion",
                        data:{
                        	id:product.id
                        },
                        success:function(d){
                            if(eval(d)==1){
                                $("#del").modal('hide');
                                /* swal("删除","删除成功！"); */
                                $("#table_role").trigger("reloadGrid");
                            }else if(eval(d)==2){
                            	$("#del").modal('hide');
                                swal("删除失败","该容量为初始赠送，不能删除！");
                            }else{
                                swal("删除失败","系统繁忙！");
                            }
                        },error:function(){
                            swal("删除失败","网络异常！");
                        }
                    });
                });
            }else{
                swal("删除版本信息","请选择一个要删除的版本信息！","info");
            }
        }); 
        
    });

  	
  	
  	
</script>

</body>
</html>