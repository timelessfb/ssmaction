<html>
<body>
<h2>Hello World!</h2>
<a href="test.jsp">test</a>
<a href="${pageContext.request.contextPath}/test.jsp">test</a>
<a href="${pageContext.request.contextPath}${pageContext.request.contextPath}/test.jsp">test</a>
<a href="baidu">baidu</a>
<a href="admin_category_list">admin_category_list</a>
<a href="${pageContext.request.contextPath}/admin_category_list">admin_category_list</a>
</body>
</html>
