<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정페이지</title>
</head>
<body>
	<h1>게시물 수정페이지</h1>
	<hr/>
	<div>
	<form action="doModify" method="post">
		게시글 번호: <input type="text" name="id" /><br />
		제목 : <input type="text" name="title" /><br />
		내용 : <input type="text" name="body" /><br />		
		<input type="submit" value="등록" />
		</form>		
	</div>

</body>
</html>