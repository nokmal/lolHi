<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="${board.name} 게시물 리스트" />
<%@ include file="../part/head.jspf"%>



<div class="articles-info-bar con-min-width">
	<div class="con">
		<span class="articles-info-bar__total-count">
			<span>총 게시물 수 : </span>
			<span>${totalCount}</span>
		</span>
	</div>
</div>

<div class="con-min-width">
	<div class="con">
		<form class="form-box-type-1">
			<div>
				<div>
					<span>검색어</span>
				</div>
				<div>
					<input type="text" name="searchKeyword" placeholder="검색어"
						value="${param.searchKeyword}">
				</div>
			</div>
			<div>
				<div>
					<span>검색</span>
				</div>
				<div>
					<input type="submit" value="검색">
				</div>
			</div>
		</form>
	</div>
</div>

<div class="article-list-box con-min-width">
	<div class="con">
		<div class="article-list-box__head">
			<div class="article-list-box__row">
				<div>
					<span>번호</span>
				</div>
				<div>
					<span>작성날짜</span>
				</div>
				<div>
					<span>갱신날짜</span>
				</div>
				<div>
					<span>작성자</span>
				</div>
				<div>
					<span>제목</span>
				</div>
				<div>
					<span>작업</span>
				</div>
			</div>
		</div>
	</div>

	<div class="article-list-box__body">
		<c:forEach items="${articles}" var="article">
			<c:set var="detailUrl"
				value="/usr/article-${board.code}/detail?id=${article.id}&listUrl=${encodedCurrentUri}" />
			<div class="article-list-box__row">
				<div>
					<a href="${detailUrl}">${article.id}</a>
				</div>
				<div>
					<span>${article.regDate}</span>
				</div>
				<div>
					<span>${article.updateDate}</span>
				</div>
				<div>
					<span> ${article.extra.writer} </span>
				</div>
				<div>
					<a href="${detailUrl}">${article.forPrintTitle}</a>
				</div>
				<div>
					<c:if test="${article.extra.actorCanDelete}">
						<a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
							href="doDelete?id=${article.id}">삭제</a>
					</c:if>
					<c:if test="${article.extra.actorCanModify}">
						<a href="modify?id=${article.id}">수정</a>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<div class="sub-menu-bar con-min-width">
	<div class="con">
		<a href="write">글쓰기</a>

		<div class="article-page-box con-min-width">
			<!-- 첫 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
			<c:set var="goFirstBtnNeedToShow"
				value="${page > pageMenuArmSize + 1}" />

			<!-- 마지막 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
			<c:set var="goLastBtnNeedToShow" value="true" />

			<c:if test="${0 == totalPage}">
				<c:set var="goFirstBtnNeedToShow" value="false" />
			</c:if>

			<!-- 첫 페이지로 이동버튼이 노출될 필요가 있다면 노출 -->
			<c:if test="${goFirstBtnNeedToShow}">
				<a href="?page=1&searchKeyword=${param.searchKeyword}">◀</a>
			</c:if>

			<c:forEach var="i" begin="${pageMenuStart}" end="${pageMenuEnd}">
				<c:set var="className"
					value="${i == page ? 'article-page-box__page-btn--selected' : ''}" />
				<a class="${className}"
					href="?page=${i}&searchKeyword=${param.searchKeyword}">${i}</a>

				<!-- 방금 노출된 페이지 번호가 마지막 페이지의 번호였다면, 마지막으로 이동하는 버튼이 노출될 필요가 없다고 설정 -->
				<c:if test="${i == totalPage}">
					<c:set var="goLastBtnNeedToShow" value="false" />
				</c:if>
			</c:forEach>

			<c:if test="${0 == totalPage}">
				<c:set var="goLastBtnNeedToShow" value="false" />
			</c:if>

			<!-- 마지막 페이지로 이동버튼이 노출될 필요가 있다면 노출 -->
			<c:if test="${goLastBtnNeedToShow}">
				<a href="?page=${totalPage}&searchKeyword=${param.searchKeyword}">▶</a>
			</c:if>
		</div>
	</div>
</div>

<%@ include file="../part/foot.jspf"%>