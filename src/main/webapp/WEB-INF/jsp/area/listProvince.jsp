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
            <hr:permission url="/manage/area/update/(\d)+" method="GET">
                opt_str +="<a href='${ctx}/manage/area/update/" + row.id+ "'>修改</a> | ";
            </hr:permission>

            <hr:permission url="/manage/area/list/city/(\d)+" method="GET">
                opt_str += "<a href='${ctx}/manage/area/list/city/" + row.id+ "'>查看城市</a> | ";
            </hr:permission>

            <hr:permission url="/manage/area/save(/\d+)*" method="GET">
                opt_str +="<a href='${ctx}/manage/area/save/" + row.id+ "'>添加城市</a>";
            </hr:permission>
            return opt_str;
        }
    </script>
</head>
<body>
<table id="dg" style="width:700px;height:250px" title="您所在的位置：系统管理－>省列表"
       data-options="
                    fit:true,
                    toolbar:'#tb',
                    fitColumns:true,
                    singleSelect:true,
                    url:'${ctx}/manage/area/list/province',
                    <%--pagination:true,--%>
                    pageSize:'50',
                    queryParams:getFormData(),
                    method:'post'"
       >
    <thead>
    <tr>
        <th data-options="field:'id',width:20">id</th>
        <th data-options="field:'name',width:80">名称</th>
        <th data-options="field:'sort',width:80">排序</th>
        <th data-options="field:'status',width:80">状态</th>
        <th data-options="field:'opt',width:100,formatter:rowformater">操作</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:0px;">
    <div style="padding:3px">
        <hr:permission url="/manage/area/save(/\d+)*" method="GET">
            <a href="${ctx}/manage/area/save" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加省</a>
        </hr:permission>
    </div>
    <div style="padding:3px 0;">
        <form id="search_form" action="${ctx}/manage/area/list/province" method="post">
        省名称: <input class="easyui-textbox" name="EQ_name" >
            <a href="#" class="easyui-linkbutton" onclick="loadData('${ctx}/manage/area/list/province')" iconCls="icon-search">查找</a>
        </form>

    </div>
</div>

</body>

</html>