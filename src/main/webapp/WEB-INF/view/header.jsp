<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />" >
</head>
<body>
	<header>
		<div class="container">
			<img src="" alt="logo" class="logo">
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}/">����������</a></li>
					<li><a href="#">����</a></li>
					<li><a href="${pageContext.request.contextPath}/list">�����Խ���</a></li>
					<li><a href="#">����������</a></li>
				</ul>
			</nav>
		</div>
	</header>
</body>
</html>