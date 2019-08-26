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
		<div class="container">
			<img src="" alt="logo" class="logo">
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}/">����������</a></li>
					<li><a href="#">����</a></li>
					<li><a href="${pageContext.request.contextPath}/list">�����Խ���</a></li>
					<li><a id="mypageLink">����������</a></li>
				</ul>
			</nav>
		</div>
	</header>
</body>
<script type="text/javascript">
	$("#mypageLink").click(function() {
		if(${null eq sessionScope.userId}) {
			alert('�α����� �ؾ� �� �� �ֽ��ϴ�.');
			return;
		}else {
			location.href="${pageContext.request.contextPath }/mypage";
		}
	});
</script>
</html>