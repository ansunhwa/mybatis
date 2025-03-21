package com.mybatis.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.member.vo.Member;

public class MemberDao {

	public Member loginMember(SqlSession sqlSession, Member m) {
		Member loginUser = sqlSession.selectOne("memberMapper.loginMember", m);  // 하나만 가지고 올땐 one
		return loginUser;
		//return sqlSession.selectOne("memberMapper.loginMember", m);
	}

	
	public int checkId(SqlSession sqlSession, String userId) {		  //아이디 중복체크/가지고올때 One
		return sqlSession.selectOne("memberMapper.checkId", userId);
	}


	public int insertMember(SqlSession sqlSession, Member m) {    //회원가입/ insert
		return sqlSession.insert("memberMapper.insertMember", m);
	}

}
