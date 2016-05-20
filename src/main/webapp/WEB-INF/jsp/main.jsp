<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>欢迎页面</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css"></link>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css" ></link>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function(){
         $.messager.show({
         title:'Title',
         msg:'欢迎进去Hrin后台管理系统',
         timeout:3000,
         showType:'slide'
         });
         });
    </script>
</head>
<body class="easyui-layout">
<div region="north" border="false" style="height:60px;background:#B3DFDA;"><h2>Hrin管理后台</h2></div>
<div data-options="region:'west',split:true" title="菜单列表" style="width:100px;">
    <div class="easyui-accordion" data-options="fit:true,border:false">
        <c:forEach items="${parentMenuList}" var="parentMenu">
            <div title="${parentMenu.name}" style="padding:10px;">
                <c:forEach items="${menuList[parentMenu]}" var="menu">
                    <a href="${ctx}${menu.url}" target="center_data">${menu.name}</a><br/><br/>
                </c:forEach>
            </div>

        </c:forEach>
        <%--<div title="Title3" style="padding:10px">--%>
            <%--content3--%>
        <%--</div>--%>
        <%--<div title="系统管理" style="padding:10px;">--%>
            <%--<a href="${ctx}/manage/resource/list" target="center_data">资源列表</a><br/><br/>--%>
            <%--<a href="${ctx}/manage/privilege/list" target="center_data">权限列表</a><br/><br/>--%>
            <%--<a href="${ctx}/manage/role/list" target="center_data">角色列表</a><br/><br/>--%>
            <%--<a href="${ctx}/manage/user/list" target="center_data">用户列表</a><br/><br/>--%>
            <%--<a href="${ctx}/manage/menu/list/parent" target="center_data">菜单列表</a><br/><br/>--%>
        <%--</div>--%>
    </div>

</div>
<div id="center" region="center" title="&nbsp;您好，<span style='color:#f00'>${user.username}</span>！
欢迎登录！ 你的角色是：<span style='color:#f00'>
							<c:forEach items="${user.roleSet}" var="role">
                                ${role.name}
							</c:forEach>
						</span>
&nbsp;<a href='${ctx}/manage/logout'>退出</a>
">
    <iframe id="center_data" name="center_data" src="${ctx}/center.jsp" frameborder="0" width="100%" height="100%"></iframe>
</div>

</body>
</html>