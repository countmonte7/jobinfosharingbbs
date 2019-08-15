<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/signup.css' />">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<form action="signupAction" method="post">
		<h2>회원가입</h2>
		<table id="signupTbl">
			<tr>
				<td><input type="text" placeholder="아이디"></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="패스워드"></td>
			</tr>
			<tr>
				<td><input type="password" placeholder="패스워드2"></td>
			</tr>
			<tr>
				<td><input type="email" placeholder="이메일"></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="이름"></td>
			</tr>
			<tr>
				<td><input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
</body>
</html>