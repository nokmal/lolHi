<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성페이지</title>
</head>
<body>
	<h1>게시물 작성페이지</h1>
	<hr/>
	<div>
	<form action="doWrite" method="post">
		제목 : <input type="text" maxlength="30" placeholder="제목을 입력해주세요." name="title" /><br />
		내용 : <input type="text" maxlength="300" placeholder="내용을 입력해주세요."name="body" /><br />		
		<input type="submit" value="등록" />
		</form>		
	</div>

</body>
</html>