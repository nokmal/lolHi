package com.sbs.example.lolHi.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dao.ArticleDao;
import com.sbs.example.lolHi.dto.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public List<Article> getArticles(Map<String, Object> param) {
		int page = Util.getAsInt(param.get("page"), 1);
		
		int itemsCountInAPage = Util.getAsInt(param.get("itemsCountInAPage"), 10);
		
		if (itemsCountInAPage > 100) {
			itemsCountInAPage = 100;
		} else if (itemsCountInAPage < 1) {
			itemsCountInAPage = 1;
		}
		
		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;
		
		param.put("limitFrom", limitFrom);
		param.put("limitTake", limitTake);
		
		return articleDao.getArticles(param);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void deleteArticleById(int id) {
		articleDao.deleteArticleById(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);		
	}

	public int doWriteArticle(@RequestParam Map<String, Object> param) {
		articleDao.writeArticle(param);
		
//		util로 형변환
//		BigInteger bigIntId = (BigInteger)param.get("id");
//		int id = bigIntId.intValue();
		int id = Util.getAsInt(param.get("id"));
		
		return id;
	}

}
