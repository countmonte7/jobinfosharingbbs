<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/comment.css' />">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form id="commentForm" name="commentForm">
			<br><br>
				<div>
					<div style="margin-left:250px;">
						<span>Comments</span> <span id="cmtCount"></span>
					</div>
					<div>
						<table class="cmtTbl">
							<tr>
								<td>
									<textarea style="width: 800px" rows="3" cols="30" id="commentContent" name="comment" 
									placeholder="댓글을 입력하세요."></textarea>
									<br>
									<div>
										<a id="cmntLink" href="#" onClick="fn_comment()">댓글등록</a>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<input type="hidden" id="b_code" name="b_code" value="${bbs.id }" />
				<input type="hidden" id="writer" name="writer" value="${sessionScope.userId }" />
		</form>
	</div>
	<div>
		<div id="commentCount">
			${cmtcount };
		</div>
		<table id="commentSection">
			
		</table>
	</div>
	<script type="text/javascript">
		function fn_comment() {
			var writer = $("#writer").val();
			if(writer==null || writer=="") {
				alert("로그인을 해야 댓글을 쓸 수 있습니다.");
				return;
			}else {
				$.ajax({
					type: "post",
					url: "addComment",
					data: $("#commentForm").serialize(),
					success : function(data) {
						console.log(data)
						if(data!=null && data!="") {
							alert('댓글이 등록되었습니다.');
							getComments();
							$("#commentContent").val("");
						}
					},
					error : function(jqXHR) {
						console.log(jqXHR.responseText);
					}
				});
			}
		}
		
		$(function() {
			getComments();
		});
		
		function getComments() {
			var bbsId = $("#b_code").val();
			$.ajax({
				type: "get",
				url: "getCommentList",
				data: {"b_code": bbsId},
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success: function(result) {
					var html = "";
					obj = JSON.parse(result);
					for(i=0;i<obj.length;i++) {
						html += "<tr><td class='cmtwriterClass'><h5>" + obj[i].writer + "</h5></td>";
						html += "<td class='cmtcontentClass'>" + obj[i].comment +"</td>";
						html += "<td style='font-size:11px;'>" + obj[i].date  + "</td>";
						html += "<td class='cmtbtnClass'><button type='button' "+ "id='updatebtn" + obj[i].commentId + "'>수정</button>"; 
						html += "<button type='button' "+ "id='deletebtn" + obj[i].commentId + "'>삭제</button>";
						html += "<button type='button' "+ "id='addcmtbtn" + obj[i].commentId + "'>댓글달기</button>";
						html += "</td></tr>";
					}
					$("#commentSection").html(html);
						
				}
			});
		}
		
	</script>
</body>
</html>