<%@page import="board_proj.dto.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qna_board_list</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/list.css">
</head>
<body>
	<%=request.getContextPath()%>
	<section id="list">
		<h2>글목록</h2>
		<h4>
			<a href="boardWriteForm.do">게시판 글쓰기</a>
		</h4>
		<table class="tbl_type">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
			<c:forEach var="board" items="${articleList }">
				<tr>
					<td>${board.board_num}</td>
					<td><a href = "boardDetail.do?board_num=${board.board_num }&page=${pageInfo.page}">${board.board_subject}</a></td>
					<td>${board.board_name}</td>
					<td>${board.board_date}</td>
					<td>${board.board_readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
		<section id = "pageList">
		<c:if test = "${pageInfo.page <=1 }">
			[이전]&nbsp;
		</c:if>
		<c:if test = "${pageInfo.page >1 }">
			<a href = "boardList.do?page=${pageInfo.page -1}">[이전]</a> &nbsp;
		</c:if>
		
		<c:forEach var="a"  begin="1" end="${pageInfo.endPage }">
			<c:if test="${a == pageInfo.page}">
				<span>[${a}]</span>
			</c:if>		
			<c:if test = "${ a ne pageInfo.page }">
				<a href = "boardList.do?page=${a}">[${a}]</a>
			</c:if>
		</c:forEach>
		
		<c:if test = "${pageInfo.page >= pageInfo.maxPage }">
			[다음]&nbsp;
		</c:if>
		<c:if test = "${pageInfo.page <pageInfo.maxPage }">
			<a href = "boardList.do?page=${pageInfo.page +1}">[다음]</a> &nbsp;
		</c:if>
		
	</section>
</body>
</html>