<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<hr />
	<script>
		var loginFormSubmitDone = false;
		function loginFormSubmit(form) {
			if (loginFormSubmitDone) {
				alert('처리중입니다.');
				return;
			}
			form.loginId.value = form.loginId.value.trim();
			if (form.loginId.value.length == 0) {
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();
				return;
			}
			form.loginPw.value = form.loginPw.value.trim();
			if (form.loginPw.value.length == 0) {
				alert('로그인 비번을 입력해주세요.');
				form.loginPw.focus();
				return;
			}
			form.submit();
			loginFormSubmitDone = true;
		}
	</script>
	<form action="doLogin" method="POST"
		onsubmit="loginFormSubmit(this); return false;">
		<div>
			아이디 : <input type="text" maxlength="10" placeholder="아이디를 입력해주세요."
				name="loginId" /><br /> 비밀번호 : <input type="password"
				maxlength="10" placeholder="비밀번호를 입력해주세요." name="loginPw" /><br />
			<input type="submit" value="로그인" />
	</form>
	</div>
	<div>
		<a href="list">리스트</a>
	</div>
</html>