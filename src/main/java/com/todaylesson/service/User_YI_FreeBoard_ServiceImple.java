package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.BoardReplyDTO;
import com.todaylesson.DTO.NoticeDTO;
import com.todaylesson.DTO.SQLjoin_Member_FreeBoardDTO;
import com.todaylesson.Mapper.User_YI_FreeBoard_Mapper;

@Service(value="user_YI_FreeBoard_Service")
public class User_YI_FreeBoard_ServiceImple implements User_YI_FreeBoard_Service {

	//@Resource(name="user_YI_FreeBoard_Mapper")
	@Inject
	private User_YI_FreeBoard_Mapper mapper;

	//����¡ ī��Ʈ
	@Override
	public int totalCount(String search, String searchtxt) {
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		return mapper.getCount(hm);
	}
	
	//�Խù� ��ü����
	@Override
	public List<SQLjoin_Member_FreeBoardDTO> list(String search, String searchtxt, int startRow, int endRow) {
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		hm.put("startrow", startRow);
		hm.put("endrow", endRow);
		return mapper.list(hm);
	}

	//�Խù� �󼼺���
	@Override
	public SQLjoin_Member_FreeBoardDTO freeboard_detail(int freeboard_no) {
		// TODO Auto-generated method stub
		return mapper.freeboard_detail(freeboard_no);
	}

	//��ȸ�� ����
	@Override
	public void freeboard_readnoUp(int freeboard_no) {
		mapper.freeboard_readnoUp(freeboard_no);
		
	}

	@Override
	public List<NoticeDTO> notice() {
		// TODO Auto-generated method stub
		return mapper.notice();
	}

	@Override
	public void notice_readnoUp(int notice_no) {
		// TODO Auto-generated method stub
		mapper.notice_readnoUp(notice_no);
	}

	@Override
	public NoticeDTO notice_detail(int notice_no) {
		// TODO Auto-generated method stub
		return mapper.notice_detail(notice_no);
	}

	@Override
	public SQLjoin_Member_FreeBoardDTO rep_detail(int freeboard_no) {
		// TODO Auto-generated method stub
		return mapper.rep_detail(freeboard_no);
	}

	@Override
	public int insert_reply(SQLjoin_Member_FreeBoardDTO dto) {
		// TODO Auto-generated method stub 
		return mapper.insert_reply(dto);
	}

	@Override
	public List<SQLjoin_Member_FreeBoardDTO> boardreply_list(int freeboard_no) {
		// TODO Auto-generated method stub
		return mapper.boardreply_list(freeboard_no);
	}

	@Override
	public String getNick_reply(String member_id) {
		// TODO Auto-generated method stub
		return mapper.getNick_reply(member_id);
	}

	@Override
	public int freeboard_replycount(int hidden_freeboard_no) {
		// TODO Auto-generated method stub
		return mapper.freeboard_replycount(hidden_freeboard_no);
	}

/*	@Override
	public int boardreply_noGet(int freeboard_no) {
		// TODO Auto-generated method stub
		return mapper.boardreply_noGet(freeboard_no);
	}*/

/*	@Override
	public List<SQLjoin_Member_FreeBoardDTO> ajax_data(int boardreply_no) {
		// TODO Auto-generated method stub
		return mapper.ajax_list(boardreply_no);
	}*/

	@Override
	public int delete_reply(int boardreply_no) {
		// TODO Auto-generated method stub
		return mapper.delete_reply(boardreply_no);
	}

	@Override
	public int freeboard_delete(int freeboard_no) {
		// TODO Auto-generated method stub
		return mapper.freeboard_delete(freeboard_no);
	}

	@Override
	public int freeboard_insert(SQLjoin_Member_FreeBoardDTO dto) {
		// TODO Auto-generated method stub
		return mapper.freeboard_insert(dto);
	}




}
