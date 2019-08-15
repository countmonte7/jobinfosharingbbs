<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css' />" >
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp" flush="false"></jsp:include>
	<section id="loginSection">
		아이디 : <input type="text" id="userId" placeholder="아이디">
		패스워드 : <input type="password" id="userPwd" placeholder="패스워드">
		<input type="button" onclick="login()" value="로그인">
		<input type="button" onclick="getSignUpPage()" value="회원가입">
	</section>
</body>
<script>
	function getSignUpPage() {
		location.href="signUp";
	}
	function login() {
		var id = $("#userId").val();
		var password = $("#userPwd").val();
	}
</script>
</html>