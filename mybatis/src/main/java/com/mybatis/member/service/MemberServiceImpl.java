package com.mybatis.member.service;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.common.template.template;
import com.mybatis.member.dao.MemberDao;
import com.mybatis.member.vo.Member;


public class MemberServiceImpl implements MemberService {
	//SqlSession sqlSession = template.getSqlSession();  //import  /세션을 계속 연결할꺼기 때문에 밖에 놔도 된다(인스턴스변수)
	private MemberDao mDao = new MemberDao();
	
	@Override
	public Member loginMember(Member m) {
		SqlSession sqlSession = template.getSqlSession();
		Member loginUser = mDao.loginMember(sqlSession, m);
		sqlSession.close();
		return loginUser;
	}

	@Override
	public int checkId(String userId) {
		SqlSession sqlSession = template.getSqlSession();  //DB와 연결하는
		int checkid = mDao.checkId(sqlSession, userId);
		sqlSession.close();
		return checkid;
	}

	@Override
	public int insertMember(Member m) {
		SqlSession sqlSession = template.getSqlSession();
		int result = mDao.insertMember(sqlSession, m);
		
		if(result >0) {  //수동으로commit
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

}