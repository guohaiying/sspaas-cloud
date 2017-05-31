<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String basePath = request.getContextPath();
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>复选框</title>

<link rel="stylesheet" href="css/demo.css" type="text/css">
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
function findAllRight(){
	$.ajax({
		url:"right/allright",
		type:"post",
		async : true, //同步异步请求
		cache:false, //是否使用缓存
		dataType : "json",
		error: function () {//请求失败处理函数  
            alert('请求失败');  
        }, 
		success:function(result){
			//var d = $.parseJSON(result);
			//alert(result);
			//alert(d);
			$.fn.zTree.init($("#treeDemo"), setting, result);
			//alert(result);
			}
		
	});
	
}

var setting = {
		showLine: true,
	    checkable: true,
		check: {
			enable: true,
			nocheckInherit: true
		},
		
		data: {
			key: {
				name:"rightName",
				title:"description"
			},
			simpleData: {
				enable: true,
				idKey: "rightId",
				pIdKey: "parentRightId",
				rootPId:null
			}
		}
	};

	function updateRoleRight() {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes();

		if (nodes.length == 0) {
			alert("请先选择一个节点");
		} else {
			var selectedValue = " ";
			for (var i = 0; i < nodes.length; i++) {
				selectedValue += nodes[i].rightId + ", ";
			}
			roleId = ${param.roleId};
			$.ajax({
				data : {
					"va" : selectedValue
				},
				url : "role/roleGrant?roleId=" + roleId,
				type : "post",
				async : false,
				success : function(result) {
					alert(result);
					$("#msg").get(result.MSG);
				}

			});

		}

	}
</script>
</head>
<body>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
<form action=""  name="form1" >
  <input type="button" onclick="findAllRight()"  value="查询所有的权限"/>
 <input type="button" onclick="updateRoleRight()" value="修改角色权限"/>
 <div id=msg></div>
</form>


</body>
</html>