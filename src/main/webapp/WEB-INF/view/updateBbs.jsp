<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.slim.js"
  integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI="
  crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/writestyles.css' />" >
</head>
<body>
	<jsp:include page="header.jsp" flush="false"></jsp:include>
	<div class="container">
		<form role="form" method="post">
			<h2>글쓰기</h2>
			<table id="writeTbl">
				<tr>
					<input type="hidden" name="id">
					<td id="titleTd"><input id="title" type="text" name="title" value="<c:out value="${bbs.title }" />"></td>
					<td>
						<input class="inputBox" type="text" name="writer" 
						placeholder="이름" value="<c:out value="${bbs.writer }"/>" />
					</td>
					<td id="pwTd">
						<input class="inputBox" type="password" name="password" placeholder="비밀번호" />
					</td>
				</tr>
				<tr>
					<td id="contentTd" colspan="4">
						<textarea id="content" name="content" placeholder="내용">${bbs.content}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="수정">
					</td>
				</tr>
			</table>
		</form>
			<a href="${pageContext.request.contextPath}/list">목록으로</a>
	</div>
</body>
</html>