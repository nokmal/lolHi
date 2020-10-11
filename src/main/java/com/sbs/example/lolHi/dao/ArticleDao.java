package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Article;

@Mapper
public interface ArticleDao {
	List<Article> getArticles();

	Article getArticleById(@Param("id") int id);

	void deleteArticleById(@Param("id") int id);
	
	void modifyArticle(@Param("id") int id, @Param("title")String title, @Param("body") String body);

	void writeArticle(@RequestParam Map<String, Object> param);

	List<Article> wirteArticle();
	
}