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
    <script type="text/javascript" src="${ctx}/js/common.js?version=new Date()"></script>

    <script type="text/javascript">
        $(function(){
            $('#dg').datagrid()//.datagrid('getPager');
            //getPagination("dg");
        });

        function getFormData() {
            var serialize = $("#search_form").serializeJson();
            return  serialize;
        }

        function loadData(url) {
            var queryParams = getFormData();
            $('#dg').datagrid('options').queryParams=queryParams;
            $('#dg').datagrid('options').url=url;
            $("#dg").datagrid('reload');

        }


        function rowformater(value,row,index) {
            var opt_str = "";
            <hr:permission url="/manage/provider/type/update/(\d)+" method="GET">
            opt_str +="<a href='${ctx}/manage/provider/type/update/" + row.id+ "'>修改</a> | ";
            </hr:permission>

            <hr:permission url="/manage/provider/type/list/child/(\d)+" method="GET">
            opt_str += "<a href='${ctx}/manage/provider/type/list/child/" + row.id+ "'>查看子机构类型</a> | ";
            </hr:permission>

            <hr:permission url="/manage/provider/type/save(/\\d+)*" method="GET">
            opt_str +="<a href='${ctx}/manage/provider/type/save/" + row.id+ "'>添加子机构类型</a>";
            </hr:permission>
            return opt_str;
        }
    </script>
</head>
<body>
<table id="dg" style="width:700px;height:250px" title="您所在的位置：网站配置－>父机构类型列表"
       data-options="
                    fit:true,
                    toolbar:'#tb',
                    fitColumns:true,
                    singleSelect:true,
                    url:'${ctx}/manage/provider/type/list/parent',
                    <%--pagination:true,--%>
                    pageSize:'50',
                    onRowContextMenu: onRowContextMenu,
                    queryParams:getFormData(),
                    method:'post'"
       >
    <thead>
    <tr>
        <th data-options="field:'id',width:80">id</th>
        <th data-options="field:'name',width:100">名称</th>
        <th data-options="field:'sort',width:100">排序</th>
        <th data-options="field:'status',width:100">状态</th>
        <th data-options="field:'opt',width:100,formatter:rowformater">操作</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:0px;">
    <hr:permission url="/manage/provider/type/save(/\\d+)*" method="GET">
        <div style="padding:3px">
            <a href="${ctx}/manage/provider/type/save" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加机构类型</a>
        </div>
    </hr:permission>
    <div style="padding:3px 0;">
        <form id="search_form" action="${ctx}/manage/provider/type/list/parent" method="post">
        省名称: <input class="easyui-textbox" name="EQ_name" >
            <a href="#" class="easyui-linkbutton" onclick="loadData('${ctx}/manage/provider/type/list/parent')" iconCls="icon-search">查找</a>
        </form>

    </div>
</div>
</body>

</html>