<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  	
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
					<th id="titleTh">글제목</th>
					<th>글쓴이</th>
					<th>작성날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="bbs">
					<fmt:formatDate value="${bbs.regdate }" var="dateInFormat" 
						type="both" pattern="yyyy년 MM월 dd일 HH시 mm분"/>
					<tr>
					<c:choose>
						<c:when test="${fn:length(bbs.title) > 25 }">
							<td>
								<a href="${pageContext.request.contextPath}/detailview?id=${bbs.id}">
									${fn:substring(bbs.title, 0, 25)} ...
								</a>
							</td>
						</c:when>
						<c:otherwise>
							<td>
								<a href="${pageContext.request.contextPath}/detailview?id=${bbs.id}">
									${bbs.title}
								</a>
							</td>
						</c:otherwise>
					</c:choose>
					<td>${bbs.userId}</td>
					<td>${dateInFormat}</td>
					<td>${bbs.count}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<c:if test="${null ne sessionScope.userId }">
		<div id="writeLink">
			<a id="writeBtn" href="${pageContext.request.contextPath}/writeBbs">글쓰기</a>
		</div>
	</c:if>
</body>
</html>