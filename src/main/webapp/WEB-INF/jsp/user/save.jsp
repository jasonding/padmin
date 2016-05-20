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
<div class="easyui-panel" title="您所在的位置：系统管理－>用户添加页面" style="width:80%" data-options="fit:true">
        <form id="ff"  action="${ctx}/manage/user/save"  method="post">
            <table cellpadding="5" align="center">
                <tr>
                    <td width="30px;">用户名:</td>
                    <td>
                        <input type="text" name="username" class="easyui-textbox" data-options="required:true" />
                    </td>
                </tr>
                <tr>
                    <td width="30px;">密码:</td>
                    <td>
                        <input type="password" name="password" class="easyui-textbox" data-options="required:true" />
                    </td>
                </tr>
                <tr>
                    <td width="30px;">是否有效:</td>
                    <td>
                        <input type="checkbox" name="enabled" class="easyui-checkbox">有效</input>
                    </td>
                </tr>
                <tr>
                    <td>角色列表:</td>
                    <td>
                        <table>
                            <tr>
                                <c:forEach var="role" items="${roleList}" varStatus="st">
                                <td>
                                    <input type="checkbox" name="roleIdSet" value="${role.id}" />${role.name}
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