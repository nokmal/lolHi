<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원정보 수정" />
<%@ include file="../part/head.jspf"%>
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
					<input type="text" maxlength="30" placeholder="이름을 입력해주세요."
						name="name" value="${loginedMember.name}" />
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