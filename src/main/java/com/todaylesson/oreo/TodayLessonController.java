package com.todaylesson.oreo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.google.connect.GoogleOAuth2Template;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.todaylesson.DTO.BannerDTO;
import com.todaylesson.DTO.CartDTO;
import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.DTO.Member_AuthDTO;
import com.todaylesson.DTO.MyLikeDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.PopUpDTO;
import com.todaylesson.DTO.ProductDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.DTO.SeniorDTO;
import com.todaylesson.service.Hm_Us_MailSendService;
import com.todaylesson.service.LoginService;
import com.todaylesson.service.Senior_HS_MainService;
import com.todaylesson.service.Admin_HS_MainService;
import com.todaylesson.service.Admin_YI_Popup_Service;
import com.todaylesson.service.EJ_All_Product_Service;
import com.todaylesson.service.EJ_US_NaverLoginBOService;
import com.todaylesson.service.TodaylessonService;
import com.todaylesson.service.User_HS_KakaoLoginService;
import com.todaylesson.service.User_HS_MainService;
import com.todaylesson.service.User_HS_MyPageService;
import com.todaylesson.service.YI_Google_AuthInfo;


//MainPage(User, Senior, Admin, Login, Logout , Join, FindId, FindPw, popupOpen) -> ���� Ȩ�� �ִ°͵�
@Controller
public class TodayLessonController {
   
	/* NaverLogin */
    private EJ_US_NaverLoginBOService naverLoginBO;
    private String apiResult = null;
    /* NaverLogin */
    
    /* googleLogin */
    @Inject
    private YI_Google_AuthInfo authInfo;
    
    @Autowired
    private GoogleOAuth2Template googleOAuth2Template;
    
    @Autowired
    private OAuth2Parameters googleOAuth2Parameters;
    /* googleLogin */
    
    /* kakaoLogin */
    @Resource(name="kakaologinservice")
    private User_HS_KakaoLoginService hs_kakaologinservice;
    /* kakaoLogin */
    
    /* security */
    @Resource(name="todaylessonService")
    private TodaylessonService todaylessonService;
    /* security */
    
    /* User_Main */
    @Resource(name="user_HS_MainService")
    private User_HS_MainService userMainService;
    /* User_Main */

    /* User_MyPage */
    @Resource(name="user_HS_MyPageService")
    private User_HS_MyPageService userMyPageService;
    /* User_MyPage */
    
    /* Senior_MyPage */
    @Resource(name="senior_HS_MainService")
    private Senior_HS_MainService seniorMainService;
    /* Senior_MyPage */
    
    /* Admin_Main */
    @Resource(name="admin_HS_MainService")
    private Admin_HS_MainService adminMainService;
    /* Admin_Main */
    
    /* ���̵�ã�� */
    @Resource(name="loginService")
    private LoginService loginService;
    /* ���̵�ã�� */
    
    /* ��й�ȣã�� */
    @Autowired
    private Hm_Us_MailSendService mailSender;
    /* ��й�ȣã�� */
    
    /* ����� ���ƿ�, ��ٱ��� */
    @Resource(name="us_store_service")
    private EJ_All_Product_Service userStoreService;
    /* ����� ���ƿ�, ��ٱ��� */
    
    /*�˾�*/ 
    @Resource(name="admin_YI_Popup_Service")
    private Admin_YI_Popup_Service popupService;
    /*�˾�*/
    
    @RequestMapping("/todaylessonadmin")
    public String admin(@RequestParam(required=false,defaultValue="date") String ymd,
    		            Authentication authentication,
                        Model model, HttpSession session) {
        //��ť��Ƽ ������̵�
        UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
        String member_id = userDetails.getUsername(); 
    	
        //������ �г��ӹޱ�
    	String adminMemberNick=adminMainService.adminMemberNick(member_id);
    	session.setAttribute("adminMemberNick", adminMemberNick);
    	
    	//���ϰ����ڼ� ����
    	int memberJoinCount=adminMainService.memberJoinCount();
    	model.addAttribute("memberJoinCount", memberJoinCount);
    	
    	//���� �ôϾ���ȯ�� ����
    	int seniorChangeCount=adminMainService.seniorChangeCount();
    	model.addAttribute("seniorChangeCount", seniorChangeCount);
    	
    	//���ϰԽñۼ� ����
    	int freeboardWriteCount=adminMainService.freeboardWriteCount();
    	model.addAttribute("freeboardWriteCount", freeboardWriteCount);
    	
    	//���� �Ǹűݾ� ����
    	int orderlistCostSum=adminMainService.orderlistCostSum();
    	model.addAttribute("orderlistCostSum", orderlistCostSum);
    	
    	//�������Ȳ
    	  //��ϵȻ�ǰ
    	  int registrationProductCount=adminMainService.registrationProductCount();
    	  model.addAttribute("registrationProductCount", registrationProductCount);
    	  //�ǸŰ��ɻ�ǰ
    	  int possibilityProductCount=adminMainService.possibilityProductCount();
    	  model.addAttribute("possibilityProductCount", possibilityProductCount);
    	  //ǰ����ǰ
    	  int soldOutProductCount=adminMainService.soldOutProductCount();
    	  model.addAttribute("soldOutProductCount", soldOutProductCount);
    	  
    	//������Ȳ
    	  //��ϵ� ���� 
    	  int registrationLessonCount=adminMainService.registrationLessonCount();
    	  model.addAttribute("registrationLessonCount", registrationLessonCount);
    	  //���·���
    	  int openLessonCount=adminMainService.openLessonCount();
    	  model.addAttribute("openLessonCount", openLessonCount);
    	  //��������
    	  int closeLessonCount=adminMainService.closeLessonCount();
    	  model.addAttribute("closeLessonCount", closeLessonCount);
    	  //ǰ������
    	  int soldOutLessonCount=adminMainService.soldOutLessonCount();
    	  model.addAttribute("soldOutLessonCount", soldOutLessonCount);
    	  
    	//�ֹ���Ȳ
    	  //�ֹ��Ϸ�
    	  int orderCompleteCount=adminMainService.orderCompleteCount();
    	  model.addAttribute("orderCompleteCount", orderCompleteCount);
    	  //�����
    	  int orderDuringShippingCount=adminMainService.orderDuringShippingCount();
    	  model.addAttribute("orderDuringShippingCount", orderDuringShippingCount);
    	  //��ۿϷ�
    	  int orderShippingCompleteCount=adminMainService.orderShippingCompleteCount();
    	  model.addAttribute("orderShippingCompleteCount", orderShippingCompleteCount);
    	  //�ֹ����
    	  int orderCancelCount=adminMainService.orderCancelCount();
    	  model.addAttribute("orderCancelCount", orderCancelCount);
    	  
    	//������Ȳ
    	  //�����Ϸ�
    	  int paymentCompleteCount=adminMainService.paymentCompleteCount();
    	  model.addAttribute("paymentCompleteCount", paymentCompleteCount);
    	  //ȯ������
    	  int refundAcceptCount=adminMainService.refundAcceptCount();
    	  model.addAttribute("refundAcceptCount", refundAcceptCount);
    	  //ȯ�ҿϷ�
    	  int refundCompleteCount=adminMainService.refundCompleteCount();
    	  model.addAttribute("refundCompleteCount", refundCompleteCount);
    	  
    	//����������Ȳ
    	  //�����ű�����  
    	  int newLessonAcceptCount=adminMainService.newLessonAcceptCount();
    	  model.addAttribute("newLessonAcceptCount", newLessonAcceptCount);
    	  //�����ɻ���
    	  int newLessonEvaluationCount=adminMainService.newLessonEvaluationCount();
    	  model.addAttribute("newLessonEvaluationCount", newLessonEvaluationCount);
    	  //��������
    	  int newLessonAcceptanceCount=adminMainService.newLessonAcceptanceCount();
    	  model.addAttribute("newLessonAcceptanceCount", newLessonAcceptanceCount);
    	  //��������
    	  int newLessonRefuseCount=adminMainService.newLessonRefuseCount();
    	  model.addAttribute("newLessonRefuseCount", newLessonRefuseCount);
    	  
    	//�ôϾ�������Ȳ
    	  //������
    	  int seniorCalculateWaitCount=adminMainService.seniorCalculateWaitCount();
    	  model.addAttribute("seniorCalculateWaitCount", seniorCalculateWaitCount);
    	  //���갡��
    	  int seniorCalculatePossibleCount=adminMainService.seniorCalculatePossibleCount();
    	  model.addAttribute("seniorCalculatePossibleCount", seniorCalculatePossibleCount);    	
    	  //����Ϸ�
    	  int seniorCalculateCompleteCount=adminMainService.seniorCalculateCompleteCount();
    	  model.addAttribute("seniorCalculateCompleteCount", seniorCalculateCompleteCount);
    	  
    	//1:1������Ȳ
    	  //��������
    	  int questionLessonCount=adminMainService.questionLessonCount();
    	  model.addAttribute("questionLessonCount", questionLessonCount);
    	  //�ôϾ��
    	  int questionSeniorCount=adminMainService.questionSeniorCount();
    	  model.addAttribute("questionSeniorCount", questionSeniorCount);
    	  //������
    	  int questionStoreCount=adminMainService.questionStoreCount();
    	  model.addAttribute("questionStoreCount", questionStoreCount);
    	  //�ֹ�����
    	  int questionOrderCount=adminMainService.questionOrderCount();
    	  model.addAttribute("questionOrderCount", questionOrderCount);
    	  //��Ÿ����
    	  int questionOtherCount=adminMainService.questionOtherCount();
    	  model.addAttribute("questionOtherCount", questionOtherCount);
    	  //�亯���
    	  int questionAnswerWaitCount=adminMainService.questionAnswerWaitCount();
    	  model.addAttribute("questionAnswerWaitCount", questionAnswerWaitCount);
    	  //�亯�Ϸ�
    	  int questionAnswerCompleteCount=adminMainService.questionAnswerCompleteCount();
    	  model.addAttribute("questionAnswerCompleteCount", questionAnswerCompleteCount);
    	  
    	//����ī�װ�
    	int lessonITCount=adminMainService.lessonITCount();
    	int lessonCookCount=adminMainService.lessonCookCount();
    	int lessonHandmadeCount=adminMainService.lessonHandmadeCount();
    	int lessonSportCount=adminMainService.lessonSportCount();
    	int lessonEducationCount=adminMainService.lessonEducationCount();
    	int lessonOtherCount=adminMainService.lessonOtherCount();
    	
    	model.addAttribute("lessonITCount", lessonITCount);
    	model.addAttribute("lessonCookCount", lessonCookCount);
    	model.addAttribute("lessonHandmadeCount", lessonHandmadeCount);
    	model.addAttribute("lessonSportCount", lessonSportCount);
    	model.addAttribute("lessonEducationCount", lessonEducationCount);
    	model.addAttribute("lessonOtherCount", lessonOtherCount);
    	
    	//��ǰī�װ�
    	int productITCount=adminMainService.productITCount();
    	int productCookCount=adminMainService.productCookCount();
    	int productHandmadeCount=adminMainService.productHandmadeCount();
    	int productSportCount=adminMainService.productSportCount();
    	int productEducationCount=adminMainService.productEducationCount();
    	int productOtherCount=adminMainService.productOtherCount();
    	
    	model.addAttribute("productITCount", productITCount);
    	model.addAttribute("productCookCount", productCookCount);
    	model.addAttribute("productHandmadeCount", productHandmadeCount);
    	model.addAttribute("productSportCount", productSportCount);
    	model.addAttribute("productEducationCount", productEducationCount);
    	model.addAttribute("productOtherCount", productOtherCount);
    	
    	//���ɴ뺰 ȸ����Ȳ
    	int memberAge10Sum=adminMainService.memberAge10Sum();
    	int memberAge20Sum=adminMainService.memberAge20Sum();
    	int memberAge30Sum=adminMainService.memberAge30Sum();
    	int memberAge40Sum=adminMainService.memberAge40Sum();
    	int memberAge50Sum=adminMainService.memberAge50Sum();
    	int memberAge60Sum=adminMainService.memberAge60Sum();
    	int memberAge70PlusSum=adminMainService.memberAge70PlusSum();
    	
    	model.addAttribute("memberAge10Sum", memberAge10Sum);
    	model.addAttribute("memberAge20Sum", memberAge20Sum);
    	model.addAttribute("memberAge30Sum", memberAge30Sum);
    	model.addAttribute("memberAge40Sum", memberAge40Sum);
    	model.addAttribute("memberAge50Sum", memberAge50Sum);
    	model.addAttribute("memberAge60Sum", memberAge60Sum);
    	model.addAttribute("memberAge70PlusSum", memberAge70PlusSum);
    	
    	//�������(�Ϻ�, �ֺ�, ����, �⺰)
    	List<OrderListDTO> adMainStatSalesAllChart=adminMainService.adMainStatSalesAllChart(ymd);
    	model.addAttribute("adMainStatSalesAllChart", adMainStatSalesAllChart);
    	
    	return "hs_ad_main";
    }
    
    //AmChart
  	@RequestMapping(value = "/todaylessonadmin", method = RequestMethod.POST)
  	@ResponseBody
  	public List chartOutput()throws Exception {
  		
  		List<OrderListDTO> chartOutput = adminMainService.chartOutput();
  		ArrayList response = new ArrayList();
  		  for(int i =0; i < chartOutput.size(); i ++) {
  		    HashMap<String , Object> map = new HashMap<String, Object>();
  		    map.put("date", chartOutput.get(i).getRegYear()+"-"+chartOutput.get(i).getRegMonth()+"-"+chartOutput.get(i).getRegDay());
  		    map.put("output", chartOutput.get(i).getCostTotal());
  		    response.add(map);
  		    //(map);
  		    //(response);
  		  }
  		  //(response);
  		  return response;

  		}
    
    @RequestMapping("/todaylessonsenior")
    public String senior(Authentication authentication
                        ,Model model, HttpSession session) {
    	
    	//��ť��Ƽ ������̵�
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
    	String member_id = userDetails.getUsername();
    	
    	SeniorDTO seniorPageInfo=seniorMainService.seniorPageInfo(member_id);
    	session.setAttribute("seniorPageInfo", seniorPageInfo);
    	
    	return "hs_sn_main"; 
    }
       

    @RequestMapping("/todaylessonmypage")
    public String usermypage(Authentication authentication
    		                ,Model model, HttpSession session) {
    	//��ť��Ƽ ������̵�
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
    	String member_id = userDetails.getUsername();

    	//���������� ���η��� �� ����Ʈ ��Ÿ����
    	MemberDTO myPageMyLevel_MyPoint=userMyPageService.myPageMyLevel_MyPoint(member_id);
    	session.setAttribute("myPageMyLevel_MyPoint", myPageMyLevel_MyPoint); //�������� ó���ϱ�..
    	
    	//���������� �������� ����
    	SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO myPageAttendLseeon=userMyPageService.myPageAttendLseeon(member_id);
    	session.setAttribute("myPageAttendLseeon", myPageAttendLseeon);
    	//���������� �����ʺ���
    	
    	return "hs_us_mypage";
    }
    
    @RequestMapping("/todaylesson")
    public String all(Model model){
	    //�űԷ������
    	List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> newlessonlist 
    	    =userMainService.newLessonList();
    	model.addAttribute("newlessonlist", newlessonlist);
    	
    	//����Ʈ�������
    	List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> bestlessonlist 
    	    =userMainService.BestLessonList();
    	model.addAttribute("bestlessonlist", bestlessonlist);
    	
    	//��õ�������
    	List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> referencelessonlist 
    	    =userMainService.referenceLessonList();
    	model.addAttribute("referencelessonlist", referencelessonlist);
    	
    	//�űԽ������
    	List<ProductDTO> storenewproductlist=userMainService.StoreNewProductList(); 
    	model.addAttribute("storenewproductlist", storenewproductlist);
    	
    	//����Ʈ�������
    	List<ProductDTO> storeBestproductlist=userMainService.StoreBestProductList(); 
    	model.addAttribute("storeBestproductlist", storeBestproductlist);
    	
    	//�̺�Ʈ�����̴�(BannerSlider)
    	List<BannerDTO> mainEventBannerSlider=userMainService.mainEventBannerSlider();
    	model.addAttribute("mainEventBannerSlider", mainEventBannerSlider);
    	 
    	//�̹��� ���
    	BannerDTO mainIMGBannerSlider=userMainService.mainIMGBannerSlider();
    	model.addAttribute("mainIMGBannerSlider", mainIMGBannerSlider);
    	
    	//���ν����̴�
    	List<BannerDTO> mainBannerSlider=userMainService.mainBannerSlider();
    	model.addAttribute("mainBannerSlider", mainBannerSlider);
    	
    	//�˾����� ��������
    	PopUpDTO popup_dto1=popupService.popupinfo(1);
		model.addAttribute("popup_dto1",popup_dto1);
    	
    	return "hs_us_main";
    }
       
    //��ǰ���ƿ�
  	@ResponseBody
  	@RequestMapping("/likejson")
  	public String likemain(@RequestParam(value="product_no") int product_no
  			,@RequestParam(value="member_id") String member_id)
  	{
  		//("productno:"+product_no+member_id);
  		MyLikeDTO likedto=new MyLikeDTO();
  		likedto.setMember_id(member_id);
  		likedto.setProduct_no(product_no);
  	
  		String result;
  		
  		List<MyLikeDTO> product_in_mylike = userStoreService.has_mylike_product(likedto);
  		userStoreService.updateproductlike(product_no);
  		
  		if (product_in_mylike.isEmpty()) {
  			userStoreService.insertmylike(likedto);
  			result="success"; 
  		} else {
  			result="false";
  		}
  		return result;
  		
  	}
  	//��ǰ��ٱ���
  	@ResponseBody
  	@RequestMapping("/cartjson")
  	public String cartmain(@RequestParam(value="product_no") int product_no
  			,@RequestParam(value="member_id") String member_id)
  	{
  		//("cart��");
  		//("productno:"+product_no+member_id);
  		CartDTO cartdto=new CartDTO();
  		cartdto.setMember_id(member_id);
  		cartdto.setProduct_no(product_no);
  		
  		String result;
  		List<CartDTO> product_in_cart = userStoreService.has_cart_product(cartdto);
  		
  		if (product_in_cart.isEmpty()) {
  			cartdto.setCart_amount(1);
  			userStoreService.insertcart(cartdto);
  			result="success"; 
  		} else {
  			result="false";
  		}
  		return result;
  	}
  	
  	/* ���Ѿ��� ���������� */   
  	@RequestMapping("/error")
       public String error()
       {
           return "/todaylesson_sec__error";
           }

       @RequestMapping("/todaylessonlogin")
       public String login( String error, String logout, Model model)
       {     
 	       //���̵��� �߸��Է½� ����.. 
    	  if (error !=null)
             model.addAttribute("error", "Please check your ID or Password");
             
          if(logout !=null)
             model.addAttribute("logout","logout");
          
          
          return "/TodayLesson_UserPage/hs_us_main_sec_login.us_main_section";
          //return "/TodayLesson_UserPage/hs_us_main_sec_login";
       }
      
       //kakao �α��� â url �����ѱ��
       @RequestMapping(value = "/kakaologinurl", method = RequestMethod.GET)
       public ModelAndView kakaoLoginURL(HttpSession session) {
    	   ModelAndView mv = new ModelAndView();
    	   
    	   //kakao login url ȣ��
    	   String kakaologin_URL = hs_kakaologinservice.getAuthorizationUrl(session);
    	   
    	   //������ ���� URL�� View(hs_us_main_sec_login.jsp)�� ����
    	   mv.setViewName("hs_us_main_sec_login");
    	   
    	   //īī�� �α���
    	   mv.addObject("kakaologin_URL", kakaologin_URL);
    	   
    	   return mv;
       }
       
       @RequestMapping(value = "/todaylessonkakaologin", produces = "application/json" ,
	                   method = {RequestMethod.GET, RequestMethod.POST})
       public ModelAndView KakaoLogin(@RequestParam("code") String code, HttpServletRequest request,
    		                          HttpServletResponse response, HttpSession session) throws IOException{
    	   ModelAndView mv = new ModelAndView();
    	   
    	   //������� node�� �����
    	   JsonNode node = hs_kakaologinservice.getAccessToken(code);
    	   //accessToken�� ����ڰ� �α����� ��� ������ �������
    	   JsonNode accessToken = node.get("access_token");
    	   
    	   //("Kakao_Code = " +code);
    	   //("Kakao_AccessToken = " + accessToken);
    	   
    	   //����� ����
    	   JsonNode KakaoUserInfo=hs_kakaologinservice.getKakaoUserInfo(accessToken);
    	      String kakao_email = null;
    	      String kakao_name = null;
    	      String kakao_gender = null;
    	      String kakao_birthday = null;
    	      String kakao_age = null;
    	   //�������� īī������ �������� Get properties
    	   JsonNode properties = KakaoUserInfo.path("properties"); 
    	   JsonNode kakao_account = KakaoUserInfo.path("kakao_account");
    	      kakao_email = kakao_account.path("email").asText(); 
 	          kakao_name = kakao_account.path("name").asText();
 	          kakao_gender = kakao_account.path("gender").asText();
 	          kakao_birthday = kakao_account.path("birthday").asText();
 	          kakao_age = kakao_account.path("age").asText();
 	          
 	          //("kakao_name = "+kakao_name);

 	       session.setAttribute("kakao_email", kakao_email);
 	       session.setAttribute("kakao_name", kakao_name);
 	       session.setAttribute("kakao_gender", kakao_gender);
 	       session.setAttribute("kakao_birthday", kakao_birthday);   
 	       session.setAttribute("kakao_age", kakao_age);
    	   
 	       //������ �α��������� View(hs_us_main_sec_login.jsp)�� ����
 	       mv.setViewName("todaylessonmypage");
 	       
    	   return mv;
       }
 
       //���̹��α��� 

       @Autowired
       private void setNaverLoginBO(EJ_US_NaverLoginBOService naverLoginBO) {
           this.naverLoginBO = naverLoginBO;
       }

       //�α��� ù ȭ�� ��û �޼ҵ�
       @RequestMapping(value = "todaylessonlogin", method = { RequestMethod.GET, RequestMethod.POST })
       public String naverlogin(Model model, HttpSession session) {

    
           
           /* ���̹����̵�� ���� URL�� �����ϱ� ���Ͽ� naverLoginBOŬ������ getAuthorizationUrl�޼ҵ� ȣ�� */
           String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
           
           //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
           //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
           ////("���̹�:" + naverAuthUrl);
           
           //���̹� 
           model.addAttribute("naverlogin_URL", naverAuthUrl);
           
           //����
         //URL�� �����Ѵ�.
           String url = googleOAuth2Template.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
           //("/googleLogin, url : " + url);
           model.addAttribute("google_url", url);
           
          
           
           /* ������ ���� URL�� View�� ���� */
           return "/TodayLesson_UserPage/hs_us_main_sec_login.us_main_section";
       }

       //���̹� �α��� ������ callbackȣ�� �޼ҵ�
       @RequestMapping(value = "navercallback", method = { RequestMethod.GET, RequestMethod.POST })
       public String naverlogincallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
               throws IOException {
           //("����� callback");
           OAuth2AccessToken oauthToken;
           oauthToken = naverLoginBO.getAccessToken(session, code, state);
           //�α��� ����� ������ �о�´�.
           apiResult = naverLoginBO.getUserProfile(oauthToken);
           //(naverLoginBO.getUserProfile(oauthToken).toString());
           model.addAttribute("result", apiResult);
           //("result"+apiResult);
           /* ���̹� �α��� ���� ������ View ȣ�� */
//         JSONObject jsonobj = jsonparse.stringToJson(apiResult, "response");
//         String snsId = jsonparse.JsonToString(jsonobj, "id");
//         String name = jsonparse.JsonToString(jsonobj, "name");
   //
//         UserVO vo = new UserVO();
//         vo.setUser_snsId(snsId);
//         vo.setUser_name(name);
   //
//         //(name);
//         try {
//             vo = service.naverLogin(vo);
//         } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }


//         session.setAttribute("login",vo);
//         return new ModelAndView("user/loginPost", "result", vo);
           
           return "naverlogin/naversuccess";
       }

     //google �α���   
       @RequestMapping(value = "/googleSignInCallback")
       public ModelAndView doSessionAssignActionPage(HttpServletRequest request,Model model) throws Exception {
    	   ModelAndView view = new ModelAndView();
           String code = request.getParameter("code");
           //(code);
           
           //RestTemplate�� ����Ͽ� Access Token �� profile�� ��û�Ѵ�.
           RestTemplate restTemplate = new RestTemplate();
           MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
           parameters.add("code", code);
           parameters.add("client_id", authInfo.getClientId());
           parameters.add("client_secret", authInfo.getClientSecret());
           parameters.add("redirect_uri", googleOAuth2Parameters.getRedirectUri());
           parameters.add("grant_type", "authorization_code");
    
           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
           HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
           ResponseEntity<Map> responseEntity = restTemplate.exchange("https://www.googleapis.com/oauth2/v4/token", HttpMethod.POST, requestEntity, Map.class);
           Map<String, Object> responseMap = responseEntity.getBody();
    
           // id_token ��� Ű�� ����ڰ� ������ �����Ѵ�.
           // �޾ƿ� ����� JWT (Json Web Token) �������� �޾ƿ´�. �޸� ������ ��� ù ��°�� �� ��ū�� ���� ��Ÿ ����, �� ��°�� �츮�� �ʿ��� ������ �����Ѵ�.
           // ����° �κп��� �������� �����ϱ� ���� Ư�� �˰������� ��ȣȭ�Ǿ� ���̴׿� ����Ѵ�.
           //Base 64�� ���ڵ� �Ǿ� �����Ƿ� ���ڵ��Ѵ�.
    
           String[] tokens = ((String)responseMap.get("id_token")).split("\\.");
           Base64 base64 = new Base64(true);
           String body = new String(base64.decode(tokens[1]));
           
           //("����1"+tokens.length);
           //("����2"+new String(Base64.decodeBase64(tokens[0]), "utf-8"));
           //("����3"+new String(Base64.decodeBase64(tokens[1]), "utf-8"));
    
           //Jackson�� ����� JSON�� �ڹ� Map �������� ��ȯ
           ObjectMapper mapper = new ObjectMapper();
           Map<String, String> result = mapper.readValue(body, Map.class);
           //("���� �̸��� : "+result.get("email"));
           //("���� sub : "+result.get("sub"));
           
           if(todaylessonService.oauth2idcheck(result.get("email"))==0)
           {
           List<Member_AuthDTO> list=new ArrayList<>();
			MemberDTO dto=new MemberDTO();
			dto.setMember_id(result.get("email"));
			dto.setMember_pwd(result.get("sub"));
			 //auth�� list�� �־ dto�� ����
			 list.add(new Member_AuthDTO("ROLE_USER",dto.getMember_id()));
			 dto.setAuthList(list);
			 
			 todaylessonService.googleinsert(dto);

           }
           view.addObject("googleId",result.get("email"));
           view.addObject("googlePwd",result.get("sub"));
           view.setViewName("/TodayLesson_UserPage/hs_us_main_sec_login.us_main_section");
           
           return view;
    
       }
       
       //ȸ������
       @RequestMapping("/todaylesson/join")
       public String join(HttpServletRequest request, Model model) throws Exception {	   
    	   
    	   /*���� sms����*/
           String api_key = "NCSRC0XSPD85BDRL"; //������ ���� api key�� �߰�
           String api_secret = "2LVQYEMQFBIBEG8WVXKQOWQ6KPDDVJQ9";  //������ ���� api secret�� �߰�

           com.todaylesson.Sms.Coolsms coolsms = new com.todaylesson.Sms.Coolsms(api_key, api_secret);
           //�� �κ��� Ȩ���������� ���� �ڹ������� �߰��Ѵ��� �� Ŭ������ import�ؾ� �� �� �ִ� Ŭ�����̴�.

           HashMap<String, String> set = new HashMap<String, String>();
              set.put("from", "01026063254"); // ���Ź�ȣ
              set.put("to", (String)request.getParameter("to")); // �߽Ź�ȣ, jsp���� ������ �߽Ź�ȣ�� �޾� map�� �����Ѵ�.
              set.put("text", (String)request.getParameter("text")); // ���ڳ���, jsp���� ������ ���ڳ����� �޾� map�� �����Ѵ�.
              set.put("type", "sms"); // ���� Ÿ��
              
              String text=(String)request.getParameter("text");
              model.addAttribute("to",(String)request.getParameter("to"));
              model.addAttribute("auth_num",text);
             
              //(set);

              JSONObject result = coolsms.send(set); // ������&���۰���ޱ�

              if ((boolean)result.get("status") == true) {
                // �޽��� ������ ���� �� ���۰�� ���
                //("����");
                //(result.get("group_id")); // �׷���̵�
                //(result.get("result_code")); // ����ڵ�
                //(result.get("result_message")); // ��� �޽���
                //(result.get("success_count")); // �޽������̵�
                //(result.get("�����޼�����: error_count")); // ������ ������ ������ �޽��� ��
              } else {
                // �޽��� ������ ����
                //("����");
                //(result.get("code")); // REST API �����ڵ�
                //(result.get("message")); // �����޽���
              }
           return "TodayLesson_UserPage/todaylesson_joinform.us_main_section";
       }
       
       @RequestMapping("/joinresult")
		 public String joinresult(@RequestParam("id") String member_id, @RequestParam("pwd") String member_pwd
				 				, @RequestParam("name") String member_name, @RequestParam("birth") String member_birth
				 				, @RequestParam("email") String member_email, @RequestParam("phone") String member_phone
				 				, @RequestParam("zipcode") int member_zipcode, @RequestParam("nick") String member_nick
	 				            , @RequestParam("addrselect") int addrselect, @RequestParam("roadaddr") String roadaddr
	 				            , @RequestParam("jibunaddr") String jibunaddr, @RequestParam("detailaddr") String detailaddr
				 				, MemberDTO dto,Model model){

			 dto.setMember_id(member_id);
			 dto.setMember_pwd(member_pwd);
			 dto.setMember_name(member_name);
			 dto.setMember_birth(member_birth);
			 dto.setMember_email(member_email);
			 dto.setMember_phone(member_phone);
			 dto.setMember_zipcode(member_zipcode);
			 dto.setMember_nick(member_nick);
			 
			//��ü�ּ�(����or�����ּ� + ���ּ�) addr�� ����
			 String fulladdr= "";	
			 if(addrselect==0){
				 fulladdr=roadaddr;
			 }else{
				 fulladdr=jibunaddr;
			 }
			 
			 dto.setMember_addr(fulladdr+" "+detailaddr);
			 
			 List<Member_AuthDTO> list=new ArrayList<>();
			
			 //auth�� list�� �־ dto�� ����
			 list.add(new Member_AuthDTO("ROLE_USER",dto.getMember_id()));
			 dto.setAuthList(list);
			 
			 int result=todaylessonService.insert(dto);
			 model.addAttribute("result",result);
			 
			 return "TodayLesson_UserPage/yi_joinresult";
		 } 
       
       
       @RequestMapping("/findId")
       public String findId()
       {
          return "TodayLesson_UserPage/yi_find_id.us_main_section";
       }
       
       /*id�ߺ� üũ*/
       @ResponseBody 
       @RequestMapping(value="/idCheck", method= RequestMethod.POST)
       public int idCheck(@RequestParam("id") String member_id,Model model)
       {
           //(member_id);
           int row = loginService.idCheck(member_id);
           model.addAttribute("data",row);
           return row;
       }
       
       /*id ã��*/
       @ResponseBody
       @RequestMapping(value = "/userSearch", method = RequestMethod.POST)
       public String userIdSearch(@RequestParam("inputName_1") String member_name, 
                                  @RequestParam("inputPhone_1") String member_phone) {
            HashMap<String,Object> map=new HashMap<>();
            //(member_name);
            //(member_phone);
            map.put("member_name", member_name);
            map.put("member_phone", member_phone);
            String result = loginService.get_searchId(map);
            
            //(result);

            return result;
       }
        
       //pwd ã��
       @RequestMapping("/findPw")
       public String findPw()
       {
     		return "/TodayLesson_UserPage/hm_find_pwd.us_main_section";
       }

       @RequestMapping(value="/findPassword",method=RequestMethod.POST)
       public String findPassword(@RequestParam("inputId_2")String member_id,
                                  @RequestParam("inputEmail_2") String member_email
                                  ,HttpServletRequest request, Model model){
     		int result = mailSender.mailSendWithPassword(member_id, member_email, request);
     		//(member_email);
     		model.addAttribute("result",result);
     		return "/TodayLesson_UserPage/hm_us_search_pwd";
       }
       
       //popup
       @RequestMapping("/popupOpen")
    	public String popuptest(Model model)
    	{
    		PopUpDTO dto1=popupService.popupinfo(1);
    		model.addAttribute("dto1",dto1);
    		
    		return "/TodayLesson_UserPage/yi_popup";
    	}

}
