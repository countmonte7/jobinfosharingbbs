<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css' />" >
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
	<header>
		<div class="menubar">
			<img src="" alt="logo" class="logo">
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}/">메인페이지</a></li>
					<li><a href="#">방명록</a></li>
					<li id="board">
						자유게시판
						<ul>
							<li><a href="list">공채소식</a></li>
							<li><a href="#">자소서 쓰기</a></li>
							<li><a href="#">취업고민</a></li>
							<li><a href="secondhandlist">취업중고장터</a></li>
						</ul>
					</li>
					<li><a id="mypageLink">마이페이지</a></li>
				</ul>
			</nav>
		</div>
	</header>
</body>
<script type="text/javascript">
	$("#mypageLink").click(function() {
		if( ${null eq sessionScope.userId} ) {
			alert('로그인을 해야 볼 수 있습니다.');
			location.href="main";
		}else {
			location.href="${pageContext.request.contextPath }/mypage";
		}
	});
</script>
</html>