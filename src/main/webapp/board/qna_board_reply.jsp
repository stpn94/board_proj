<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>MVC 게시판</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/write.css">
<!-- <script language="javascript"></script> -->
</head>
<body>
${page }
${article }
	<!-- 게시판 답변 -->
	<section id="writeForm">
		<h2>게시판글등록</h2>
		<form action="boardReplyPro.do" method="post" name="boardform">
			<input type="hidden" name="page" value="${page}" />
			<input type="hidden" name="BOARD_NUM" value="${article.board_num }">
			<input type="hidden" name="BOARD_RE_REF" value="${article.board_re_ref }">
			<input type="hidden" name="BOARD_RE_LEV" value="${article.board_re_lev }">
			<input type="hidden" name="BOARD_RE_SEQ" value="${article.board_re_seq }">
			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input type="text" name="BOARD_NAME" id="BOARD_NAME" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
					<td class="td_right"><input type="password" name="BOARD_PASS" id="BOARD_PASS" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_SUBJECT">제 목</label></td>
					<td class="td_right"><input type="text" name="BOARD_SUBJECT" id="BOARD_SUBJECT" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_CONTENT">내 용</label></td>
					<td><textarea name="BOARD_CONTENT" id="BOARD_CONTENT" cols="40" rows="15"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록" />&nbsp;&nbsp;
				<input type="reset" value="다시작성" />
			</section>
		</form>
	</section>
</body>
</html>