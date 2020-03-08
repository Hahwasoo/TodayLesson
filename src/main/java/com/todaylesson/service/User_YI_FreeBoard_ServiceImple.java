package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

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







	//�� �Խñ� �� Ȯ��
	/*@Override
	public int getBoardListCnt() throws Exception {
		// TODO Auto-generated method stub
		return mapper.getBoardListCnt();

	}

	@Override
	public Object getBoardList(Freeboard_PageMaker pageMaker) {
		// TODO Auto-generated method stub
		return mapper.getBoardList(pageMaker);
	}*/

}
