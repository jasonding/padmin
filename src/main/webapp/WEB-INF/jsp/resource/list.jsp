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
            $('#dg').datagrid().datagrid('getPager');
            getPagination("dg");
        });

        function getFormData() {
            var serialize = $("#search_form").serializeJson();//{"resourceName":"资源列表"};//$("#search_form").serialize();
            return  serialize;
        }

        function loadData(url) {
            var queryParams = getFormData();
            $('#dg').datagrid('options').queryParams=queryParams;
            $('#dg').datagrid('options').url=url;
            $("#dg").datagrid('reload');

        }
    </script>
</head>
<body>
<table id="dg" style="width:700px;height:250px" title="您所在的位置：系统管理－>资源列表"
       data-options="
                    fit:true,
                    toolbar:'#tb',
                    fitColumns:true,
                    singleSelect:true,
                    pagination:true,
                    <%--url:'${ctx}/manage/resource/list',--%>
                    pageSize:'20',
                    onRowContextMenu: onRowContextMenu,
                    queryParams:getFormData(),
                    method:'post'"
       >
    <thead>
    <tr>
        <th data-options="field:'id',width:80">资源id</th>
        <th data-options="field:'resource',width:100">资源名称</th>
        <th data-options="field:'urlPartten',width:100">资源地址</th>
        <th data-options="field:'httpMethod',width:100">资源请求方法</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:0px;">
    <div style="padding:3px">
        <a href="${ctx}/manage/resource/save" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加资源</a>
    </div>
    <div style="padding:3px 0;">
        <form id="search_form" action="${ctx}/manage/resource/list" method="post">
        资源名称: <input class="easyui-textbox" name="resourceName" >
            <a href="#" class="easyui-linkbutton" onclick="loadData('${ctx}/manage/resource/list')" iconCls="icon-search">查找</a>
        <%--To: <input class="easyui-datebox" style="width:80px">--%>
        <%--Language:--%>
        <%--<select class="easyui-combobox" panelHeight="auto" style="width:100px">--%>
            <%--<option value="java">Java</option>--%>
            <%--<option value="c">C</option>--%>
            <%--<option value="basic">Basic</option>--%>
            <%--<option value="perl">Perl</option>--%>
            <%--<option value="python">Python</option>--%>
        <%--</select>--%>
        </form>

    </div>
</div>

<div id="mm" class="easyui-menu" style="width:120px;">
    <div onClick="edit('${ctx}/manage/resource/update/','dg')" data-options="iconCls:'icon-edit'">编辑</div>
    <div onClick="del()" data-options="iconCls:'icon-remove'">删除</div>
</div>

</body>

</html>