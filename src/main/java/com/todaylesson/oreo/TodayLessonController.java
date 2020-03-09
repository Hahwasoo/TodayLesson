package com.todaylesson.oreo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.DTO.Member_AuthDTO;
import com.todaylesson.service.Hm_Us_MailSendService;
import com.todaylesson.service.LoginService;
import com.todaylesson.service.MailSendService;
//MainPage(User, Senior, Admin, Login, Logout , Join, FindId, FindPw) -> ���� Ȩ�� �ִ°͵�
import com.todaylesson.service.TodaylessonService;

@Controller
public class TodayLessonController {
   
   @Resource(name="todaylessonService")
   private TodaylessonService todaylessonService;

   @Resource(name="loginService")
   private LoginService loginService;
   
   @Autowired
   private Hm_Us_MailSendService mailSender;
   
   
   @RequestMapping("/todaylessonadmin")
   public String admin() { 
	   return "TodayLesson_AdminPage/hs_ad_main";
   }
    
   @RequestMapping("/todaylessonsenior")
   public String senior() {
       return "/TodayLesson_SeniorPage/hs_sn_main"; 
    }
       
    @RequestMapping("/todaylessonmember")
    public String member(){
       return "/todaylesson_sec/todaylesson_sec_member";
    }
       
       
       @RequestMapping("/todaylesson")
       public String all(){
          return "/TodayLesson_UserPage/hs_us_main";
       }
       
    /*@RequestMapping("/todaylesson")
    public String all(Locale locale,HttpServletRequest request, HttpServletResponse response, Model model){
       return "/TodayLesson_UserPage/hs_us_main";
    }*/
          
       @RequestMapping("/error")
       public String error()
       {
           return "/todaylesson_sec__error";
       }
              
       @RequestMapping("/customlogin")
       public String login(String error, String logout, Model model)
       { 
    	   
    	  if (error !=null)
             model.addAttribute("error", "Please check your ID or Password");
             
          if(logout !=null)
             model.addAttribute("logout","logout");
          
          
          return "/TodayLesson_UserPage/hs_us_main_sec_login";
       }
       
       /*  ���â �����ߴ���... ���߿� �ٽ� �ϱ�*/
       /*@RequestMapping("/customlogin")
       @ResponseBody
       public String login(String error, String logout,
    		             HttpServletRequest request, Model model)
       { 
    	   
    	  if (error !=null)
             model.addAttribute("error", "Please check your ID or Password");
             
          if(logout !=null)
             model.addAttribute("logout","logout");
          
          
          //return "/TodayLesson_UserPage/hs_us_main_sec_login";
          return "/TodayLesson_UserPage/hs_us_main_sec_logintest";
       }*/
       
       @RequestMapping("/logout")
       public String logout() {
    	   return "redirect:/customlogin";
       }
    		   
    		   
       @RequestMapping("/join")
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
           return "TodayLesson_UserPage/todaylesson_joinform";
       }
       
       @RequestMapping("/joinresult")
		 public String joinresult(@RequestParam("id") String member_id
				 				, @RequestParam("pwd") String member_pwd
				 				, @RequestParam("name") String member_name
				 				, @RequestParam("birth") String member_birth
				 				, @RequestParam("email") String member_email
				 				, @RequestParam("phone") String member_phone
				 				, @RequestParam("zipcode") int member_zipcode
				 				, @RequestParam("nick") String member_nick
				
	 				, @RequestParam("addrselect") int addrselect
	 				,@RequestParam("roadaddr") String roadaddr
	 				,@RequestParam("jibunaddr") String jibunaddr
	 				, @RequestParam("detailaddr") String detailaddr
				 				,  MemberDTO dto,Model model)
		 {

			 
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
			 if(addrselect==0)
					{fulladdr=roadaddr;}
					else
					{fulladdr=jibunaddr;}
			 
			 
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
     		
     		return "/TodayLesson_UserPage/hm_find_pwd";
     	}

     	
     	@RequestMapping(value="/findPassword",method=RequestMethod.POST)
     	@ResponseBody
     	public String findPassword(@RequestParam("inputId_2")String member_id,
                 @RequestParam("inputEmail_2") String member_email
                 ,HttpServletRequest request
                 ,Model model){
     		
     		int result = mailSender.mailSendWithPassword(member_id, member_email, request);
     		System.out.println(member_email);
     		model.addAttribute("result",result);
     		
     		return "/TodayLesson_UserPage/hm_us_search_pwd";
     	}
/*         pwd ã��
         @RequestMapping(value="/searchPassword",method=RequestMethod.POST)
         @ResponseBody
         public String passwordSearch(@RequestParam("inputId_2")String member_id,
               @RequestParam("inputEmail_2") String member_email
               ,HttpServletRequest request) {
            mailSender.mailSendWithPassword(member_id,member_email,request);
            System.out.println(member_email);
            return "/userSearchPassword";
  
         }
         */

}
