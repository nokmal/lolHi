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
	<c:forEach items="${article}" var="article">
		번호: ${article.id}
		<br/>
		regDate: ${article.regDate}
		<br/>
		updateDate: ${article.updateDate}
		<br/>
		title: ${article.title}
		<br/>
		body: ${article.body}
		<br/><hr/>	
	</c:forEach>
	</div>

</body>
</html>