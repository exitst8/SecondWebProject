<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ page import="board.model.vo.Board, java.util.*, java.sql.Date" %>
<%	
	Board board = (Board)request.getAttribute("board");	
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();	
%>   --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetailView</title>
</head>
<body>
<c:set var="board" value="${requestScope.board}"/>
<c:set var="currentPage" value="${requestScope.currentPage}"/>

<c:import url="/header.jsp"/>
<h1 align="center">게시글 상세보기</h1>
<table align="center" cellpadding="10" cellspacing="0" border="1" width="500"> 
    <tr align="center" valign="middle">  
          <th colspan="2">${board.boardNum} 번글 상세보기</th> 
    </tr>
    <tr><td height="15" width="100">제 목</td>          
        <td>${board.boardTitle}</td> 
    </tr>     
    <tr><td>내 용</td> 
        <td>${board.boardContent}</td>         
    </tr> 
    <tr><td>첨부파일</td>
        <td>
        <c:if test="${empty board.boardOriginalFileName}">
        	첨부파일 없음 
        </c:if><c:if test="${!empty board.boardOriginalFileName}">
        	<c:url var="bfdown" value="/bfdown">
        		<c:param name="ofile" value="${board.boardOriginalFileName}"/>
        		<c:param name="rfile" value="${board.boardRenameFileName}"/>
        	</c:url>
        	<a href="${bfdown}">${board.boardOriginalFileName}</a>         
        	</c:if>
        </td> 
    </tr>
    <tr align="center" valign="middle"> 
        <td colspan="2"> 
        <c:if test="${!empty member}">
        <c:url var="brf" value="views/board/boardReplyForm.jsp">
        	<c:param name="bnum" value="${board.boardNum}"/>
        	<c:param name="page" value="${currentPage}"/>
        </c:url>
            <a href="${brf}"> [댓글달기] </a> &nbsp;&nbsp; 
         <c:if test="${member.id eq board.boardWriter}">
         <c:url var="bupview" value="/bupview">
         	<c:param name="bnum" value="${board.boardNum}"/>
         	<c:param name="page" value="${currentPage}"/>
         </c:url>
         <c:url var="bdelete" value="/bdelete">
         	<c:param name="bnum" value="${board.boardNum}"/>
         </c:url>
            <a href="${bupview}"> [수정페이지로 이동] </a> &nbsp;&nbsp; 
            <a href="${bdelete}"> [글삭제] </a> &nbsp;&nbsp; 
         </c:if></c:if>
         <c:url var="blist" value="/blist">
         	<c:param name="page" value="${currentPage}"/>
         </c:url>
            <a href="${blist}">[목록]</a>            
        </td> 
    </tr> 
</table>
<br>
<c:import url="/footer.html"/>

<%-- <%@ include file="../../header.jsp" %>
<h1 align="center">게시글 상세보기</h1>
<table align="center" cellpadding="10" cellspacing="0" border="1" width="500"> 
    <tr align="center" valign="middle">  
          <th colspan="2"><%= board.getBoardNum() %> 번글 상세보기</th> 
    </tr>      
    <tr><td height="15" width="100">제 목</td>          
        <td><%= board.getBoardTitle() %></td> 
    </tr>     
    <tr><td>내 용</td> 
        <td><%= board.getBoardContent() %></td>         
    </tr> 
    <tr><td>첨부파일</td> 
        <td><% if(board.getBoardOriginalFileName() == null){ %>
        	첨부파일 없음 
        	<% }else{ %>
        	<a href="/second/bfdown?ofile=<%= board.getBoardOriginalFileName() %>&rfile=<%= board.getBoardRenameFileName() %>"><%= board.getBoardOriginalFileName() %></a>         
        	<% } %> 
        </td> 
    </tr>
    <tr align="center" valign="middle"> 
        <td colspan="2"> 
         <% if(member != null){ %>            
            <a href="/second/views/board/boardReplyForm.jsp?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>"> [댓글달기] </a> &nbsp;&nbsp; 
         <% if(member.getId().equals(board.getBoardWriter()) == true){ %>
            <a href="/second/bupview?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>"> [수정페이지로 이동] </a> &nbsp;&nbsp; 
            <a href="/second/bdelete?bnum=<%= board.getBoardNum() %>"> [글삭제] </a> &nbsp;&nbsp; 
         <% }} %>
            <a href="/second/blist?page=<%= currentPage %>">[목록]</a>            
        </td> 
    </tr> 
</table>
<br>
<%@ include file="../../footer.html" %> --%>
</body>
</html>