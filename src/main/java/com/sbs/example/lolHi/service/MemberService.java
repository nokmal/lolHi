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

	public void doJoinMember(@RequestParam Map<String, Object> param) {
		memberDao.joinMember(param);
	}
}
