<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원정보 수정" />
<%@ include file="../part/head.jspf"%>
<hr />
<div>
	<form action="doModify" method="post">
		<div>번호 : ${loginedMember.id}</div>
		<div>가입날짜 : ${loginedMember.regDate}</div>
		<div>로그인 아이디 : ${loginedMember.loginId}</div>
		이름 : <input type="text" maxlength="30" placeholder="이름을 입력해주세요."
			value="${loginedMember.name}" name="name" /><br />
		<input type="submit" value="수정" />
	</form>
	<div>
	<button>  <a onclick="history.back();">뒤로가기</a> </button>
</div>
</div>
<%@ include file="../part/foot.jspf"%>