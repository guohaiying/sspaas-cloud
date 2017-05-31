<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%
	String basePath = request.getContextPath();
%>
<meta charset="UTF-8">
<title>用户邀请详情</title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/jquery-ui.min.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/ui.jqgrid.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>/css/jquery-ui-1.7.1.custom.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui-1.8.14.custom.min.js"></script>
<!-- <script type="text/javascript">

$(function(){
    $(window).resize(function(){   
 $("#list4").setGridWidth($(window).width());
});
}); 
</script> -->

<script type="text/javascript">
	$(document).ready(function table(){
		var userid = "${requestScope.userid}";
	    $("#list4").jqGrid({
	        url:"<%=basePath%>/userJoin/finddetails?userid="+userid,
	        datatype:"json", //数据来源，本地数据
	        //postData:mydata,
	        mtype:"GET",//提交方式
	        height:420,//高度，表格高度。可为数值、百分比或'auto'
	        //width:1000,//这个宽度不能为百分比
	        autowidth:true,//自动宽
	        colNames:['昵称', '手机号码','被邀请时间','最后登录'],
	        colModel:[
				{name:'name',index:'uf.nickname', width:'15%',align:'center'},
				{name:'phone',index:'u.phone', width:'20%',align:'center'},
				{name:'time',index:'inv.invitation_time', width:'15%',align:'center'},
				{name:'lastlogintime',index:'u.LastLoginTime', width:'15%',align:'center'}
				//{name:'time',index:'time', width:'15%',align:'center'}
	            //{name:'userid',index:'userid', width:'35%', align:"center",formatter: function (cellvalue) { return "<a href=\"\" style=\"color:#f60\" onclick=\"show('" + cellvalue + "')\">查看详情</a>" }}
	        ],
	        pager:'#gridPager',
	        //pager:$('#gridPager'),
	        //rownumbers:true,//添加左侧行号
	        //altRows:true,//设置为交替行表格,默认为false
	        //sortname:'createDate',
	        //sortorder:'asc',
	        viewrecords: true,//是否在浏览导航栏显示记录总数
	        rowNum:3, //每页显示记录数
	        //rowTotal:2, //表色一次性导入数据的最大行数
	        //viewrecords: true, //是否显示行数
	        rownumbers: true,
	        sortorder: "desc",
	        sortname : 'inv.invitation_time',
	        //loadonce:true, //设置成true，表示一次性导入数据；默认为false
	        //rownumbers: true, //设置成false，就不显示行号；否则反之
	        //grouping: true, //默认false 不分组，反之亦然
	        caption:"被邀请详情",
	        rowList:[2,3,4,5],//用于改变显示行数的下拉列表框的元素数组。
	        //pgbuttons: true,
	        /*loadComplete:function(data){
	            var ids = $('#list4').getDataIDs();//返回数据表的ID数组["66","39"..]
	            var len = ids.length; 
	            for(var i=0; i<len; i++){
	                var getRow = $('#list4').getRowData(ids[i]);//获取当前的数据行
	                var colVal = getRow.name;
	         		alert(colVal);
	           }
	        }, //获取行中某字段的值*/
	        jsonReader:{
	            //id: "blackId",//设置返回参数中，表格ID的名字为blackId
	            //id: "0",
	            root: "rows", // json中代表实际模型数据的入口
	            page: "page", // json中代表当前页码的数据
	            total: "total", // json中代表页码总数的数据
	            records: "records", // json中代表数据行总数的数据
	            repeatitems : false
	        }
	    });
	    //jQuery("#list4").jqGrid('navGrid','#gridPager',{edit:false,add:false,del:false});
	    //$("#list4").jqGrid.setGridParam({rowNum:10});
	});
	
</script>
</head>
<body>
	<table id="list4"></table>
	<div id="gridPager"></div>
</body>
</html>