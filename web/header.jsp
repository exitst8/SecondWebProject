<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <%@ page import="member.model.vo.Member, java.util.*"  %>     --%>
<%-- jsp comment element --%>    
<!-- html comment tag -->
<%-- <%
	Member member = (Member)session.getAttribute("member");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>second</title>
<style type="text/css">
	.login { float : right; }
	ul li a {
		display : block;
		width : 120px;
		height : 35px;
		background : orange;
		padding-top : 5px;
	}
	
	ul li a:hover {
		background : olive;
		color : white;
		text-shadow : 2px 2px 5px black;
	}
	
	ul li {
		list-style : none;
		float : left;
		margin-right : 5px;
		padding-top : 10px;
		text-align : center;
		font-size : 16pt;
		font-weight : bold;
	}
</style>
</head>
<body>
<header>
<h1>secondWebProject</h1>

</header>
<hr>
<%-- <%
	if(member == null){  //로그인하지 않았다면
%> --%>
<c:set var="member" value="${sessionScope.member}"/>
<c:if test="${empty sessionScope.member }">
<form action="login" method="post" class="login">
<table class="login" width="250" cellpadding="0" cellspacing="0" bgcolor="yellow">
	<tr><td>아이디 : </td><td><input type="text" name="userid"></td></tr>
	<tr><td>암 호 : </td><td><input type="password" name="userpwd"></td></tr>
	<tr><td colspan="2" align="center">
	<input type="submit" value="로그인"> &nbsp; &nbsp; 
	<a href="views/member/enroll.html">회원가입</a>
	</td></tr>
	<tr><td colspan="2" align="center">아이디/암호가 기억나지 않는다면</td></tr>
</table>
</form>
</c:if>
<%-- <% }else{ %> --%>
<c:if test="${!empty sessionScope.member }">
<table class="login" width="250" cellpadding="0" cellspacing="0" bgcolor="yellow">
	<tr><td><%-- <%= member.getName() %> --%> 
	${ member.name } 님</td>
	<c:url var="lout" value="logout" />
	<td><a href="${lout }">로그아웃</a></td></tr>
	<tr><td></td><td></td></tr>
	<tr><td colspan="2" align="center">
	<c:url var="myinfo" value="views/member/myinfo.jsp" />
	<a href="${myinfo }">내 정보보기</a>	
	</td></tr>
	<tr><td colspan="2" align="center"></td></tr>
</table>
</c:if>
<%-- <% } %> --%>
<c:url var="nl" value="nlist" />
<c:url var="bl" value="blist" />
<nav>
	<ul>
		<li><a href="${nl }">공지사항</a></li>
		<li><a href="${bl }">게시판</a></li>
		<li><a>etc</a></li>
		<li><a>etc</a></li>
	</ul>
</nav>
<hr style="clear:both;">
</body>
</html>