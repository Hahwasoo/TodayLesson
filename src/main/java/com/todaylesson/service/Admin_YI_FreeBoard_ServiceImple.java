package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.NoticeDTO;
import com.todaylesson.DTO.SQLjoin_Member_FreeBoardDTO;
import com.todaylesson.Mapper.Admin_YI_FreeBoard_Mapper;

@Service(value="admin_YI_FreeBoard_Service")
public class Admin_YI_FreeBoard_ServiceImple implements Admin_YI_FreeBoard_Service {
	
	@Inject
	private Admin_YI_FreeBoard_Mapper mapper;

	//페이징 카운트
	@Override
	public int totalCount(String search, String searchtxt) {
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		return mapper.getCount(hm);
	}
	
	//게시물 전체보기
	@Override
	public List<SQLjoin_Member_FreeBoardDTO> list(String search, String searchtxt, int startRow, int endRow) {
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		hm.put("startrow", startRow);
		hm.put("endrow", endRow);
		return mapper.list(hm);
	}

	@Override
	public List<NoticeDTO> notice() {
		// TODO Auto-generated method stub
		return mapper.notice();
	}

	@Override
	public int freeboard_replycount(int hidden_freeboard_no) {
		// TODO Auto-generated method stub
		return mapper.freeboard_replycount(hidden_freeboard_no);
	}


}
