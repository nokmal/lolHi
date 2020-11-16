<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="${board.name} 게시물 작성" />
<%@ include file="../part/head.jspf"%>
<hr />
<div>
	<form action="doWrite" method="post">
		제목 :
		<input type="text" maxlength="30" placeholder="제목을 입력해주세요."
			name="title" />
		<br /> 내용 :
		<input type="text" maxlength="300" placeholder="내용을 입력해주세요."
			name="body" />
		<br />
		<input type="submit" value="등록" />
	</form>
</div>
<%@ include file="../part/foot.jspf"%>