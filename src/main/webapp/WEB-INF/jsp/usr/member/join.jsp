<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<hr/>
	<div>
	<form action="doJoin" method="post">
		이름 : <input type="text" maxlength="30" placeholder="이름을 입력해주세요." name="name" /><br />
		아이디 : <input type="text" maxlength="10" placeholder="아이디를 입력해주세요."name="loginId" /><br />
		비밀번호 : <input type="password" maxlength="10" placeholder="비밀번호를 입력해주세요."name="loginPw" /><br />
		<input type="submit" value="가입" />
		</form>		
	</div>
	<div>
		<a href="list">리스트</a>
	</div>
</html>