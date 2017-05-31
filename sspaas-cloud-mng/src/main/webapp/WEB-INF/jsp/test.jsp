<%--
  Created by IntelliJ IDEA.
  User: 10735
  Date: 2016/11/21
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getContextPath();
%>
<html>
<head>
    <title>测试</title>
    <link rel="stylesheet" href="<%=basePath %>/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="<%=basePath %>/js/zTree_v3-master/css/demo.css" type="text/css">
    <script src="<%=basePath %>/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript" src="<%=basePath %>/js/zTree_v3-master/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/jquery.ztree.exedit-3.5.js"></script>
    <script src="<%=basePath %>/hplus/js/bootstrap.min.js?v=3.3.6"></script>
</head>
<body>
<div><ul>~~~~~~~~~~~~~~~~~~~~~~~~~</ul></div>
<div><ul><li>a</li><li>b</li><li>c</li><li>d</li></ul></div>

<script>
    $(function () {
        $("#seeMenu").modal('show');
        var setting = {
            showLine: true,
            checkable: true,

            async: {
                enable: true,
                url: "http://host/getNode.php",
//                autoParam: ["id", "name"]
            },

            check: {
                enable: true,
                nocheckInherit: true,
                chkboxType:{ "Y" : "s", "N" : "" },
            },

            data: {
                key: {
                    name:"menuName",
                    title:"menuIcon"
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "fatherId",
                    rootPId:null
                }
            },

//            check: {
//                enable: true
//            },
//            data: {
//                simpleData: {
//                    enable: true
//                }
//            },

            callback:{
                onCheck:onCheck
            }
        };

        var zNodes = '[{"fatherId":"bbb","id":"ccc","menuName":"ccc","menuIcon":"ddd"},{"fatherId":"ccc","id":"ddd","menuName":"ccc","menuIcon":"ddd"}]';
        alert(zNodes+":"+zNodes[0].fatherId+"11111111111111");
        var zNode = JSON.parse(zNodes);
        alert(zNode.length+"length"+zNode+":"+zNode[0].fatherId+"222222222222222");
//        var obj = eval(zNode);
//        alert(obj+":"+obj[0].fatherId+"33333333333333");
        $.fn.zTree.init($("#treeDemo"), setting, zNode);



        function onCheck() {
            alert("--------------------");
            $("#empower").bind("click",function(){
                alert("！！！！！！！！！！！！！！！！");
            });
        };
    });

</script>
<div id="seeMenu" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body"><ul id="treeDemo" class="ztree"></ul></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="empower">授权</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
