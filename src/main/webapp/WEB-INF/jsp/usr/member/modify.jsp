<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원정보 수정" />
<%@ include file="../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<script>
	function MemberModifyForm__submit(form) {
		if (isNowLoading()) {
			alert('처리중입니다.');
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();

		if (form.loginPw.value.length > 0) {
			if (form.loginPw.value.length == 0) {
				form.loginPw.focus();
				alert('로그인 비밀번호를 입력해주세요.');
				return;
			}
			if (form.loginPw.value.length < 5) {
				form.loginPw.focus();
				alert('로그인 비밀번호를 5자 이상 입력해주세요.');
				return;
			}
			if (form.loginPwConfirm.value.length == 0) {
				form.loginPwConfirm.focus();
				alert('로그인 비밀번호 확인을 입력해주세요.');
				return;
			}
			if (form.loginPw.value != form.loginPwConfirm.value) {
				form.loginPwConfirm.focus();
				alert('로그인 비밀번호 확인이 일치하지 않습니다.');
				return;
			}
		}
		form.loginPw.value = '';
		form.loginPwConfirm.value = '';
		form.submit();
		startLoading();
	}
</script>
<div class="member-modify con-min-width">
	<div class="con">
		<form class="member-modify-box" action="doModify" method="POST">
			<input type="hidden" name="checkLoginPwAuthCode"
				value="${param.checkLoginPwAuthCode}" />
			<div>
				<div>
					<span> 번호 </span>
				</div>
				<div>
					<span>${loginedMember.id}</span>
				</div>
			</div>
			<div>
				<div>
					<span> 가입날짜 </span>
				</div>
				<div>
					<span>${loginedMember.regDate}</span>
				</div>
			</div>
			<div>
				<div>
					<span> 아이디 </span>
				</div>
				<div>
					<span>${loginedMember.loginId}</span>
				</div>
			</div>
			<div>
				<div>
					<span>이름</span>
				</div>
				<div>
					<input type="text" maxlength="15" placeholder="이름을 입력해주세요."
						name="name" value="${loginedMember.name}" />
				</div>
			</div>
			<div>
				<div>
					<span>비밀번호 변경</span>
				</div>
				<div>
					<input type="password" maxlength="15" placeholder="새 비밀번호를 입력해주세요."
						name="loginPw" value="${loginPw}" />
				</div>
			</div>
			<div>
				<div>
					<span>비밀번호 변경 확인</span>
				</div>
				<div>
					<input type="password" placeholder="비밀번호 확인을 입력해주세요."
						name="loginPwConfirm" maxlength="15" />
				</div>
			</div>
			<div>
				<div>
					<input type="submit" value="수정" />
				</div>
				<div>
					<input type="button" value="목록으로"
						onclick="location.href='/usr/article-free/list'" />
				</div>
			</div>
		</form>
	</div>
</div>

<%@ include file="../part/foot.jspf"%>