<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>后台登录页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript">
		var verifyCodeUrl = "${ctx}/randomCode.servlet?";
		$(function() {
			$('#codeImg').attr('src',verifyCodeUrl + (new Date()).getTime());
			$('#codeImg').click(function() {
				$(this).attr('src',verifyCodeUrl + (new Date()).getTime());
			});
		});
	</script>

  </head>

  <body>
  	<div align="center">
        <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">用户名或者密码有错${SPRING_SECURITY_LAST_EXCEPTION.message}</c:if>
        ${SPRING_SECURITY_RANDOM_CODE}

        <form action="/manage/login" method="post">
			用户名：<input type="text" name="j_username" />
			密码：<input type="password" name="j_password" />
			验证码:<input type="text" name="j_randomCode"/>
			<input type="submit" value="登录"/>
		</form>
    <span><img id="codeImg" border=0>再换一张</span>
    </div>
  </body>
</html>
