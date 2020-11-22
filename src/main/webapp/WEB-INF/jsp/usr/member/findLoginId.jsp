<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원 로그인" />
<%@ include file="../part/head.jspf"%>
<hr />
<script>
	var loginFormSubmitDone = false;
	function loginFormSubmit(form) {
		if (loginFormSubmitDone) {
			alert('처리중입니다.');
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		form.email.value = form.email.value.trim();

		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.submit();
		findLoginIdFormSubmitDone = true;
	}
</script>
<form action="doLogin" method="POST"
	onsubmit="loginFormSubmit(this); return false;">
	<div>
		아이디 :
		<input type="text" maxlength="10" placeholder="아이디를 입력해주세요."
			name="loginId" />
		<br /> 비밀번호 :
		<input type="password" maxlength="10" placeholder="비밀번호를 입력해주세요."
			name="loginPw" />
		<br />
		<input type="submit" value="로그인" />
</form>
</div>
<%@ include file="../part/foot.jspf"%>