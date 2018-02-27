<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="notice.model.vo.Notice, member.model.vo.Member" %> --%>    
<%-- <%
	Notice notice = (Notice)request.getAttribute("notice");
	String fileName = null;
	if(notice.getFilePath() != null){
		fileName = notice.getFilePath()
			.substring(notice.getFilePath().lastIndexOf("/") + 1);
	}
	
	Member member = (Member)session.getAttribute("member");
%>    --%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="notice" value="${requestScope.notice }" />
<c:set var="filePath" value="${notice.filePath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeDetailView</title>
</head>
<body>
<c:import url="/header.jsp" />
<h1 align="center"> ${notice.noticeNo }번글 상세 보기</h1>
<br><br>
<table align="center">
	<tr><td>번호</td><td>${notice.noticeNo }</td></tr>
	<tr><td>제목</td><td>${notice.noticeTitle }</td></tr>
	<tr><td>작성자</td><td>${notice.noticeWriter }</td></tr>
	<tr><td>올린날짜</td><td>${notice.noticeDate }</td></tr>
	<c:if test="${!empty filePath }">
	<tr><td>첨부파일</td>
		<td>
		<c:url var="nfdown" value="/nfdown">
			<c:param name="path" value="${filePath}"/>
		</c:url>
		<a href="${nfdown}">
		${filePath}</a>
		</td></tr>
	</c:if>
	<tr><td>내용</td><td>${notice.noticeContent}</td></tr>
	<c:url var="nupview" value="/nupview">
		<c:param name="no" value="${notice.noticeNo}"/>
	</c:url>
	<c:url var="ndelete" value="/ndelete">
		<c:param name="no" value="${notice.noticeNo}"/>
	</c:url>
	<c:if test="${sessionScope.member.id eq notice.noticeWriter}">
	<tr><td colspan="2" align="center"> 
	<a href="${nupview}">수정페이지로 이동</a> &nbsp; &nbsp;
	<a href="${ndelete}">삭제하기</a>
	<%-- 
	<button onclick="location.href='/second/ndelete?no=<%= notice.getNoticeNo() %>'">삭제</button>
	--%>
	</td></tr>
	</c:if>	
</table>
<br><br>
<p align="center">
<c:url var="index" value="/index.jsp"/>
<c:url var="nlist" value="/nlist"/>
<a href="${index}">시작페이지로 이동</a> &nbsp; &nbsp;
<a href="${nlist}">목록보기로 이동</a>
<!-- <a href="/second/index.jsp">시작페이지로 이동</a> &nbsp; &nbsp;
<a href="/second/nlist">목록보기로 이동</a> -->
</p>
<c:import url="/footer.html" />
</body>
</html>





