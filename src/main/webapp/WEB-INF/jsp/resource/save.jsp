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
</head>
<body>
<div class="easyui-panel" title="您所在的位置：系统管理－>资源添加页面" style="width:80%" data-options="fit:true">
        <form id="ff" action="${ctx}/manage/resource/save" method="post">
            <table cellpadding="5" align="center">
                <tr>
                    <td>资源名称:</td>
                    <td><input class="easyui-textbox" type="text" name="resource" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>显示名称:</td>
                    <td><input class="easyui-textbox" type="text" name="displayName" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>路径pattern:</td>
                    <td><input class="easyui-textbox" type="text" size="50" name="urlPartten" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>请求方法:</td>
                    <td>
                        <select name="httpMethod">
                            <c:forEach items="${httpMethodList}" var="method">
                                <option value="${method}">${method}</option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('ff')">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="back(-1)">返回</a>
        </div>
</div>

</body>

</html>