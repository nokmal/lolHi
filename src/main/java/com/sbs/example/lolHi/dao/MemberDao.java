package com.sbs.example.lolHi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.example.lolHi.dto.Article;
import com.sbs.example.lolHi.dto.Member;

@Mapper
public interface MemberDao {
	void joinMember(@RequestParam Map<String, Object> param);

	void loginMember(String loginId, String loginPw);

	Member getMemberByLoginId(String loginId);

	List<Member> getMember(String loginId, String loginPw);

	Member getMemberById(@Param("id") int id);

	void modifyMember(Map<String, Object> param);

	Member getMemberByNameAndEmail(@Param("name") String name, @Param("email") String email);

	void modify(Map<String, Object> modifyParam);
}