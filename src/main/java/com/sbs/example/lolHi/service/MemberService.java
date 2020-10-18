package com.sbs.example.lolHi.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dao.MemberDao;
import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public List<Member> getMember(String loginId, String loginPw) {
		return memberDao.getMember(loginId, loginPw);
	}

	public int doJoinMember(Map<String, Object> param) {
		memberDao.joinMember(param);

		int id = Util.getAsInt(param.get("id"));

		return id;
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
}
