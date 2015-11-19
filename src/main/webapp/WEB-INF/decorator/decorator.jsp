<!doctype html>
<html dir="ltr" lang="zh-CN">
<html>
<head>
    <title><sitemesh:write property='title'/></title>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
    <sitemesh:write property='head'/>
</head>
<body><sitemesh:write property='body'/>
<%@include file="/WEB-INF/jsp/common/left.jsp" %>
<%@include file="/WEB-INF/jsp/common/right.jsp" %>
</body>
</html>
