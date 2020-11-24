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
import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.ResultData;
import com.sbs.example.lolHi.service.ArticleService;
import com.sbs.example.lolHi.service.MemberService;
import com.sbs.example.lolHi.service.Util;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	public String doJoin(@RequestParam Map<String, Object> param, Model model) {
		String loginId = Util.getAsStr(param.get("loginId"), "");
		String name = Util.getAsStr(param.get("name"), "");
		String email = Util.getAsStr(param.get("email"), "");

		if (loginId.length() == 0) {
			model.addAttribute("msg", String.format("로그인 아이디를 입력해주세요."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		boolean isJoinAvaiableLoginId = memberService.isJoinAvaiableLoginId(loginId);

		if (isJoinAvaiableLoginId == false) {
			model.addAttribute("msg", String.format("%s는 이미 사용 중인 아이디입니다.", loginId));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		boolean isJoinAvailableNameAndEmail = memberService.isJoinAvailableNameAndEmail(name, email);

		if (isJoinAvailableNameAndEmail == false) {
			model.addAttribute("msg", String.format("이미 가입된 회원의 정보입니다."));
			model.addAttribute("replaceUri", "/usr/member/findLoginId");
			return "common/redirect";
		}

		int id = memberService.doJoinMember(param);

		model.addAttribute("msg", String.format("환영합니다!"));
		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}

	@RequestMapping("/usr/member/checkLoginPw")
	public String showCheckLoginPw() {
		return "usr/member/checkLoginPw";
	}

	@RequestMapping("/usr/member/doCheckLoginPw")
	public String doCheckLoginPw(Model model, HttpServletRequest req, String loginPw, String redirectUrl) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");

		if (loginedMember.getLoginPw().equals(loginPw) == false) {
			model.addAttribute("historyBack", true);
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "common/redirect";
		}

		// String authCode =
		// memberService.genCheckPasswordAuthCode(loginedMember.getId());

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/usr/home/main";
		}

		// redirectUri = Util.getNewUri(redirectUri, "checkPasswordAuthCode", authCode);

		model.addAttribute("replaceUri", redirectUrl);

		return "common/redirect";

	}

	@RequestMapping("/usr/member/findLoginPw")
	public String showFindLoginPw() {
		return "usr/member/findLoginPw";
	}

	@RequestMapping("/usr/member/doFindLoginPw")
	public String doFindLoginPw(Model model, String loginId, String email) {
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			model.addAttribute("msg", String.format("해당회원은 존재하지 않습니다."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getEmail().equals(email) == false) {
			model.addAttribute("msg", String.format("해당회원은 존재하지 않습니다."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		ResultData setTempPasswordAndNotifyRsData = memberService.setTempPasswordAndNotify(member);

		model.addAttribute("msg", String.format(setTempPasswordAndNotifyRsData.getMsg()));
		model.addAttribute("historyBack", true);
		return "common/redirect";
	}

	@RequestMapping("/usr/member/login")
	public String showLogin(Model model, String loginId) {
		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	public String doLogin(String loginId, String loginPw, HttpSession session, Model model) {
		if (loginId.length() == 0) {
			model.addAttribute("msg", String.format("로그인 아이디를 입력해주세요."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			model.addAttribute("msg", String.format("%s는 존재하지 않는 아이디입니다.", loginId));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			model.addAttribute("msg", String.format("비밀번호를 정확하게 입력해주세요."));
			model.addAttribute("historyBack", true);
			return "common/redirect";
		}

		session.setAttribute("loginedMemberId", member.getId());

		model.addAttribute("msg", String.format("%s님 환영합니다.", member.getName()));
		model.addAttribute("replaceUri", String.format("/usr/article-free/list"));
		return "common/redirect";
	}

	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession session, Model model) {
		session.removeAttribute("loginedMemberId");

		model.addAttribute("msg", String.format("로그아웃 되었습니다."));
		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}

	@RequestMapping("/usr/member/modify")
	public String showModify() {
		return "usr/member/modify";
	}

	@RequestMapping("/usr/member/doModify")
	public String doModify(HttpServletRequest req, @RequestParam Map<String, Object> param, Model model) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);

		// 해킹방지
		param.remove("loginId");
		param.remove("loginPw");

		memberService.modifyMember(param);

		model.addAttribute("msg", "회원정보가 수정되었습니다.");
		model.addAttribute("replaceUri", "/usr/article-free/list");
		return "common/redirect";
	}
}
