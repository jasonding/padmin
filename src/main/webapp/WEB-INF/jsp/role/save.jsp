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
<div class="easyui-panel" title="您所在的位置：系统管理－>角色添加页面" style="width:80%" data-options="fit:true">
        <form id="ff"  action="${ctx}/manage/role/save"  method="post">
            <table cellpadding="5" align="center">
                <tr>
                    <td width="30px;">角色名称:</td>
                    <td>
                        <input type="text" name="name" class="easyui-textbox" data-options="required:true" />
                    </td>
                </tr>
                <tr>
                    <td>权限列表:</td>
                    <td>
                        <table>
                            <tr>
                            <c:forEach var="privilege" items="${privilegeList}" varStatus="st">
                                <td>
                                    <input type="checkbox" name="privilegeIdSet" value="${privilege.id}" />${privilege.name}
                                </td>
                                <c:if test="${st.count % 5 eq 0}"></tr><tr></c:if>
                            </c:forEach>
                        </table>
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