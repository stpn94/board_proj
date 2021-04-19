<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC게시판</title>
<link rel = "stylesheet" href = "css/qna_board_delete.css">
</head>
<body>
<section id = "passForm">
	<form name = "deleteForm" action="boardDeletePro.do?board_num=${board_num }" method = "post">
	<input type = "hidden" name = "page" value = "${page }"/>
	<table>
		<tr>
			<td>
				<label>글비밀번호 : </label>
			</td>
			<td>
				<input name="BOARD_PASS" type = "password">
			</td>
		</tr>
		<tr>
			<td>
				<input type = "submit" value = "삭제"/>
				&nbsp;&nbsp;
				<input type = "button" value = "돌아가기" onClick = "javascript:history.go(-1)"/>
			</td>
		</tr>
	</table>
	</form>

</section>
</body>
</html>