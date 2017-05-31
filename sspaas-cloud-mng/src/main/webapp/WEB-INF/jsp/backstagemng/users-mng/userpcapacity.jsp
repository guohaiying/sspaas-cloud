<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String basePath = request.getContextPath();
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户订单列表</title>
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
	                    <!-- <a data-toggle="modal" class="btn btn-primary" id="but_edit">编辑</a>
	                    <a data-toggle="modal" class="btn btn-primary" id="but_add">添加</a>
	                    <a data-toggle="modal" class="btn btn-primary" id="but_delete">删除</a> -->
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
            url:'<%=basePath %>/userpurchasecapacity/getUserOrder',
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
                    label:'用户名称',
                    name:'size',
                    index:'size',
                    align:'center',
                },
                {
                    label:'用户电话',
                    name:'price',
                    index:'price',
                    align:'center',
                },
                {
                    label:'容量大小',
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

  	
</script>

</body>
</html>