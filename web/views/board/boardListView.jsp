<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="boardError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ page import="board.model.vo.Board, java.util.ArrayList, java.sql.Date, member.model.vo.Member" %>    
<%
	//ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	//Member member = (Member)session.getAttribute("member");
%> --%>

<c:set var="listCount" value="${requestScope.listCount}"/>
<c:set var="currentPage" value="${requestScope.currentPage}"/>
<c:set var="startPage" value="${requestScope.startPage}"/>
<c:set var="endPage" value="${requestScope.endPage}"/>
<c:set var="maxPage" value="${requestScope.maxPage}"/>
<c:set var="list" value="${requestScope.list}"/>
<c:set var="member" value="${sessionScope.member}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardListView</title>
<script type="text/javascript">
	function showInsertForm(){
		location.href = "views/board/boardInsertForm.jsp";
	}
</script>
</head>
<body>

<%-- <%@ include file="../../header.jsp" %> --%>
<c:import url="/header.jsp"/>
<h1 align="center">게시글 목록</h1>
<h3 align="center">총 게시글 갯수 : ${listCount}
<c:if test="${!empty member}">
&nbsp; &nbsp; &nbsp; <button onclick="showInsertForm();">글쓰기</button>
</c:if></h3>
<table align="center" border="1" cellspacing="0" width="700">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th>
<th>조회수</th><th>첨부파일</th></tr>

<c:forEach var="b" items="${list}">

<tr><td align="center">${b.boardNum}</td>
<!-- 답글일 때는 들여쓰기함 -->
<td>
	<c:if test="${b.boardLevel eq 1}"> <!-- 원글의 답글일 때 -->
	 &nbsp; &nbsp; ▶ 
	 </c:if><c:if test="${b.boardLevel eq 2}"> <!-- 답글의 답글일 때 -->
	 &nbsp; &nbsp; &nbsp; &nbsp; ▶▶ 
	</c:if>
	<!-- 로그인한 사용자만 상세보기할 수 있게 처리함 -->
	<c:if test="${!empty member}">
	<c:url var="bdetail" value="/bdetail">
		<c:param name="bnum" value="${b.boardNum}"/>
		<c:param name="page" value="${currentPage}"/>
	</c:url>
	<a href="${bdetail}">${b.boardTitle}</a>
	</c:if><c:if test="${empty member}">
	${b.boardTitle}
	</c:if>
</td>
<td align="center">${b.boardWriter}</td>
<td align="center">${b.boardDate}</td>
<td align="center">${b.boardReadCount}</td>
<td align="center">
	<c:if test="${!empty b.boardOriginalFileName}">
		 ◎
	</c:if><c:if test="${empty b.boardOriginalFileName}">
		&nbsp;
	</c:if>
</td></tr>
</c:forEach>
<!-- 페이지 번호 처리 -->
<tr align="center" height="20">
<td colspan="6">
	<c:if test="${currentPage <= 1}">
		[이전] &nbsp;
	</c:if><c:if test="${currentPage > 1}">
	<c:url var="blistST" value="/blist">
		<c:param name="page" value="${currentPage-1}"/>
	</c:url>
		<a href="${blistST}">[이전]</a> &nbsp;
	</c:if>
	<!-- 페이지 숫자 보여주기 -->
	<c:forEach var="p" begin="${startPage}" end="${endPage}">
	<c:if test="${p eq currentPage}">
	<font color="red" size="4"><b>[${p}]</b></font>
	</c:if><c:if test="${p ne currentPage}">
	<c:url var="blistchk" value="/blist">
		<c:param name="page" value="${p}"/>
	</c:url>
		<a href="${blistchk}">${p}</a>
	</c:if>
	</c:forEach>
	<c:if test="${currentPage >= maxPage}">
		[다음]
	</c:if><c:if test="${currentPage < maxPage}">
	<c:url var="blistEND" value="/blist">
		<c:param name="page" value="${currentPage+1}"/>
	</c:url>
		<a href="${blistEND}">[다음]</a>
	</c:if>
</td>
</tr>
</table>
<br><br>
<c:import url="/footer.html"/>
<%-- <%@ include file="../../footer.html" %> --%>

<%-- <%@ include file="../../header.jsp" %>
<c:import url="/header.jsp"/>
<h1 align="center">게시글 목록</h1>
<h3 align="center">총 게시글 갯수 : ${listCount}<%= listCount %>
<% if(member != null){ %>
<c:if test="${!empty member}">
&nbsp; &nbsp; &nbsp; <button onclick="showInsertForm();">글쓰기</button>
<%  } %>
</c:if></h3>
<table align="center" border="1" cellspacing="0" width="700">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th>
<th>조회수</th><th>첨부파일</th></tr>
<%
	for(Board b : list){
%>
<c:forEach var="b" items="list">
<tr><td align="center">${b.boardNum}<%= b.getBoardNum() %></td>
답글일 때는 들여쓰기함
<td>
	<c:if test="${b.boardLevel eq 1}">
	<% if(b.getBoardLevel() == 1){  //원글의 답글일 때 %>
	 &nbsp; &nbsp; ▶ 
	 </c:if><c:if test="${b.boardLevel eq 2}">
	<% }else if(b.getBoardLevel() == 2){ //답글의 답글일 때 %>
	 &nbsp; &nbsp; &nbsp; &nbsp; ▶▶ 
	<% } %>
	</c:if>
	로그인한 사용자만 상세보기할 수 있게 처리함
	<c:if test="${!empty member}">
	<% if(member != null){ %>
	<c:url var="bdetail" value="/bdetail">
		<c:param name="bnum" value="${b.boardNum}"/>
		<c:param name="page" value="${currentPage}"/>
	</c:url>
	<a href="${bdetail}">${b.boardTitle}</a>
		<a href="/second/bdetail?bnum=<%= b.getBoardNum() %>&page=<%= currentPage %>">${b.boardTitle}<%= b.getBoardTitle() %></a>
	<% }else{ %>
	</c:if><c:if test="${empty member}">
	${b.boardTitle}
		<%= b.getBoardTitle() %>
	<% } %>
	</c:if>
</td>
<td align="center"><%= b.getBoardWriter() %></td>
<td align="center"><%= b.getBoardDate() %></td>
<td align="center"><%= b.getBoardReadCount() %></td>
<td align="center">
	<% if(b.getBoardOriginalFileName() != null){  //원글의 답글일 때 %>
		 ◎
	<% }else{ %>
		&nbsp;
	<% } %>	 
</td></tr>
</c:forEach>
<%  } //for close  %>
페이지 번호 처리
<tr align="center" height="20">
<td colspan="6">
	<%  if(currentPage <= 1){	%>
		[이전] &nbsp;
	<%  }else{ %>
		<a href="/second/blist?page=<%= currentPage - 1 %>">[이전]</a> &nbsp;
	<%  } %>
	페이지 숫자 보여주기
	<%  for(int p = startPage; p <= endPage; p++){ 
			if(p == currentPage){
	%><font color="red" size="4"><b>[<%= p %>]</b></font>
	<%     }else{ %>
		<a href="/second/blist?page=<%= p %>"><%= p %></a>
	<%  }} //else and for close %>
	<% if(currentPage >= maxPage){ %>
		[다음]
	<% }else{ %>
		<a href="/second/blist?page=<%= currentPage + 1 %>">[다음]</a>
	<% } %>
</td>
</tr>
</table>
<br><br>
<c:import url="/footer.html"/>
<%@ include file="../../footer.html" %> --%>
</body>
</html>





