<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원가입" />
<%@ include file="../part/head.jspf"%>
<hr />
<script>
	var joinFormSubmitDone = false;
	function joinFormSubmit(form) {
		if (joinFormSubmitDone) {
			alert('처리중입니다.');
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
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
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPw.value != form.loginPwConfirm.value) {
			alert('로그인 비번 확인이 일치하지 않습니다.');
			form.loginPwConfirm.focus();
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.submit();
		joinFormSubmitDone = true;
	}
</script>
<form action="doJoin" method="POST"
	onsubmit="joinFormSubmit(this); return false;">
	<div>
		이름 :
		<input type="text" maxlength="30" placeholder="이름을 입력해주세요."
			name="name" />
		<br /> 아이디 :
		<input type="text" maxlength="10" placeholder="아이디를 입력해주세요."
			name="loginId" />
		<br /> 비밀번호 :
		<input type="password" maxlength="10" placeholder="비밀번호를 입력해주세요."
			name="loginPw" />
		<br /> 비밀번호 확인 :
		<input type="password" maxlength="10" placeholder="비밀번호를 재입력해주세요."
			name="loginPwConfirm" />
		<br />
		<div>
			이메일 :
			<input type="email" maxlength="50" placeholder="이메일을 입력해주세요."
				name="email" />
		</div>
		<input type="submit" value="가입" />
</form>
</div>
<%@ include file="../part/foot.jspf"%>