<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 수정" />
<%@ include file="../part/head.jspf"%>
<hr />
<div>
	<form action="doModify" method="post">
		<input type="hidden" name="id" value="${article.id}" />
		<div>번호 : ${article.id}</div>
		<div>작성날짜 : ${article.regDate}</div>
		<div>수정날짜 : ${article.updateDate}</div>
		제목 : <input type="text" maxlength="30" value="${article.title}"
			name="title" /><br /> 내용 : <input type="text" maxlength="300"
			value="${article.body}" name="body" /><br /> <input type="submit"
			value="수정" />
	</form>
</div>
<%@ include file="../part/foot.jspf"%>