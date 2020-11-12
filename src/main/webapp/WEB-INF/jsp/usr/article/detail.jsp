<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 상세페이지" />
<%@ include file="../part/head.jspf"%>
<hr />
<div>&nbsp;&nbsp;&nbsp;번호:&nbsp;${article.id}</div>
<div>작성자:&nbsp;${article.extra.writer}</div>
<div>&nbsp;&nbsp;&nbsp;제목: &nbsp;${article.title}</div>
<div>&nbsp;&nbsp;&nbsp;내용: &nbsp;${article.body}</div>
<div>${age}</div>
<a href="${listUrl}">리스트</a>
<c:if test="${article.extra.actorCanDelete}">
	<a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
		href="doDelete?id=${article.id}">삭제</a>
</c:if>
<c:if test="${article.extra.actorCanModify}">
	<a href="modify?id=${article.id}">수정</a>
</c:if>
<h2>댓글 작성</h2>
<form action="/usr/reply/doWrite" method="POST">
	<input type="hidden" name="redirectUrl" value="${currentUri}" />
	<input type="hidden" name="relTypeCode" value="article" />
	<input type="hidden" name="relId" value="${param.id}" />
	<div>
		<textarea rows="10" name="body" placeholder="내용"></textarea>
	</div>
	<div>
		<input type="submit" value="작성" />
	</div>
</form>
<h2>댓글 리스트</h2>
<c:forEach items="${replies}" var="reply">
	<div>작성날짜: ${reply.regDate}</div>
	<div>작성날짜: ${reply.updateDate}</div>
	<div>작성자 : ${reply.extra.writer}</div>
	<div>내용: ${reply.body}</div>
	<c:if test="${loginedMemberId == reply.memberId}">
		<a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
			href="/usr/reply/doDelete?id=${reply.id}&redirectUrl=${encodedCurrentUri}">삭제</a>
	</c:if>
	<c:if test="${loginedMemberId == reply.memberId}">
		<a
			href="/usr/reply/modify?id=${reply.id}&redirectUrl=${encodedCurrentUri}">수정</a>
	</c:if>
</c:forEach>
<%@ include file="../part/foot.jspf"%>