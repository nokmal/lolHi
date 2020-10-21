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
	public String join() {
		return "usr/member/join"; 
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(@RequestParam Map<String, Object> param) {
		String loginId = Util.getAsStr(param.get("loginId"), "");
		if (loginId.length() == 0) {
			return String.format("<script> alert('아이디를 입력해주세요.'); history.back;</script>", loginId);
		}
		
		boolean isJoinAvaiableLoginId = memberService.isJoinAvaiableLoginId(loginId);
		
		if ( isJoinAvaiableLoginId == false ) {
			return String.format("<script> alert('%s(은)는 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId);
		}

		int id = memberService.doJoinMember(param);

		return String.format("<script> alert('%d번 회원이 가입하였습니다.'); location.replace('/usr/article/list'); </script>",
				id);		
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin(Model model, String loginId) {
		
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, HttpSession session) {
		if (loginId.length() == 0) {
			return String.format("<script> alert('로그인 아이디를 입력해주세요.'); history.back; </script>");	
		}
		
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return String.format("<script> alert('%s은(는) 존재하지 않는 아이디 입니다.'); history.back(); </script>", loginId);
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return String.format("<script> alert('비밀번호를 정확히 입력해주세요.'); history.back(); </script>");
		}

		session.setAttribute("loginedMemberId", member.getId());
		return String.format("<script> alert('%s님이 접속하였습니다.'); location.replace('/usr/article/list'); </script>",
				loginId);	
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
		session.removeAttribute("loginedMemberId");
		
		return String.format("<script> alert('로그아웃 되었습니다.'); location.replace('/usr/article/list');</script>");
	}
	
}
