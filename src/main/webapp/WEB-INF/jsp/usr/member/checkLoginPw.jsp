<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="패스워드 입력" />
<%@ include file="../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
	var checkLoginPwFormSubmitDone = false;
	function checkLoginPwFormSubmit(form) {
		if (checkLoginPwFormSubmitDone) {
			alert('처리중입니다.');
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('로그인 비번을 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		form.loginPw.value = sha256(form.loginPw.value);
		form.submit();
		checkLoginPwFormSubmitDone = true;
	}
</script>
<div class="checkLoginPw con-min-width">
	<div class="con">
		<form class="checkLoginPw-box" action="doCheckLoginPw" method="POST"
			onsubmit="checkLoginPwFormSubmit(this); return false;">
			<div>
				<input type="hidden" name="redirectUrl" value="/usr/member/modify">
				<div>
					로그인 비번 :
					<input type="password" maxlength="30" placeholder="로그인 비번을 입력해주세요."
						name="loginPw" />
				</div>
				<div class="bg-red">
					<input type="submit" value="비밀번호 확인" />
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="../part/foot.jspf"%>
