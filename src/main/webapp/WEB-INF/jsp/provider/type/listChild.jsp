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
            $('#dg').datagrid();
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
                opt_str +="<a href='${ctx}/manage/provider/type/update/" + row.id+ "'>修改</a> ";
            </hr:permission>
            return opt_str;
        }
    </script>
</head>
<body>
<table id="dg" style="width:700px;height:250px" title="您所在的位置：网站配置－>${providerType.name}->子机构类型"
       data-options="
                    fit:true,
                    fitColumns:true,
                    singleSelect:true,
                    pageSize:'50',
                    url:'${ctx}/manage/provider/type/list/child/${pid}',
                    onRowContextMenu: onRowContextMenu,
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
</body>

</html>