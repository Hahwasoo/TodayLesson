package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.CalculateDTO;
import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.DTO.SeniorDTO;

@Mapper
public interface Senior_HS_Salescalculate_Mapper {
	
	//시니어 멤버아이디로 시니어번호 받아오기
	//public int get_Seniorno(String member_id);
	
	//매출현황 토탈카운트
	public int seniorSales_TotalCount(HashMap<String, Object> hm);
	
	//매출현황전체리스트
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> SalesList(HashMap<String, Object> hm);

	//정산신청 리스트 정산번호 / 정산상태 / 정산신청일 / 정산기간 / 정산계좌
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_RequestList(int senior_no);
	
	//정산신청  정산계좌
	//public List<SeniorDTO> cal_SeniorAccount(String member_id);
	
	//정산신청 리스트 결제건수
	public List<Integer> calPayCount(int senior_no);

	//정산신청 리스트 레스수익금액
	public List<Integer> calRevenueCost(int senior_no);

	//정산신청 리스트 포인트사용
	public List<Integer> calUsePointSum(int senior_no);

	//정산신청 정산금액 정산수수료 세금계산서 부가세
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_CommSurtax(int senior_no);

	
	//정산신청 시니어디테일
	public SeniorDTO accountDetailDTO(String member_id);
	
	//정산신청 시니어계좌정보수정
	public int accountUpdateDTO(SeniorDTO dto);

	//정산신청가능금액
	public Integer calculate_PossibilityCost(int senior_no);

	//정산대기금액
	public Integer calculate_WaitingCost(int senior_no);
 
	//정산신청
	public int calculateRequestResult(CalculateDTO dto);
	
	//정산내역 목록 리스트
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calStatementList(String member_id);

	//정산내역 리스트 결제건수
	public List<Integer> calStatementPayCount(String member_id);

	//정산내역 리스트 레스수익금액
	public List<Integer> calStatementRevenueCost(String member_id);

	//정산내역 리스트 포인트사용
	public List<Integer> calStatementUsePointSum(String member_id);

	//정산상태업데이트
	public int updateOrderCalculateStatus(OrderListDTO orderdto);

	//시니어 신청완료된 계좌정보
	//public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_SeniorInfo(int senior_no);
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_SeniorInfo();

}
