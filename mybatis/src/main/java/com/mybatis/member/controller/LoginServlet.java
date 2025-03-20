package com.mybatis.member.controller;

import java.io.IOException;

import com.mybatis.member.service.MemberServiceImpl;
import com.mybatis.member.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member m = new Member();        
		//내가 만든 Member import
		String id = request.getParameter("userId");   //입력된 아이디 값을 빈에 받아오는 것
		m.setUserId(id);
		m.setUserPwd(request.getParameter("userPwd")); //
		
		MemberServiceImpl mi = new MemberServiceImpl();   //import
		Member loginUser = mi.loginMember(m);
		
		if(loginUser != null) {  //로그인이 잘 되었으면
			request.getSession().setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath());
		} else { //로그인이 잘 안되었으면
			request.setAttribute("errorMsg","@@ 로그인 실패 @@");  //key호출
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
		
	}

} 
