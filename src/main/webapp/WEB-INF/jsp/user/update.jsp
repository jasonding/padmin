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
        var _switch_ = 0;
        $(function(){
           $("#change_password_button_id").click(function(){
               if(_switch_ == 0) {
                   $(this).attr("value","取消修改");
                   $("#user_password_id").show()
                   $("#confirm_password_tr_id").show()
                   _switch_ = 1;
               }else if(_switch_ == 1){
                   $(this).attr("value","修改密码");
                   $("#user_password_id").hide()
                   $("#confirm_password_tr_id").hide()
                   _switch_ = 0;
               }
           });
        });
    </script>
</head>
<body>
<div class="easyui-panel" title="您所在的位置：系统管理－>用户修改页面" style="width:80%" data-options="fit:true">
        <form id="ff"  action="${ctx}/manage/user/update"  method="post">
            <input type="hidden" name="id" value="${user.id}">
            <table cellpadding="5" align="center">
                <tr>
                    <td width="30px;">用户名:</td>
                    <td>
                        <input type="text" name="username" value="${user.username}" class="easyui-textbox" data-options="required:true" />
                    </td>
                </tr>
                <tr>
                    <td width="30px;">密码:</td>
                    <td>
                        <span id="user_password_id" style="display: none" class="textbox" style="width: 123px; height: 20px;">
                            <input class="textbox-text easyui-validatebox" type="password" name="password" />
                        </span>
                        <input type="button" id="change_password_button_id" value="修改密码" />
                    </td>
                </tr>
                <tr id="confirm_password_tr_id" style="display: none">
                    <td width="30px;">确认密码:</td>
                    <td>
                        <span id="confirm_password_id" class="textbox" style="width: 123px; height: 20px;">
                            <input class="textbox-text easyui-validatebox"  validType="equalTo['#password']"
                                   invalidMessage="两次输入密码不匹配" type="password" name="confrimPassword" />
                        </span>
                    </td>
                </tr>
                <tr>
                    <td width="30px;">是否有效:</td>
                    <td>
                        <input type="checkbox" name="enabled" <c:if test="${user.enabled}">checked="checked"</c:if> class="easyui-checkbox">有效</input>
                    </td>
                </tr>
                <tr>
                    <td>资源列表:</td>
                    <td>

                        <table>
                            <tr>
                                <c:forEach var="role" items="${roleList}" varStatus="st">
                                <td>
                                    <c:choose>
                                        <c:when test="${user.roleIdSet.contains(role.id)}">
                                            <input type="checkbox" name="roleIdSet" value="${role.id}" checked="checked" />${role.name}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="roleIdSet" value="${role.id}" />${role.name}
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