package com.mybatis.common.template;

import com.mybatis.common.vo.PageInfo;

public class Pagination {
	public static PageInfo getPageInfo(int totalRecord,int nowPage, int numPerPage, int pagePerBlock) {   //네개로 밑에 3가지 계산할꺼임
		int totalPage = (int)Math.ceil((double)totalRecord / numPerPage);    // 32 / 10 = 3.2(2개까지 ) 4page나와야 하기때문에 double하고 무조건 올림
		int startPage = (nowPage -1) / pagePerBlock * pagePerBlock + 1;  // 0/1 = 0 * 3(내가 하단에 보여주고 싶은 페이지 갯수) (int)1/3= 0 *3 /  1,2,3 => 다나옴 3p블럭 다음부터는 나머지가 생김 
		int endPage = startPage + pagePerBlock -1;   //start는 1부터 block(3)개씩 보여주세요 라고함 1+3 = 4 / 2p 시작 2+3 = 5 -1 해줘야 마지막숫자까지   
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
		PageInfo pi = new PageInfo(totalRecord, nowPage, numPerPage, pagePerBlock, totalPage, startPage, endPage);
		return pi;
	}
	
	
}
