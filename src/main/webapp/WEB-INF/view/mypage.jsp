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
		<form method="post">
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
						<td><input type="password" id="password" /><button type=button>비밀번호 변경</button></td>
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
						<td><button type="button" id="signOutBtn">회원 탈퇴</button></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$("#signOutBtn").click(function() {
			var userId = "${sessionScope.userId}";
			var password = $('#password').val();
			var sendingData = {userId: userId, password: password};
			$.ajax({
				url: "checkPassword",
				data: sendingData,
				type: "post",
				success: function(data) {
					if(data!="") {
						if(confirm('정말 회원 탈퇴하시겠습니까?')) {
							$.ajax({
								url: "signout",
								data: sendingData,
								type: "post",
								success: function(data) {
									if(data > 0) {
										console.log(data);
										alert('회원 탈퇴 완료되었습니다.');
										location.href="member/logout";
									}else {
										alert('회원 탈퇴에 실패했습니다.');
									}
								},
								error: function(jqXHR) {
									console.log(jqXHR.responseText);
								}
							});
						}
					}else {
						alert('비밀번호가 틀립니다.');
						return;
					}
				}
			});
			
			
		})
	</script>
</body>
</html>