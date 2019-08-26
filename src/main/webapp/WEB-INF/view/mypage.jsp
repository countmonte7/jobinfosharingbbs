<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mypage.css' />" />
</head>
<body>
	<c:if test="${null eq sessionScope.userId }">
		<% response.sendRedirect("main"); %>
	</c:if>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="myPagecontainer">
		<h2>마이 페이지</h2>
		<table id="mypageTbl">
			<tbody>
				<tr>
					<td>아이디</td>
					<td>${user.userId}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${user.name}</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" /><button type=button>비밀번호 변경</button></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${user.email}</td>
				</tr>
				<tr>
					<td>가입일자</td>
					<td><fmt:formatDate value="${user.regdate}" var="dateInFormat" 
					type="both" pattern="yyyy년 MM월 dd일" />${dateInFormat}</td>
				</tr>
				<tr>
					<td><button type="button">정보 수정하기</button></td>
					<td><button type="button">회원 탈퇴</button></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>