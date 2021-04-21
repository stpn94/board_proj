<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
</head>
<body>
<%-- ${articleList } --%>
	<!-- 게시판 리스트 -->
	<section>
		<h2>
			글 목록
		</h2>
		<h4>
			<a href="boardWriteForm.do">게시판 글쓰기</a>
		</h4>
		<table border="1">
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
					<td id="subject">
		 				<c:if test="${list.board_re_seq!=0}">
			 				<c:forEach begin="0" end="${list.board_re_seq}">
			 					&nbsp;
			 				</c:forEach>
			 				▶
		 				</c:if>
	 					<a href="boardDetail.do?board_num=${list.board_num }&page=${pageInfo.page}"> ${list.board_subject} </a>
					</td>
					<td>${board.board_name}</td>
					<td>${board.board_date}</td>
					<td>${board.board_readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<c:choose>
		<c:when test="${fn:length(articleList) != 0 && pageInfo.listCount > 0 }">
			<section id="pageList">
				<c:choose>
					<c:when test="${pageInfo.page <= 1 }">
						[이전]&nbsp;
					</c:when>
					<c:otherwise>
						<a href="boardList.do?page=${pageInfo.page -1}">[이전]</a>&nbsp;
					</c:otherwise>
				</c:choose>
				
				<c:forEach var="a" begin="1" end="${pageInfo.endPage}">
					<c:choose>
						<c:when test="${a == pageInfo.page}">
							<span>[${a}]</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="boardList.do?page=${a}">[${a}]</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage }">
						[다음]&nbsp;
					</c:when>
					<c:otherwise>
						<a href="boardList.do?page=${pageInfo.page +1}">[다음]</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</section>
		</c:when>
		<c:otherwise>
			<section>등록된 글이 없습니다.</section>
		</c:otherwise>
	</c:choose>	
</body>
</html>