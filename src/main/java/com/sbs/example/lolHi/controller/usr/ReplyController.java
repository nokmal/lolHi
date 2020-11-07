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
import com.sbs.example.lolHi.dto.Reply;
import com.sbs.example.lolHi.service.ArticleService;
import com.sbs.example.lolHi.service.Util;
import com.sbs.example.lolHi.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/reply/doWrite")
	public String doWrite(HttpServletRequest req, @RequestParam Map<String, Object> param, Model model,
			String redirectUrl) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		String relTypeCode = (String) param.get("relTypeCode");
		int relId = Util.getAsInt(param.get("relId"));

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", relTypeCode, relId);
		}

		param.put("memberId", loginedMemberId);
		int id = replyService.write(param);

		model.addAttribute("msg", String.format("댓글이 작성되었습니다."));
		model.addAttribute("replaceUri", redirectUrl);
		return "common/redirect";
	}

	@RequestMapping("/usr/reply/doDelete")
	public String doDelete(HttpServletRequest req, Model model, int id, String redirectUrl) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Reply reply = replyService.getReply(id);

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", reply.getRelTypeCode(), reply.getRelId());
		}

		if (reply == null) {
			model.addAttribute("msg", "존재하지 않는 댓글입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if (loginedMemberId != reply.getMemberId()) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		replyService.deleteReplyById(id);

		model.addAttribute("msg", String.format("댓글이 삭제되었습니다."));
		model.addAttribute("replaceUri", redirectUrl);
		return "common/redirect";
	}

	@RequestMapping("/usr/reply/modify")
	public String showModify(HttpServletRequest req, Model model, int id, String redirectUrl) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Reply reply = replyService.getReply(id);

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = String.format("/usr/%s/detail?id=%d", reply.getRelTypeCode(), reply.getRelId());
		}

		if (reply == null) {
			model.addAttribute("msg", "존재하지 않는 댓글입니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if (loginedMemberId != reply.getMemberId()) {
			model.addAttribute("msg", "권한이 없습니다.");
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		model.addAttribute("reply", reply);

		return "usr/reply/modify";
	}

}
