package com.todaylesson.oreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todaylesson.DTO.CalculateDTO;
import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.PageMaker;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.DTO.SeniorDTO;
import com.todaylesson.service.Senior_HS_Salescalculate_Service;

@Controller
@RequestMapping("/todaylessonsenior/")
public class Senior_HS_Salescalculate_Controller {
	
	@Resource(name="senior_HS_Salescalculate_Service")
	private Senior_HS_Salescalculate_Service salescalculateService;
	
	//������Ȳ
	@RequestMapping("/senior_sales_list/{senior_no}")
	public String salesList(Authentication authentication
			               ,@PathVariable int senior_no
			               ,@RequestParam(required=false, defaultValue="") String start_date
			               ,@RequestParam(required=false, defaultValue="") String end_date
			               ,@RequestParam(required=false, defaultValue="1") int currPage
			               ,@RequestParam(required=false, defaultValue="") String search
			               ,@RequestParam(required=false, defaultValue="") String searchtxt
			               //,@RequestParam(required=false, defaultValue="freeboard_no") String order
			               , Model model) {
		//��ť��Ƽ ������̵�
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();
		
		int seniorSales_TotalCount=salescalculateService.seniorSales_TotalCount(senior_no,search,searchtxt);
		int pageSize=15;
		int blockSize=5;
		
		
		PageMaker Totalpage = new PageMaker(currPage, seniorSales_TotalCount, pageSize, blockSize);
		
		//������Ȳ��ü����Ʈ
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> salesList=
	         salescalculateService.salesList(senior_no, start_date, end_date, search, searchtxt, Totalpage.getStartRow(), Totalpage.getEndRow()); 
		
		model.addAttribute("salesList", salesList);
		model.addAttribute("Totalpage", Totalpage);
		model.addAttribute("search",search);
		model.addAttribute("searchtxt",searchtxt);
		model.addAttribute("start_date",start_date);
		model.addAttribute("end_date",end_date);
		model.addAttribute("senior_no",senior_no);
		return "/TodayLesson_SeniorPage/hs_sn_sales_list.sn_main_section";
	}
	
	//�����û
	@RequestMapping("/senior_calculate_requestlist/{senior_no}")
	public String calculateRequestList( SeniorDTO dto, Model model
			                          , Authentication authentication
			                          , HttpServletRequest request,HttpServletResponse response
			                          ,@PathVariable int senior_no
			                          ) throws Exception {
		
		//��ť��Ƽ ������̵�
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();

		//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_requestlist
		                          =salescalculateService.calculateRequsetList(senior_no);
		model.addAttribute("cal_requestlist", cal_requestlist);
		
		//�����û  �������
		/*List<SeniorDTO> cal_SeniorAccount=salescalculateService.cal_SeniorAccount(member_id);
		model.addAttribute("cal_SeniorAccount", cal_SeniorAccount);*/
				
		//�����û ����Ʈ �����Ǽ�
		List<Integer> cal_paycount=salescalculateService.calPayCount(senior_no);
		model.addAttribute("cal_paycount", cal_paycount);
		
		//�����û ����Ʈ �������ͱݾ�
		List<Integer> cal_lessonrevenuecost=salescalculateService.calRevenueCost(senior_no);
		model.addAttribute("cal_lessonrevenuecost", cal_lessonrevenuecost);

		//�����û ����Ʈ ����Ʈ���
		List<Integer> cal_usepointsum=salescalculateService.calUsePointSum(senior_no);
		model.addAttribute("cal_usepointsum", cal_usepointsum);
		
		//�����û ��������� ���ݰ�꼭 �ΰ���
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_CommSurtax
				                  =salescalculateService.cal_CommSurtax(senior_no);
		model.addAttribute("cal_CommSurtax", cal_CommSurtax);
		
		//�����û �ôϾ������
		SeniorDTO accountdetalidto=salescalculateService.accountDetailDTO(member_id);
		model.addAttribute("accountdetalidto", accountdetalidto);
		
		//��ū��
		String imp_key 		=	"5422837446408379";
		String imp_secret	=	"FhzhNcakGqAxLiWaXndMLWKpsouBVOQB5pTTC3eitOPe6Mp39CPVyAl1YPCUEtwJTpDvsSOWGEaNqzQz";

		JSONObject json = new JSONObject();
		json.put("imp_key", imp_key);
		json.put("imp_secret", imp_secret);
	
		String token = getToken(request, response, json, "https://api.iamport.kr/users/getToken"); 
		model.addAttribute("token",token);
		
		
		//�����û���ɱݾ�
		Integer calculate_possibilitycost=salescalculateService.calculate_PossibilityCost(senior_no);
		model.addAttribute("calculate_possibilitycost", calculate_possibilitycost);

		
		//������ݾ�
		Integer calculate_waitingcost=salescalculateService.calculate_WaitingCost(senior_no);
		model.addAttribute("calculate_waitingcost", calculate_waitingcost );
		
		//�ôϾ� ���ް��� �� ��û����
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_SeniorInfo
		            =salescalculateService.cal_SeniorInfo(senior_no);
		model.addAttribute("cal_SeniorInfo", cal_SeniorInfo);
		
		return "/TodayLesson_SeniorPage/hs_sn_calculate_requestlist.sn_main_section";
	}
	
	//  ������Ʈ�� ��ū �� �޾ƿ���
	public String getToken(HttpServletRequest request,HttpServletResponse response,JSONObject json,String requestURL) throws Exception{
		String _token = "";

		try{

			String requestString = "";
			URL url = new URL(requestURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); 				
			connection.setInstanceFollowRedirects(false);  
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStream os= connection.getOutputStream();
			os.write(json.toString().getBytes());
			connection.connect();
			StringBuilder sb = new StringBuilder(); 
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line = null;  
				while ((line = br.readLine()) != null) { 
					sb.append(line + "\n");  
				}
				br.close();
				requestString = sb.toString();
			}
			os.flush();
			connection.disconnect();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(requestString);

			if((Long)jsonObj.get("code")  == 0){
				JSONObject getToken = (JSONObject) jsonObj.get("response");
				System.out.println("getToken==>>"+getToken.get("access_token") );
				_token = (String)getToken.get("access_token");
			}
 
		}catch(Exception e){
			e.printStackTrace();
			_token = "";
		}
		return _token;
	}
	
	//�����û ������������
	@RequestMapping("/senior_calculate_accountupdateresult")
	public String calculateRequestAccountUpdateResult (SeniorDTO dto, Model model)  {
		
		//�����û �ôϾ������������
		int accountupdatedto=salescalculateService.accountUpdateDTO(dto);
		model.addAttribute("accountupdatedto", accountupdatedto);
		
		return "TodayLesson_SeniorPage/hs_sn_calculate_accountupdateresult";
	}
	
	//�����û
	@RequestMapping("/senior_calculate_senior_calculate_requestresult")
	public String calculateRequestResult (CalculateDTO dto, OrderListDTO orderdto, Model model
			                             , Authentication authentication
                                         , HttpServletRequest request,HttpServletResponse response)  {
		
		//��ť��Ƽ ������̵�
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();
		
		//�����û �ôϾ������
		SeniorDTO accountdetalidto=salescalculateService.accountDetailDTO(member_id);
		model.addAttribute("accountdetalidto", accountdetalidto);		
		
		//�����û 
		int calculateRequestResult=salescalculateService.calculateRequestResult(dto);
		model.addAttribute("calculateRequestResult", calculateRequestResult);
		
		int updateOrderCalculateStatus=salescalculateService.updateOrderCalculateStatus(orderdto);
		
		return "TodayLesson_SeniorPage/hs_sn_calculate_requestresult";
	}

	//���곻��
	@RequestMapping("/senior_calculate_statementlist")
	public String calculateStatementList(Model model,
			                             Authentication authentication) {
		//��ť��Ƽ ������̵�
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();
		
		//���곻�� ����Ʈ
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_statementlist
		                          =salescalculateService.calStatementList(member_id);
		model.addAttribute("cal_statementlist", cal_statementlist);
		
		//���곻�� ����Ʈ �����Ǽ�
		List<Integer> cal_statement_paycount=salescalculateService.calStatementPayCount(member_id);
		model.addAttribute("cal_statement_paycount", cal_statement_paycount);
				
		//���곻�� ����Ʈ �������ͱݾ�
		List<Integer> cal_statement_lessonrevenuecost=salescalculateService.calStatementRevenueCost(member_id);
		model.addAttribute("cal_statement_lessonrevenuecost", cal_statement_lessonrevenuecost);

		//���곻�� ����Ʈ ����Ʈ���
		List<Integer> cal_statement_usepointsum=salescalculateService.calStatementUsePointSum(member_id);
		model.addAttribute("cal_statement_usepointsum", cal_statement_usepointsum);
		
		return "/TodayLesson_SeniorPage/hs_sn_calculate_statementlist.sn_main_section";
	}
	
}
