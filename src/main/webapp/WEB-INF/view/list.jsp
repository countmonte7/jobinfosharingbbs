<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  	
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />" >
</head>
<body>
	<jsp:include page="header.jsp" flush="false"></jsp:include>
	<section>
		<h1>자유 게시판</h1>
		<table id="listTbl">
			<thead>
				<tr>
					<th>글제목</th>
					<th>글쓴이</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="bbs">
					<tr>
					<td><a href="${pageContext.request.contextPath}/detailview?id=${bbs.id}">${bbs.title}</a></td>
					<td>${bbs.writer}</td>
					<td>${bbs.regdate}<br></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<div id="writeLink">
		<a id="writeBtn" href="${pageContext.request.contextPath}/writeBbs">글쓰기</a>
	</div>
</body>
</html>