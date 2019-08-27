<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/detailview.css' />" >
</head>
<body>
	<% pageContext.setAttribute("newLine", "\r\n"); 
		pageContext.setAttribute("br", "<br/>");
	%>
	<jsp:include page="header.jsp" flush="false"></jsp:include>
	<table>
		<tbody>
			<tr>
				<td id="bbsId">${bbs.id}</td>
				<td id="bbsTitle">${bbs.title}</td>
				<td id="bbsWriter">작성자 : ${bbs.userId }</td>
			</tr>
			<tr>
				<td colspan="3"><div id="bbsContent">${fn:replace(bbs.content, newLine, br)} <img src="<spring:url value='/img/${bbs.thumbImg }'/>" width="300"></div></td>
			</tr>
			<tr>
				<td colspan="3" id="bbsDate">작성날짜 : ${bbs.regdate}</td>				
			</tr>
		</tbody>
	</table>
	<c:if test="${sessionScope.userId eq bbs.userId }">
		<div id="updateDeleteGroup">
			<a onclick="updatebbs();">글 수정하기</a>
			<a onclick="delcheck();">글 삭제하기</a>
		</div>
	</c:if>
	<div id="listLink">
		<a href="${pageContext.request.contextPath }/list">목록으로</a>
	</div>
</body>
<script>
	function updatebbs() {
		location.href="${pageContext.request.contextPath}/updateBbs?id=${bbs.id}";
	}

	function delcheck() {
		if(confirm("글을 삭제하시겠습니까?")) {
			location.href="${pageContext.request.contextPath }/deleteBbs?id=${bbs.id}";
			return true;
		}else {
			return false;
		}
	}
</script>
</html>