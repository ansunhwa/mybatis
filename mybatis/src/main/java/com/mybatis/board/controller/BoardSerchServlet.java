package com.mybatis.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mybatis.board.service.BoardService;
import com.mybatis.board.service.BoardServiceImpl;
import com.mybatis.board.vo.Board;
import com.mybatis.common.template.Pagination;
import com.mybatis.common.vo.PageInfo;

public class BoardSerchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyField = request.getParameter("keyField");
		String keyWord = request.getParameter("keyWord");
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		
		// keyField와 keyWord 2개를 모두 담으려면 vo 에 bean을 만들어 넣어서 넘겨주던지
		// 2개를 모두 담을 수 있는 컬렉션에 담던지 해야 함
		// HashMap을 이용하여 저장 (키:값)
		HashMap<String, String> map = new HashMap<>();
		map.put("keyField", keyField);   // 검색 3가지 중 하나
		map.put("keyWord", keyWord);     // 사용자가 입력한 값
		
		BoardService bService = new BoardServiceImpl();    //import
		
		// 페이징 처리를 위해 검색조건에 맞는 모든 게시글의 총 갯수
		int searchCount = bService.selectSearchCount(map);
		PageInfo pi = Pagination.getPageInfo(searchCount, nowPage, 5, 2);
		ArrayList<Board> list = bService.selectSearchList(map, pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		//키워드랑키필드도 넘겨줘야함
		
		request.setAttribute("keyField", keyField);
		request.setAttribute("keyWord", keyWord );
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);
	}

}
