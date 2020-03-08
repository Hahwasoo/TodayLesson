package com.todaylesson.service;

import java.util.List;
import java.util.Map;

import com.todaylesson.DTO.FreeBoardDTO;
import com.todaylesson.DTO.Freeboard_PageMaker;
import com.todaylesson.DTO.NoticeDTO;
import com.todaylesson.DTO.SQLjoin_Member_FreeBoardDTO;

public interface User_YI_FreeBoard_Service {
	//����¡ ī��Ʈ
	public int totalCount(String search, String searchtxt);
	//�Խù� ��ü����
	public List<SQLjoin_Member_FreeBoardDTO> list(String search, String searchtxt, int startRow, int endRow);
	//�Խù� �󼼺���
	public SQLjoin_Member_FreeBoardDTO freeboard_detail(int freeboard_no);
	//��ȸ�� ����
	public void freeboard_readnoUp(int freeboard_no);
	public List<NoticeDTO> notice();
	public void notice_readnoUp(int notice_no);
	public NoticeDTO notice_detail(int notice_no);

	


	
	
}
