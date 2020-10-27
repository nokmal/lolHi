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
<a onclick="if (confirm ('삭제하시겠습니까?') == false) return false;"
	href="doDelete?id=${article.id}">삭제</a>
<a href="modify?id=${article.id}">수정</a>
<%@ include file="../part/foot.jspf"%>
