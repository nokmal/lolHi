package com.sbs.example.lolHi.controller.usr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Member;
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
		if (loginId.length() == 0) {
			model.addAttribute("msg", String.format("로그인 아이디를 입력해주세요."));
			model.addAttribute("historyBack", true);		
			return "common/redirect"; 
		}
		
		boolean isJoinAvaiableLoginId = memberService.isJoinAvaiableLoginId(loginId);
		
		if ( isJoinAvaiableLoginId == false ) {
			model.addAttribute("msg", String.format("%s는 이미 사용 중인 아이디입니다.", loginId));
			model.addAttribute("historyBack", true);		
			return "common/redirect"; 
		}

		int id = memberService.doJoinMember(param);

		model.addAttribute("msg", String.format("환영합니다!"));
		model.addAttribute("replaceUri", String.format("/usr/article/list"));
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
		model.addAttribute("replaceUri", String.format("/usr/article/list"));
		return "common/redirect";
	}
	
	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession session, Model model) {
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		if (loginedMemberId == 0) {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			model.addAttribute("replaceUri", "/usr/member/login");
			return "common/redirect";
		}
		session.removeAttribute("loginedMemberId");
		
		model.addAttribute("msg", String.format("로그아웃 되었습니다."));
		model.addAttribute("replaceUri", String.format("/usr/article/list"));
		return "common/redirect";
	}
	
}
