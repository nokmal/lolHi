<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 댓글 수정" />
<%@ include file="../part/head.jspf"%>
<div class="reply-modify con-min-width">
	<div class="con">
		<form class="reply-modify-box" action="doModify" method="POST">
			<input type="hidden" name="id" value="${reply.id}" />
			<input type=hidden name="redirectUrl" value="${param.redirectUrl}" />
			<div>
				<div>
					<span>번호 : </span>
				</div>
				<div>
					<span>${reply.id}</span>
				</div>
			</div>
			<div>
				<div>
					<span>작성날짜 : </span>
				</div>
				<div>
					<span>${reply.regDate}</span>
				</div>
			</div>
			<div>
				<div>
					<span>수정날짜 : </span>
				</div>
				<div>
					<span>${reply.updateDate}</span>
				</div>
			</div>
			<div>
				<span>
					<input type="text" maxlength="30" placeholder="내용을 입력해주세요."
						name="body" value="${reply.body}" />
				</span>
			</div>
			<div>
				<input type="submit" value="수정" />
			</div>
		</form>
	</div>
</div>

<div class="sub-menu-bar con-min-width">
	<div class="con">
		<a href="${param.redirectUrl}">돌아가기</a>
	</div>
</div>

<%@ include file="../part/foot.jspf"%>