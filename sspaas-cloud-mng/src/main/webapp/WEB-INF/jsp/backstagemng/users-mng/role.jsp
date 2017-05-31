<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String basePath = request.getContextPath();
%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>角色管理</title>
    <link rel="shortcut icon" href="<%=basePath %>/favicon.ico">
    <link href="<%=basePath %>/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath %>/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <!-- jqgrid-->
    <link href="<%=basePath %>/hplus/css/plugins/jqgrid/ui.jqgridffe4.css?0820" rel="stylesheet">

    <!-- treeview -->
    <link href="<%=basePath %>/hplus/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">

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

    <script type="text/javascript">


        $(function(){
            $.jgrid.defaults.styleUI="Bootstrap";
            //分页查询数据(自定义结果)
            $("#table_role").jqGrid({
                url:'<%=basePath %>/role/roleList',
                datatype:"json",
                mtype:"GET",
                height:$(window).height()-239,
                autowidth:true,
                shrinkToFit:true,
                rowNum:8,
                rowList:[8,20,30,50],
                colNames:[],
                colModel:[
                    {
                        label:'roleId',
                        name:'roleId',
                        index:'roleId',
                        sortable:false,
                        hidden:true,
                        width:'10%',
                        align:'center',
                    },
                    {
                        label:'角色昵称',
                        name:'roleName',
                        index:'roleName',
                        align:'center',
                    },
                    {
                        label:'角色描述',
                        name:'description',
                        index:'description',
                        align:'center',
                    },
                    {
                        label:'角色添加时间',
                        name:'time',
                        index:'time',
                        align:'center',
                        formatter:"date",
                        formatter:function(cellvalue, options, rowObject){
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
                sortname: "time",
                sortorder: "desc",
                emptyrecords : "没有符合条件的数据",
                jsonReader:{
                    id:"roleId",
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

            var buttonList = "";
            $.each(eval(${requestScope.mid}), function (idx, obj) {
                buttonList+="<a data-toggle='modal' class='btn btn-primary' id=" + obj.buttonId + ">" + obj.menuName + "</a>\n";
            });
            $("#user_button").append(buttonList);

            //添加角色窗口
            $("#btn_add").bind("click",function(){
                //清空文本框中的数据
                $("roleName").attr("value","");
                $("description").attr("value","");

                $("#addRole").modal('show');
            });

            //添加角色
            $("#add").bind("click",function(){

                var roleName = $("#roleName").val();
                var description = $("#description").val();

                if(!roleName && typeof(roleName)!="undefined" && roleName!=0){
                    swal("请输入角色");
                    return;
                }
                $.ajax({
                    url:"<%=basePath%>/role/addRole",
                    async:false,
                    type:"post",
                    data:{
                        roleName:roleName,
                        description:description,
                    },
                    success:function(r){
                        $("#addRole").modal('hide');
                        $("#table_role").trigger("reloadGrid");
                    }
                });
            });

            //修改角色信息
            $("#btn_edit").bind("click",function(){
                //事件初始化
                $("#edit").unbind("click");

                var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
                var role= jQuery("#table_role").jqGrid('getRowData', selectedId);
                if (selectedId != null && selectedId.length>0){
                    //显示修改框
                    $("#editRole").modal('show');
                    $("#up_roleId").val(role.roleId);
                    $("#up_roleName").val(role.roleName);
                    $("#up_description").val(role.description);
                    //校验数据
                    $("#edit").bind("click",function(){
                        $.ajax({
                            url:"<%=basePath%>/role/updateRole",
                            async : false,
                            datatype:'json',
                            type:'get',
                            data:{
                                roleName:$("#up_roleName").val(),
                                description:$("#up_description").val(),
                                roleId:$("#up_roleId").val(),
                            },
                            success:function(d){
                                if(eval(d)=='success'){
                                    $("#editRole").modal('hide');
                                    swal("修改","修改成功！");
                                    $("#table_role").trigger("reloadGrid");
                                }else{
                                    swal("修改失败","系统繁忙！");
                                }
                            },error:function(){
                                swal("修改失败","网络异常！");
                            }
                        });
                    });
                }else{
                    swal("修改角色信息","请选择一个要修改的角色！","info");
                }
            });

            //查看角色信息
            $("#btn_see").bind("click",function(){
                var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
                var role= jQuery("#table_role").jqGrid('getRowData', selectedId);
                if (selectedId != null && selectedId.length>0){
                    //显示修改框
                    $("#seeRole").modal('show');
                    $("#see_roleId").val(role.roleId);
                    $("#see_roleName").val(role.roleName);
                    $("#see_description").val(role.description);
                }else{
                    swal("查看角色信息","请选择一个要查看的角色！","info");
                }

            });


            //删除角色信息
            $("#btn_delete").bind("click",function(){
                var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
                var role= jQuery("#table_role").jqGrid('getRowData', selectedId);
                if (selectedId != null && selectedId.length>0){
                    $.ajax({
                        url:"<%=basePath%>/role/deleteRole",
                        async : false,
                        datatype:'json',
                        type:'get',
                        data:{
                            roleId:role.roleId
                        },
                        success:function(d){
                            if(eval(d)=='success'){
                                $("#editRole").modal('hide');
                                swal("删除","删除成功！");
                                $("#table_role").trigger("reloadGrid");
                            }else{
                                swal("删除失败","系统繁忙！");
                            }
                        },error:function(){
                            swal("删除失败","网络异常！");
                        }
                    });
                }else{
                    swal("删除角色信息","请选择一个要删除的角色！","info");
                }

            });


            //授权
            $("#btn_authorize").bind("click",function(){
                var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
                var role= jQuery("#table_role").jqGrid('getRowData', selectedId);
                if (selectedId != null && selectedId.length>0){

                    $("#seeMenu").modal('show');

                    $.ajax({
                        url:"<%=basePath%>/user/getMenuRole?roleId="+role.roleId,
                        async : false,
                        datatype:'json',
                        type:'get',
                        success:function(e){
                            var setting = {
                                check: {
                                    enable: true,
                                    chkboxType:{ "Y" : "s", "N" : "" },
                                },
                                data: {
                                    simpleData: {
                                        enable: true
                                    }
                                },
                                callback:{
                                    onCheck:onCheck
                                }
                            };
                            var zNodes = eval("(" + e + ")");
                            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                        },
                        error:function(){
                            alert("服务器繁忙");
                        }
                    });

                    //$.fn.zTree.init($("#treeDemo"), setting, zNodes);

                }else{
                    swal("角色授权","请选择一个要授权的角色！","info");
                }

            });

            //获取选中节点值
            function onCheck(e,treeId,treeNode){
                var boolean=false;
                var selectedId = $("#table_role").jqGrid("getGridParam", "selrow");
                var role= jQuery("#table_role").jqGrid('getRowData', selectedId);
                var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
                nodes=treeObj.getCheckedNodes(true);
                //授权
                $("#empower").bind("click",function(){
                    $.ajax({
                        type : 'get',
                        url : '<%=basePath%>/role/deleteMenuRole',
                        data: {"roleId":role.roleId},
                        async : false,
                        success:function(e){
                            boolean=true;
                        }
                    });
                    for(var i=0;i<nodes.length;i++){
                        $.ajax({
                            type : 'get',
                            url : '<%=basePath%>/role/addMenuRole',
                            data: {"roleId":role.roleId,"menuId":nodes[i].id},
                            async : false,
                            success:function(e){
                                boolean=true;
                            }
                        });
                    }
                    if(boolean){
                        $("#seeMenu").modal('hide');
                        swal("角色授权!", "授权成功", "success");
                    }
                });
            }


        });

    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title" id="user_button">
                    <%--<a data-toggle="modal" class="btn btn-primary" id="btn_add">添加</a>
                    <a data-toggle="modal" class="btn btn-primary" id="btn_edit">修改</a>
                    <a data-toggle="modal" id="btn_see" class="btn btn-primary">查看</a>
                    <a data-toggle="modal" id="btn_delete" class="btn btn-primary">删除</a>
                    <a data-toggle="modal" id="btn_authorize" class="btn btn-primary">授权</a>--%>
                </div>
                <div class="ibox-content">
                    <div class="jqGrid_wrapper" id="divWidth">
                        <table id="table_role"></table>
                        <div id="pager_role"></div>
                    </div>

                <!-- 添加角色 -->
                <div id="addRole" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="myModalLabel">添加角色</h2>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">请输入角色名称</label>
                                    <input type="text" id="roleName" name="roleName">
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-5 control-label">请输入备注</label> <input
                                        type="text" id="description" name="description">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="add">添加</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 添加角色结束 -->
                <!-- 修改角色开始 -->
                <div id="editRole" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="modal-title" id="up_myModalLabel">修改角色</h2>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="col-sm-5 control-label">请输入角色名称</label>
                                    <input type="text" id="up_roleName" name="roleName">
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-5 control-label">请输入备注</label>
                                    <input type="text" id="up_description" name="description">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="edit">修改</button>
                            </div>
                            <input type="hidden" id="up_roleId" name="roleId" >
                        </div>
                    </div>
                </div>
                <!-- 修改角色结束 -->
                <!-- 查看角色开始 -->
                    <div id="seeRole" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h2 class="modal-title" id="see_myModalLabel">查看角色</h2>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="col-sm-5 control-label">角色名称</label>
                                        <input type="text" id="see_roleName" name="roleName" readonly>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-5 control-label">请输入备注</label>
                                        <input type="text" id="see_description" name="description" readonly>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 修改角色结束 -->
                    <!-- 权限列表开始 -->
                    <div id="seeMenu" class="modal fade" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h2 class="modal-title" id="menu_myModalLabel">查看权限</h2>
                                </div>
                                <div class="modal-body"><ul id="treeDemo" class="ztree"></ul></div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-primary" id="empower">授权</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 权限列表结束 -->
            </div>
        </div>
    </div>
</div>
</body>
</html>