<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Second Web Index</title>
<script type="text/javascript" src="views/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(function() {
	//$.ajax() 사용시
	$.ajax({
		url : "/second/toplist",
		//data : {no : "t5"},
		type : "post",
		dataType : "json",
		success : function(data) {
			//console.log(data);
			var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
			//console.log(jsonStr);
			var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈

			var values = $("#tb").html();

			for ( var i in json.list) {
				//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
				values += "<tr><td align='center'>" + i//json.list[i].bnum
						+ "</td><td><a href='/second/bdetail?bnum="
						+ json.list[i].bnum + "'>"
						+ decodeURIComponent(json.list[i].title)
						+ "</a></td>" + "<td align='center'>"
						+ json.list[i].writer + "</td>"
						+ "<td align='center'>" + json.list[i].date
						+ "</td>" 
						+ "<td align='center'>"
						+ json.list[i].cnt + "</td>"
						+ "<td align='center'>" + json.list[i].file
						+ "</td></tr>";
			}

			$("#tb").html(values);
		},
		error : function(request,status,error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
});
</script>
</head>
<body>
<%-- <%@ include file="header.jsp" %> --%>
<c:import url="header.jsp" />
<br>
	<br>
	<h1 align="center">게시글 TOP 5 목록</h1>
	<hr>
	<table align="center" border="1" cellspacing="0" width="700" id="tb">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
	</table>

<%-- <%@ include file="footer.html" %> --%>
<c:import url="footer.html" />
</body>
</html>






