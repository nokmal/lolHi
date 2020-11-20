package com.sbs.example.lolHi.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dao.MemberDao;
import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Member;

@Service
public class MemberService {
	@Value("${custom.siteName}")
	private String siteName;

	@Value("${custom.siteMainUri}")
	private String siteMainUri;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MailService mailService;

	public List<Member> getMember(String loginId, String loginPw) {
		return memberDao.getMember(loginId, loginPw);
	}

	public int doJoinMember(Map<String, Object> param) {
		memberDao.joinMember(param);

		int id = Util.getAsInt(param.get("id"));
		
		sendJoinCompleteMail((String) param.get("email"));

		return id;
	}
	
	private void sendJoinCompleteMail(String email) {
		String mailTitle = String.format("[%s] 가입이 완료되었습니다.", siteName);

		StringBuilder mailBodySb = new StringBuilder();
		mailBodySb.append("<h1>가입이 완료되었습니다.</h1>");
		mailBodySb.append(String.format("<p><a href=\"%s\" target=\"_blank\">%s</a>로 이동</p>", siteMainUri, siteName));

		mailService.send(email, mailTitle, mailBodySb.toString());
	}

	public void doLoginMember(String loginId, String loginPw) {
		memberDao.loginMember(loginId, loginPw);

	}

	public boolean isJoinAvaiableLoginId(String loginId) {
		Member member = memberDao.getMemberByLoginId(loginId);

		if (member == null) {
			return true;
		}

		return false;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public void modifyMember(Map<String, Object> param) {
		memberDao.modifyMember(param);
	}

	public boolean isJoinAvailableNameAndEmail(String name, String email) {
		if (name == null || name.length() == 0) {
			return false;
		}
		if (email == null || email.length() == 0) {
			return false;
		}

		Member member = memberDao.getMemberByNameAndEmail(name, email);

		return member == null;
	}
}
