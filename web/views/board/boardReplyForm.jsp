<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%
	int boardNum = Integer.parseInt(request.getParameter("bnum"));
    int currentPage = Integer.parseInt(request.getParameter("page"));
%>     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardReplyForm</title>
</head>
<body>
<c:import url="/header.jsp"/>
<c:set var="boardNum" value="${param.bnum}"/>
<c:set var="currentPage" value="${param.page}"/>
<%-- <%@ include file="../../header.jsp" %> --%>
<h1 align="center"><%-- <%= boardNum %> --%>${boardNum}번 글의 댓글달기</h1>
<br>
<form action="/second/breply" method="post">
<input type="hidden" name="page" value="${currentPage}<%-- <%= currentPage %> --%>">
<input type="hidden" name="bnum" value="${boardNum}<%-- <%= boardNum %> --%>">
<input type="hidden" name="bwriter" value="${member.id}<%-- <%= member.getId() %> --%>">
<table align="center">
	<tr><td>제목</td><td><input type="text" name="btitle"></td></tr>
	<tr><td>작성자</td><td><input type="text" readonly value="${member.id}<%-- <%= member.getId() %> --%>"></td></tr>
	<tr><td>내용</td><td><textarea cols="50" rows="7" name="bcontent"></textarea></td></tr>	
	<tr><td colspan="2" align="center">
	<input type="submit" value="댓글등록"> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
		</td></tr>
</table>
</form>
<c:url var="blist" value="/blist">
	<c:param name="page" value="${currentPage}"></c:param>
</c:url>
<h4 align="center"><a href="${blist}<%-- /second/blist?page=<%= currentPage %> --%>">목록</a></h4>
<c:import url="/footer.html"/>
<%-- <%@ include file="../../footer.html" %> --%>
</body>
</html>




