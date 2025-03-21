package com.mybatis.member.service;

import com.mybatis.member.vo.Member;

public interface MemberService {
//구현X 메소드 이름정의
	Member loginMember(Member m);  //자바빈에 담아서 가져올거임/서블릿에 만들어놓은 
	
	//잘 있으면 1 없으면 0
	int checkId(String userId);
	
	int insertMember(Member m);
	
}
