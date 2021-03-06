<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Client Side Pagination in DataGrid - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>

    <script type="text/javascript">
        $(function(){
            $('#dg').datagrid().datagrid('getPager');
            getPagination("dg");
        });
    </script>
</head>
<body>
<table id="dg" style="width:700px;height:250px" title="您所在的位置：系统管理－>菜单列表"
       data-options="
                    fit:true,
                    toolbar:'#tb',
                    fitColumns:true,
                    singleSelect:true,
                    pagination:true,
                    url:'${ctx}/manage/menu/list/parent',
                    pageSize:'20',
                    onRowContextMenu: onRowContextMenu,
                    method:'post'"
       >
    <thead>
    <tr>
        <th data-options="field:'id',width:80">id</th>
        <th data-options="field:'name',width:100">菜单名称</th>
        <th data-options="field:'order',width:100">排序</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="${ctx}/manage/menu/save" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加菜单</a>
    </div>
</div>

<div id="mm" class="easyui-menu" style="width:120px;">
    <div onClick="edit('${ctx}/manage/menu/list/child/','dg')" data-options="iconCls:'icon-edit'">查看子菜单</div>
    <div onClick="edit('${ctx}/manage/menu/save/','dg')" data-options="iconCls:'icon-edit'">添加子菜单</div>
    <div onClick="edit('${ctx}/manage/menu/update/','dg')" data-options="iconCls:'icon-edit'">编辑</div>
    <div onClick="del()" data-options="iconCls:'icon-remove'">删除</div>
</div>
</body>

</html>