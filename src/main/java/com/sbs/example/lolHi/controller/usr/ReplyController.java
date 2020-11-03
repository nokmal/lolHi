package com.sbs.example.lolHi.controller.usr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.service.ArticleService;
import com.sbs.example.lolHi.service.Util;
import com.sbs.example.lolHi.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/reply/doWrite")
	public String doWrite(HttpServletRequest req, @RequestParam Map<String, Object> param, Model model) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		String relTypeCode = (String)param.get("relTypeCode");
		int relId = Util.getAsInt(param.get("relId"));

		param.put("memberId", loginedMemberId);
		int id = replyService.write(param);

		model.addAttribute("msg", String.format("댓글이 작성되었습니다."));
		model.addAttribute("replaceUri", String.format("/usr/%s/detail?id=%d", relTypeCode, relId));
		return "common/redirect";
	}
}
