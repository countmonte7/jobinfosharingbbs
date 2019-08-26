<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/writestyles.css' />" >
</head>
<body>
	<jsp:include page="header.jsp" flush="false"></jsp:include>
	<div class="container">
		
		<form action="writeAction" method="post">
			<h2>글쓰기</h2>
			<table id="writeTbl">
				<tr>
					<td id="titleTd"><input id="title" type="text" name="title" placeholder="제목" /></td>
				</tr>
				<tr>
					<td id="contentTd" colspan="4"><textarea id="content" name="content" placeholder="내용"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
			<a href="${pageContext.request.contextPath}/list">목록으로</a>
		</form>
	</div>
</body>
</html>