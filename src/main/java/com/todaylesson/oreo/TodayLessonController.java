package com.todaylesson.oreo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.DTO.Member_AuthDTO;
import com.todaylesson.DTO.ProductDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO;
import com.todaylesson.service.Hm_Us_MailSendService;
import com.todaylesson.service.LoginService;
import com.todaylesson.service.Admin_HS_MainService;
import com.todaylesson.service.EJ_US_NaverLoginBOService;
import com.todaylesson.service.TodaylessonService;
import com.todaylesson.service.User_HS_KakaoLoginService;
import com.todaylesson.service.User_HS_MainService;
import com.todaylesson.service.User_HS_MyPageService;
import com.todaylesson.service.YI_Google_AuthInfo;


//MainPage(User, Senior, Admin, Login, Logout , Join, FindId, FindPw) -> ���� Ȩ�� �ִ°͵�
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
    
    @RequestMapping("/todaylessonadmin")
    public String admin(Model model) { 
    	
    	//���ϰԽñۼ� ����
    	int freeboardWriteCount=adminMainService.freeboardWriteCount();
    	
    	model.addAttribute("freeboardWriteCount", freeboardWriteCount);
    	
    	//���ϽűԷ��� ����
    	
    	//���� �Ǹűݾ� ����
    	int orderlistCostSum=adminMainService.orderlistCostSum();
    	
    	model.addAttribute("orderlistCostSum", orderlistCostSum);
    	
    	//���ϰ����ڼ� ����
    	int memberJoinCount=adminMainService.memberJoinCount();
    	
    	model.addAttribute("memberJoinCount", memberJoinCount);
    	
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
    	
    	return "hs_ad_main";
    }
    
    @RequestMapping("/todaylessonsenior")
    public String senior() {
    	return "hs_sn_main"; 
    }
       
/*    @RequestMapping("/todaylessonmember")
    public String member(){
    	return "hs_us_mypage";
    } 
    */
    @RequestMapping("/todaylessonmypage")
    public String usermypage(Authentication authentication
    		                ,Model model) {
    	//��ť��Ƽ ������̵�
    	UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
    	String member_id = userDetails.getUsername();
    	
    	//���������� ���η��� �� ����Ʈ ��Ÿ����
    	MemberDTO myPageMyLevel_MyPoint=userMyPageService.myPageMyLevel_MyPoint(member_id);
    	model.addAttribute("myPageMyLevel_MyPoint", myPageMyLevel_MyPoint);
    	
    	//���������� �����ʺ���
    	
    	return "hs_us_mypage";
    }
   
    @RequestMapping("/todaylesson")
    public String all(Model model){
	    //�űԷ������
    	List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> newlessonlist 
    	    =userMainService.newLessonList();
    	//�űԽ������
    	List<ProductDTO> storenewproductlist=userMainService.StoreNewProductList(); 
    	
    	model.addAttribute("newlessonlist", newlessonlist);
    	model.addAttribute("storenewproductlist", storenewproductlist);
    	
    	return "hs_us_main";
    }
          
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
    	   
    	   System.out.println("Kakao_Code = " +code);
    	   System.out.println("Kakao_AccessToken = " + accessToken);
    	   
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
 	          
 	          System.out.println("kakao_name = "+kakao_name);

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
           //System.out.println("���̹�:" + naverAuthUrl);
           
           //���̹� 
           model.addAttribute("naverlogin_URL", naverAuthUrl);
           
           //����
         //URL�� �����Ѵ�.
           String url = googleOAuth2Template.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
           System.out.println("/googleLogin, url : " + url);
           model.addAttribute("google_url", url);

           /* ������ ���� URL�� View�� ���� */
           return "/TodayLesson_UserPage/hs_us_main_sec_login.us_main_section";
       }

       //���̹� �α��� ������ callbackȣ�� �޼ҵ�
       @RequestMapping(value = "navercallback", method = { RequestMethod.GET, RequestMethod.POST })
       public String naverlogincallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
               throws IOException {
           System.out.println("����� callback");
           OAuth2AccessToken oauthToken;
           oauthToken = naverLoginBO.getAccessToken(session, code, state);
           //�α��� ����� ������ �о�´�.
           apiResult = naverLoginBO.getUserProfile(oauthToken);
           System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
           model.addAttribute("result", apiResult);
           System.out.println("result"+apiResult);
           /* ���̹� �α��� ���� ������ View ȣ�� */
//         JSONObject jsonobj = jsonparse.stringToJson(apiResult, "response");
//         String snsId = jsonparse.JsonToString(jsonobj, "id");
//         String name = jsonparse.JsonToString(jsonobj, "name");
   //
//         UserVO vo = new UserVO();
//         vo.setUser_snsId(snsId);
//         vo.setUser_name(name);
   //
//         System.out.println(name);
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
       public String doSessionAssignActionPage(HttpServletRequest request) throws Exception {
    
           String code = request.getParameter("code");
           System.out.println(code);
           
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
           
           System.out.println("����1"+tokens.length);
           System.out.println("����2"+new String(Base64.decodeBase64(tokens[0]), "utf-8"));
           System.out.println("����3"+new String(Base64.decodeBase64(tokens[1]), "utf-8"));
    
           //Jackson�� ����� JSON�� �ڹ� Map �������� ��ȯ
           ObjectMapper mapper = new ObjectMapper();
           Map<String, String> result = mapper.readValue(body, Map.class);
           System.out.println("���� �̸��� : "+result.get("email"));
           System.out.println("���� sub : "+result.get("sub"));
           
           
           return "redirect:/todaylesson";
    
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
             
              System.out.println(set);

              JSONObject result = coolsms.send(set); // ������&���۰���ޱ�

              if ((boolean)result.get("status") == true) {
                // �޽��� ������ ���� �� ���۰�� ���
                System.out.println("����");
                System.out.println(result.get("group_id")); // �׷���̵�
                System.out.println(result.get("result_code")); // ����ڵ�
                System.out.println(result.get("result_message")); // ��� �޽���
                System.out.println(result.get("success_count")); // �޽������̵�
                System.out.println(result.get("�����޼�����: error_count")); // ������ ������ ������ �޽��� ��
              } else {
                // �޽��� ������ ����
                System.out.println("����");
                System.out.println(result.get("code")); // REST API �����ڵ�
                System.out.println(result.get("message")); // �����޽���
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
          return "TodayLesson_UserPage/yi_find_id";
       }
       
       /*id�ߺ� üũ*/
       @ResponseBody 
       @RequestMapping(value="/idCheck", method= RequestMethod.POST)
       public int idCheck(@RequestParam("id") String member_id,Model model)
       {
           System.out.println(member_id);
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
            System.out.println(member_name);
            System.out.println(member_phone);
            map.put("member_name", member_name);
            map.put("member_phone", member_phone);
            String result = loginService.get_searchId(map);
            
            System.out.println(result);

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
     		System.out.println(member_email);
     		model.addAttribute("result",result);
     		return "/TodayLesson_UserPage/hm_us_search_pwd";
       }

}
