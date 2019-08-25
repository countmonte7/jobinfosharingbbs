<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/signup.css' />">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<form action="${pageContext.request.contextPath}/member/signupAction" method="post" id="signupForm">
		<h2>회원가입</h2>
		<table id="signupTbl">
			<tr>
				<td><input type="text" id="userId" name="userId" placeholder="아이디(영,숫자, 특수문자(-,_,.) 사용 4자 이상)"></td>
				<td id="dupleChk"><input type="button" id="dupleChkBtn" value="중복확인"></td>
			</tr>
			<tr>
				<td><p class="idCheckResult"></p></td>
			</tr>
			<tr>
				<td><input type="password" id="password" name="password" placeholder="패스워드"></td>
			</tr>
			<tr>
				<td><input type="password" id="password2" name="password2"  placeholder="패스워드2"></td>
			</tr>
			<tr>
				<td><p class="passwordCheck"></p></td>
			</tr>
			<tr>
				<td><input type="email" id="email" name="email"  placeholder="이메일"></td>
			</tr>
			<tr>
				<td><input type="text" id="name" name="name"  placeholder="이름"></td>
			</tr>
			<tr>
				<td colspan="2" id ="signupSec"><button type="button" id="signUpBtn">회원가입</button></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	$("#dupleChkBtn").click(function() {
		$(this).data('clicked', true);
	})
	$(document).ready(function() {
		$("#password2, #password").keyup(function() {
			var password = $("#password").val();
			var password2 = $("#password2").val();
			if(password!='' && password2!='' && password != password2) {
				$(".passwordCheck").html("패스워드가 일치하지 않습니다.");
			}else if(password!='' && password2!='' && password == password2) {
				$(".passwordCheck").html("");
			}
		});
		
		$("#dupleChkBtn").click(function(){
			
			var data = {userId : $("#userId").val()};
			if(data.userId=="") {
				$(".idCheckResult").text("아이디를 입력하세요.");
				return;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/member/idCheck",
				type : "post",
				data : data,
				success : function(data) {
					console.log(data);
					if(data == 0) {
						$(".idCheckResult").text("이미 존재하는 아이디입니다.");
					}else if(data == 1){
						$(".idCheckResult").text("사용할 수 있는 아이디입니다.");
					}else if(data == -1) {
						$(".idCheckResult").text("잘못된 형식의 아이디입니다.");
					}
				}
			});
		});
		
		$("#signUpBtn").click(function(evt) {
			evt.preventDefault();
			var userId = $("#userId").val().trim().replace(/\s/g, "");
			var password = $("#password").val().trim().replace(/\s/g, "");
			var password2 = $("#password2").val().trim().replace(/\s/g, "");
			var email = $("#email").val().trim();
			var name = $("#name").val().trim();
			
			var emailData = {"email" : email};
			
			var form = $("form[id=signupForm]").serialize();
			
			//validation을 위한 정규식
			
			var emailRegex = /^[0-9a-zA-Z_\.\-]+@[0-9a-zA-Z\-]+\.[0-9a-zA-Z\-]+/;
			var nameRegex = /^([a-zA-Z]{2-10}\s[a-zA-Z]{2,10}|[가-힣]{2,6})$/;
			var passRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%&*?])[a-zA-Z\d-_!@#$%\^&*]{8,20}$/;
			
			//validation
			
			if(!$("#dupleChkBtn").data('clicked')) {
				alert("아이디 중복확인을 해주세요.");
			}else if(userId =='' || password=='' || password2=='' || email=="" || name=="") {
				alert("입력되지 않은 사항이 있습니다.");
			}else if(!passRegex.test(password)) {
				alert("패스워드 형식이 올바르지 않습니다.(숫자, 문자, 특수문자 혼용 8~20자)");
			}else if(password !== password2){
				alert("패스워드 값이 일치하지 않습니다.");
			}else if(!emailRegex.test(email)) {
				alert("올바른 이메일 형식이 아닙니다.");
			}else if(!nameRegex.test(name)) {
				alert("올바른 이름이 아닙니다.");
			}else {
				$.ajax({
					url : "${pageContext.request.contextPath}/member/emailCheck",
					type: "post",
					data: emailData,
					success: function(data) {
						if(data==0) {
							alert("이미 가입된 이메일 주소입니다.");
						}else {
							$.ajax({
								url : "${pageContext.request.contextPath}/member/signupAction",
								type : "post",
								data : form,
								error : function(msg, status, data) {
									alert(msg);
								},
								success : function(data) {
									alert("회원가입 성공");
									location.href = '${pageContext.request.contextPath}/main';
								}
							});
						}
					}
				});
			}
			});
	});
</script>
</html>