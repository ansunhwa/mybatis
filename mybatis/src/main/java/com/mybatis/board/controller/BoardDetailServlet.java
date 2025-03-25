package com.mybatis.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.mybatis.board.service.BoardService;
import com.mybatis.board.service.BoardServiceImpl;
import com.mybatis.board.vo.Board;
import com.mybatis.board.vo.Reply;

public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BoardService bService = new BoardServiceImpl();   //자식을 부모타입으로 형변환 가능

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));   //bno에 해당하는거 조회수 증가
		
		// 조회수 증가 시키기 count증가시키고 한 행을 보여줌
		int result = bService.increaseCount(boardNo);
				
		//조회수 증가가 잘되었으면 검색하여 boardDetailView.jsp로 가기
		if(result >0) {
			Board b = bService.selectBoard(boardNo);
			
			ArrayList<Reply> list = bService.selectReplyList(boardNo);   //원글 밑에 댓글창 가져오기
			
			request.setAttribute("b", b);
			request.setAttribute("reply", list);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailview.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "상세조회 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		ArrayList<Reply> reply = bService.selectReplyList(boardNo);
		
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(reply));
		
		/*
		 * Gson gson = new Gson(); String json = gson.toJson(gson);
		 * response.getWriter().print(json);
		 */
		
		//response.getWriter().print(new Gson().toJson(reply));
					
	}

}
