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
<div class="easyui-panel" title="您所在的位置：系统管理－>权限更新页面" style="width:80%" data-options="fit:true">
        <form id="ff"  action="${ctx}/manage/privilege/update"  method="post">
            <input type="hidden" name="id" value="${privilege.id}">
            <table cellpadding="5" align="center">
                <tr>
                    <td width="30px;">权限名称:</td>
                    <td>
                        <input type="text" name="name" value="${privilege.name}" class="easyui-textbox" data-options="required:true" />
                    </td>
                </tr>
                <tr>
                    <td>资源列表:</td>
                    <td>
                        <table>
                            <tr>
                            <c:forEach var="resource" items="${resourceList}" varStatus="st">
                                <td>
                                    <c:choose>
                                        <c:when test="${privilege.resourceIdSet.contains(resource.id)}">
                                            <input type="checkbox" name="resourceIdSet" value="${resource.id}" checked="checked" />${resource.displayName}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="resourceIdSet" value="${resource.id}" />${resource.displayName}
                                        </c:otherwise>
                                    </c:choose>
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