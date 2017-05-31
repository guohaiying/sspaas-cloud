<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String basePath = request.getContextPath();
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>容量类型列表</title>
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
                
                <!-- 编辑容量 开始 -->
                <form id="upCapacityForm">
                <div id="editCapacity" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="up_myModalLabel">修改容量类型</h2>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">容量大小(M)</label>
                                    <input type="text" id="up_size" name="size">
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">价格</label>
                                    <input type="text" id="up_price" name="price">
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">折扣(10-100)(100为不打折  10为1折)</label>
                                    <input type="text" id="up_discount" name="discount" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" 
                                    onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" value='100'>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">图标</label>
                                    <input type="file" id="up_file" name="file">
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label"></label>
                                    <div id="up_file_name"></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">时间（天）</label>
                                    <input type="text" id="up_expire" name="expire">
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">容量描述</label>
                                    <textarea rows="5" cols="22" id="up_descript" name="descript"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="edit">修改</button>
                            </div>
                            <input type="hidden" id="up_capacityId" name="capacityId" >
                        </div>
                    </div>
                </div>
                </form>
                <!-- 编辑容量 结束 -->
                
                <!-- 添加容量类型 开始 -->
                <form id="addCapacityForm">
                <div id="addCapacity" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="myModalLabel">添加容量类型</h2>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">请输入容量大小(M)</label>
                                    <input type="text" id="size" name="size">
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-5 control-label">请输入价格</label>
                                    <input type="text" id="price" name="price">
                                </div>

								<div class="form-group">
                                    <label class="col-sm-5 control-label">请输入时间（天）</label>
                                    <input type="text" id="expire" name="expire">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">折扣(10-100)(100为不打折  10为1折)</label>
                                    <input type="text" id="discount" name="discount" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" 
                                    onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" value='100'>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">图标</label>
                                    <input type="file" id="file" name="file">
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">容量描述</label>
                                    <textarea rows="5" cols="22" id="descript" name="descript"></textarea>
                                </div>
                                
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">请选择添加类型</label>
                                    <select id="type" name="type">
                                        <option value="1">赠送</option>
                                        <option value="2" selected="selected">购买</option>
                                    </select>
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
                <!-- 添加容量类型 结束 -->
                
                <!-- 删除容量类型 开始 -->
                <div id="del" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title">确定删除容量类型？</h2>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="delPro">确定</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 删除容量类型 结束 -->
                
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
            url:'<%=basePath %>/capacity/capacityTypeList',
            datatype:"json",
            mtype:"GET",
            height:$(window).height()-239,
            autowidth:true,
            shrinkToFit:true,
            rowNum:8,
            rowList:[8,20,30,50],
            colModel:[
                {
                    label:'capacityId',
                    name:'capacityId',
                    index:'capacityId',
                    sortable:false,
                    hidden:true,
                    align:'center',
                },
                {
                    label:'容量大小(M)',
                    name:'size',
                    index:'size',
                    align:'center',
                },
                {
                    label:'价格',
                    name:'price',
                    index:'price',
                    align:'center',
                },
                {
                    label:'折扣',
                    name:'discount',
                    index:'discount',
                    align:'center',
                },
                {
                    label:'折扣后价格',
                    align:'center',
                    formatter:function(cellvalue, options, rowObject){
                    	if(rowObject.discount==0){
                    		return rowObject.price;
                    	}else{
                    		return rowObject.price*(rowObject.discount/100);
                    	}
                    }
                },
                {
                    label:'时间（天）',
                    name:'expire',
                    index:'expire',
                    align:'center',
                },
                {
                    label:'容量描述',
                    name:'descript',
                    index:'descript',
                    align:'center',
                },
                {
                    label:'类型',
                    name:'type',
                    index:'type',
                    align:'center',
                    formatter:"date",
                    formatter:function(cellvalue, options, rowObject){
                        if (cellvalue == 1){
                            return "赠送";
                        } else if (cellvalue == 2){
                            return "购买";
                        }
                    }
                },
                {
                    label:'图标',
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
                    label:'添加时间',
                    name:'addTime',
                    index:'addTime',
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
            ],
            pager:'#pager_role',
            viewrecords: true,
            rownumbers:true,
            sortname: "add_time",
            sortorder: "desc",
            emptyrecords : "没有符合条件的数据",
            jsonReader:{
                id:"capacityId",
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

  	//编辑容量
    $("#but_edit").bind("click",function(){
        //事件初始化
        $("#edit").unbind("click");

        var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
        var product= jQuery("#table_role").jqGrid('getRowData', selectedId);
        if (selectedId != null && selectedId.length>0){
            //显示修改框
            $("#editCapacity").modal('show');
	        $("#up_size").val(product.size);
	        $("#up_price").val(product.price);
	        $("#up_expire").val(product.expire);
	        $("#up_descript").val(product.descript);
	        $("#up_capacityId").val(product.capacityId);
	        $("#up_discount").val(product.discount);
	        $("#up_file_name").html(product.icon);
	        
	        
            //校验数据
            $("#edit").bind("click",function(){
            	var size = $("#up_size").val();
    	        var price = $("#up_price").val();
    	        var expire = $("#up_expire").val();
    	        var descript = $("#up_descript").val();
    	        var capacityId = $("#up_capacityId").val();
    	        var discount = $("#up_discount").val();
    	        var file = $("#up_file").val();
            	
            	if(typeof(size)=="undefined" || size=="" || size==0){
                    swal("请输入容量大小");
                    return;
                }
            	
            	if(isNaN(size)){
                    swal("容量大小输入不规范");
                    return;
                }
            	if(typeof(price)=="undefined" || price==""){
                    swal("请输入价格");
                    return;
                }
            	
            	if(isNaN(price)){
                    swal("价格输入不规范");
                    return;
                }
            	
            	if(typeof(expire)=="undefined" || expire==""){
                    swal("请输入时间");
                    return;
                }
            	
            	if(isNaN(expire)){
                    swal("时间输入不规范");
                    return;
                }
            	
            	if(discount<10){
           		 	swal("折扣为10以上的正整数");
                    return;
           		}
            	
            	if(discount>100){
           		 	swal("折扣为100以下的正整数");
                    return;
           		}
            	
            	//开始ajax操作  
                $("#upCapacityForm").ajaxSubmit({  
                     type: "POST",//提交类型  
                     dataType: "json",//返回结果格式  
                     async : true,
                     url: "<%=basePath%>/capacity/editCapacity",//请求地址  
                     data:$("#upCapacityForm").serialize(),
                     /* data:{
                    	size:size,
             	        price:price,
             	        expire:expire,
             	        descript:descript,
             	        capacityId:capacityId,
             	        discount:discount,
             	        file:file
                     }, */
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
            swal("修改容量类型","请选择一个容量类型！","info");
        }
    });
  	
  	//添加容量类型窗口
    $("#but_add").bind("click",function(){
        $("#addCapacity").modal('show');
        $("#add").unbind("click");
        
        $("#add").bind("click",function(){
        	
        	var size = $("#size").val();
	        var price = $("#price").val();
	        var expire = $("#expire").val();
	        var descript = $("#descript").val();
	        var type = $("#type").val();
	        var discount = $("#discount").val();
	        var file = $("#file").val();
	        
	        if(typeof(size)=="undefined" || size=="" || size==0){
                swal("请输入容量大小");
                return;
            }
        	
        	if(isNaN(size)){
                swal("容量大小输入不规范");
                return;
            }
        	
        	if(typeof(price)=="undefined" || price==""){
                swal("请输入价格");
                return;
            }
        	
        	if(isNaN(price)){
                swal("价格输入不规范");
                return;
            }
        	
        	if(typeof(expire)=="undefined" || expire==""){
                swal("请输入时间");
                return;
            }
        	
        	if(isNaN(expire)){
                swal("时间输入不规范");
                return;
            }
        	
        	if(discount<10){
       		 	swal("折扣为10以上的正整数");
                return;
       		}
        	
        	if(discount>100){
       		 	swal("折扣为100以下的正整数");
                return;
       		}
        	
        	if(typeof(file)=="undefined" || file=="" || file==0){
       		 	swal("请选择图片");
                return;
       		}

        	 //开始ajax操作  
            $("#addCapacityForm").ajaxSubmit({  
            	
                    type: "POST",//提交类型  
                    dataType: "json",//返回结果格式  
                    url: "<%=basePath%>/capacity/addCapacity",//请求地址  
//                     data:{
//                     	size:size,
//             	        price:price,
//             	        expire:expire,
//             	        descript:descript,
//             	        type:type,
//             	        discount:discount,
//             	        file:file
//                     },
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
           <%--  $.ajax({
            url:"<%=basePath%>/capacity/addCapacity",
            datatype:'json',
            data:{
            	size:size,
    	        price:price,
    	        expire:expire,
    	        descript:descript,
    	        type:type,
    	        discount:discount
            },
            success:function(r){
                if(eval(r)==1){
                	$("#addCapacity").modal('hide');
                    swal("添加","添加成功！");
                    $("#table_role").trigger("reloadGrid");
                }else if(eval(r)==2){
                	$("#addCapacity").modal('hide');
                    swal("添加失败","初始化赠送类型已存在，请勿再次添加！","error");
                }else {
                    swal("添加失败","系统繁忙！","error");
                }
            },error:function(){
                swal("添加失败","网络异常！","error");
            }
            }); 
        });
    });--%>
  	
  //删除产品类型
    $("#but_delete").bind("click",function(){
        var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
        var product= jQuery("#table_role").jqGrid('getRowData', selectedId);
        if (selectedId != null && selectedId.length>0){
            $("#del").modal('show');
            $("#delPro").bind("click",function(){
                $.ajax({
                    url:"<%=basePath%>/capacity/deleteCapacity",
                    data:{
                    	capacityId:product.capacityId,
                    	type:product.type
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
            swal("删除容量类型信息","请选择一个要删除的容量类型！","info");
        }
    });
</script>

</body>
</html>