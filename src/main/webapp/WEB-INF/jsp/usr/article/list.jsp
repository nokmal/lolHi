<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 리스트" />
<%@ include file="../part/head.jspf"%>
<hr />
<style>
.selected {
	color: red;
}
</style>
<div>
	<div>총 게시물 수 : ${totalCount}</div>
	<div>
		<form>
			<div>
				<input type="text" name="searchKeyword" placeholder="검색어"
					value="${param.searchKeyword}">
			</div>
			<div>
				<input type="submit" value="검색">
			</div>
		</form>
	</div>
	<c:forEach items="${articles}" var="article">
		번호: <a href="${detailUrl}">${article.id}</a>
		<br />
		regDate: ${article.regDate}
		<br />
		updateDate: ${article.updateDate}
		<br />
		작성자: ${article.extra.writer}
		<br />
		제목: <a href="${detailUrl}">${article.title}</a>
		<br />
		내용: ${article.body}
		<br />
		작업 :
			<a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
			href="doDelete?id=${article.id}">삭제</a>
		<a href="modify?id=${article.id}">수정</a>
		<hr />
</div>
</c:forEach>
</div>
<h2>페이지</h2>
<div>
	<!-- 첫 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
	<c:set var="goFirstBtnNeedToShow" value="${page > pageMenuArmSize + 1}" />
	<!-- 마지막 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
	<c:set var="goLastBtnNeedToShow" value="true" />
	<!-- 첫 페이지로 이동버튼이 노출될 필요가 있다면 노출 -->
	<c:if test="${goFirstBtnNeedToShow}">
		<a href="?page=1&searchKeyword=${param.searchKeyword}">◀</a>
	</c:if>
	<c:forEach var="i" begin="${pageMenuStart}" end="${pageMenuEnd}">
		<c:set var="className" value="${i == page ? 'selected' : ''}" />
		<a class="${className}"
			href="?page=${i}&searchKeyword=${param.searchKeyword}">${i}</a>
		<!-- 방금 노출된 페이지 번호가 마지막 페이지의 번호였다면, 마지막으로 이동하는 버튼이 노출될 필요가 없다고 설정 -->
		<c:if test="${i == totalPage}">
			<c:set var="goLastBtnNeedToShow" value="false" />
		</c:if>
	</c:forEach>
	<!-- 마지막 페이지로 이동버튼이 노출될 필요가 있다면 노출 -->
	<c:if test="${goLastBtnNeedToShow}">
		<a href="?page=${totalPage}&searchKeyword=${param.searchKeyword}">▶</a>
	</c:if>
	<%@ include file="../part/foot.jspf"%>