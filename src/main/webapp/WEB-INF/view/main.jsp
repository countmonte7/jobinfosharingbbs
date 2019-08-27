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
	<c:set var="user" value="${user }" />
	<c:choose>
		<c:when test="${sessionScope.userId eq null }">
			<section id="loginSection">
				<form id="login" method="post" action="${pageContext.request.contextPath }/member/login">
					아이디 : <input type="text" name="userId" id="userId" placeholder="아이디">
					패스워드 : <input type="password" name="password" id="password" placeholder="패스워드">
					<button type="button" id="btnLogin">로그인</button>
					<button type="button" onclick="getSignUpPage()">회원가입</button>
				</form>
			</section>
		</c:when>
		<c:otherwise>
			<section id="loginSection">
				"${sessionScope.userId }"님 환영합니다. &nbsp;&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/member/logout">로그아웃</a>
			</section>
		</c:otherwise>
	</c:choose>
	<div class="boardSummary">
		<details open>
			<summary>채용 공고</summary>
			<table>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
			</table>
		</details>
		<details open>
			<summary>취업 고민</summary>
			<table>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
			</table>
		</details>
		<details open>
			<summary>중고 장터</summary>
			<table>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
				<tr><td>sample</td></tr>
			</table>
		</details>
	</div>
</body>
<script type="text/javascript">
	function getSignUpPage() {
		location.href="signUp";
	}
	$(document).ready(function() {
		$("#btnLogin").click(function() {
			var form = $("#login");
			var userId = $("#userId").val();
			var password = $("#password").val();
			var action = $("#login").attr("action");
			if(userId == "") {
				alert("아이디를 입력하세요.");
				$("#userId").focus();
				return;
			}else if(password == "") {
				alert("패스워드를 입력하세요.");
				$("#password").focus();
				return;
			}else {
				form.submit();
			}
		});
	});
</script>
</html>