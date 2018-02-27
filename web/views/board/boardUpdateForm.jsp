<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ page import="board.model.vo.*" %>
<%
	Board b = (Board)request.getAttribute("board");
    int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardUpdateForm</title>
</head>
<body>
<c:import url="/header.jsp"/>
<c:set var="b" value="${requestScope.board}"/>
<c:set var="currentPage" value="${requestScope.currentPage}"/>
<h1 align="center">${b.boardNum}번 글 수정 페이지</h1>
<br>
<c:if test="${b.boardLevel eq 0}">
<form action="/second/bupdate" method="post" enctype="multipart/form-data">
</c:if><c:if test="${b.boardLevel ne 0}">
<form action="/second/brupdate" method="post">
</c:if>
<input type="hidden" name="page" value="${currentPage}">
<input type="hidden" name="bnum" value="${b.boardNum}">
<input type="hidden" name="bwriter" value="${member.id}">
<table align="center">
	<tr><td>제목</td><td><input type="text" name="btitle" value="${b.boardTitle}"></td></tr>
	<tr><td>내용</td><td><textarea cols="50" rows="7" name="bcontent">${b.boardContent}</textarea></td></tr>
	<c:if test="${b.boardLevel eq 0}">
	<c:if test="${!empty b.boardOriginalFileName}">
	<c:url var="bfdown" value="/bfdown">
		<c:param name="ofile" value="${b.boardOriginalFileName}"/>
		<c:param name="rfile" value="${b.boardRenameFileName}"/>
	</c:url>
	<tr><td>첨부파일</td><td><a href="${bfdown}">${b.boardOriginalFileName}</a></td></tr>
	</c:if>
	<tr><td>첨부파일</td><td><input type="file" name="upfile"></td></tr>
	</c:if>
	<tr><td colspan="2" align="center">
	<input type="submit" value="수정하기"> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
		</td></tr>
</table>
</form>
<c:url var="blist" value="/blist">
		<c:param name="page" value="${currentPage}"/>
	</c:url>
<h4 align="center"><a href="${blist}">목록</a></h4>

<%-- <%@ include file="../../header.jsp" %> --%>
<%-- <h1 align="center"><%= b.getBoardNum() %>번 글 수정 페이지</h1>
<br>
<% if(b.getBoardLevel() == 0) { %>
<form action="/second/bupdate" method="post" enctype="multipart/form-data">
<% } else { %>
<form action="/second/brupdate" method="post">
<% } %>
<input type="hidden" name="page" value="<%= currentPage %>">
<input type="hidden" name="bnum" value="<%= b.getBoardNum() %>">
<input type="hidden" name="bwriter" value="<%= member.getId() %>">
<table align="center">
	<tr><td>제목</td><td><input type="text" name="btitle" value="<%=b.getBoardTitle()%>"></td></tr>
	<tr><td>내용</td><td><textarea cols="50" rows="7" name="bcontent"><%= b.getBoardContent() %></textarea></td></tr>
	<% if(b.getBoardLevel() == 0) { %>
	<% if(b.getBoardOriginalFileName() != null) { %>
	<tr><td>첨부파일</td><td><a href="/second/bfdown?ofile=<%= b.getBoardOriginalFileName() %>&rfile=<%= b.getBoardRenameFileName() %>"><%= b.getBoardOriginalFileName() %></a></td></tr>
	<% } %>
	<tr><td>첨부파일</td><td><input type="file" name="upfile"></td></tr>
	<% } %>	
	<tr><td colspan="2" align="center">
	<input type="submit" value="수정하기"> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
		</td></tr>
</table>
</form>
<h4 align="center"><a href="/second/blist?page=<%= currentPage %>">목록</a></h4> --%>
<c:import url="/footer.html"/>
<%-- <%@ include file="../../footer.html" %> --%>
</html>