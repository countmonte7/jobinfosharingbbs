<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script
  src="https://code.jquery.com/jquery-3.4.1.slim.js"
  integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI="
  crossorigin="anonymous"></script>

<c:set var="message" value="${message}" />

<c:choose>
	<c:when test="${message eq 'success' }">
		<script>
			$(function() {
				var message = "글을 삭제했습니다.";
				alert(message);
				window.location="list";
			})
		</script>
	</c:when>
	<c:when test="${message eq 'fail' }" >
		<script>
			$(function() {
				var message = "글 삭제를 실패했습니다.";
				alert(message);
				history.back();
			})
		</script>
	</c:when>
</c:choose>