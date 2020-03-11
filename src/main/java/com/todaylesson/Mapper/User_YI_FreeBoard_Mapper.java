package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.BoardReplyDTO;
import com.todaylesson.DTO.NoticeDTO;
import com.todaylesson.DTO.SQLjoin_Member_FreeBoardDTO;

@Mapper
public interface User_YI_FreeBoard_Mapper {

//����¡ ī��Ʈ
public int getCount(HashMap<String, Object> hm);	

//�Խù� ��ü����
public List<SQLjoin_Member_FreeBoardDTO> list(HashMap<String, Object> hm);

//�Խù� �󼼺���
public SQLjoin_Member_FreeBoardDTO freeboard_detail(int freeboard_no);

//�Խù� ��ȸ�� ����
public void freeboard_readnoUp(int freeboard_no);

public List<NoticeDTO> notice();

public void notice_readnoUp(int notice_no);

public NoticeDTO notice_detail(int notice_no);

public SQLjoin_Member_FreeBoardDTO rep_detail(int freeboard_no);

public int insert_reply(SQLjoin_Member_FreeBoardDTO dto);

public List<SQLjoin_Member_FreeBoardDTO> boardreply_list(int freeboard_no);

public String getNick_reply(String member_id);

public int freeboard_replycount(int hidden_freeboard_no);

public int boardreply_noGet(int freeboard_no);

public List<SQLjoin_Member_FreeBoardDTO> ajax_list(int boardreply_no);





}
