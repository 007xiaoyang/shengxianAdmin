<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账号密码错误，跳转到登陆页面</title>
</head>
<body onload="window.location.href='${pageContext.request.contextPath }/login.jsp?result=${result}'">
</body>
</html>
