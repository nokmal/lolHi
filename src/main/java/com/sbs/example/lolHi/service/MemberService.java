package com.sbs.example.lolHi.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dao.MemberDao;
import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Member;
import com.sbs.example.lolHi.dto.ResultData;

@Service
public class MemberService {
	@Value("${custom.siteName}")
	private String siteName;

	@Value("${custom.siteMainUri}")
	private String siteMainUri;

	@Value("${custom.siteLoginUri}")
	private String siteLoginUri;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MailService mailService;

	@Autowired
	private AttrService attrService;

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

	public ResultData setTempPasswordAndNotify(Member member) {
		Random r = new Random();
		String tempLoginPw = (10000 + r.nextInt(90000)) + "";

		String mailTitle = String.format("[%s] 임시 비밀번호가 발송되었습니다.", siteName);
		String mailBody = "";

		mailBody += String.format("로그인아이디 : %s<br>", member.getLoginId());
		mailBody += String.format("임시 비밀번호 : %s", tempLoginPw);
		mailBody += "<br>";
		mailBody += String.format("<a href=\"%s\" target=\"_blank\">로그인 하러가기</a>", siteLoginUri);

		ResultData sendResultData = mailService.send(member.getEmail(), mailTitle, mailBody);

		if (sendResultData.isFail()) {
			return new ResultData("F-1", "메일발송에 실패했습니다.");
		}

		Map<String, Object> modifyParam = new HashMap<>();
		modifyParam.put("loginPw", tempLoginPw);
		modifyParam.put("loginPw", Util.sha256(tempLoginPw));
		modifyParam.put("id", member.getId());
		memberDao.modify(modifyParam);

		return new ResultData("S-1", "임시 패스워드를 메일로 발송하였습니다.");
	}

	public String genCheckLoginPwAuthCode(int actorId) {
		String authCode = UUID.randomUUID().toString();
		attrService.setValue("member__" + actorId + "__extra__modifyPrivateAuthCode", authCode,
				Util.getDateStrLater(60 * 60));

		return authCode;
	}

	public ResultData checkValidCheckLoginPwAuthCode(int actorId, String checkLoginPwAuthCode) {
		if (attrService.getValue("member__" + actorId + "__extra__modifyPrivateAuthCode")
				.equals(checkLoginPwAuthCode)) {
			return new ResultData("S-1", "유효한 키 입니다.");
		}

		return new ResultData("F-1", "유효하지 않은 키 입니다.");
	}
}
