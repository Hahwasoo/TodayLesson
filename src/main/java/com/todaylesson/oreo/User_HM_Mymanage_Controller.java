package com.todaylesson.oreo;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.service.Hm_Us_MymanageService;

@Controller
public class User_HM_Mymanage_Controller {

	//암호화 메서드
	@Resource(name="passwordEncoder")
	private BCryptPasswordEncoder encoder;


	//서비스
	@Resource(name="hm_us_mymanage")
	private Hm_Us_MymanageService hm_mymanageservice;


	//내 정보관리
	@RequestMapping("/hm_us_mymanage")
	public String hm_us_mymanage1()
	{

		return "/TodayLesson_UserPage/hm_us_mymanage";
	}
	//내 정보관리 비밀번호 인증 후 true일 때 내 정보 수정
	@RequestMapping("/hm_us_mymanage2")
	public String currentUserName(@RequestParam("member_id") String member_id
			,@RequestParam("member_pwd") String member_pwd
			,Model model)
	{


		String encoded_pwd=encoder.encode(member_pwd);
		boolean result=encoder.matches(member_pwd , encoded_pwd);


		System.out.println(member_pwd);
		System.out.println(result);

		if(result==true)
		{
			MemberDTO dto = hm_mymanageservice.MyInfolist(member_id);
			model.addAttribute("dto",dto);
			return "/TodayLesson_UserPage/hm_us_mymanageupdate";
		}
		else {

			return "/TodayLesson_UserPage/hm_us_mymanage2";
		}
	}

	//내정보 수정
	@RequestMapping("/hm_us_mymanagesms")
	public String hmusmymanageupdatesms(Authentication authentication, Model model, HttpServletRequest request)throws Exception
	{

		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();
		 /*은지 sms인증*/
        String api_key = "NCSRC0XSPD85BDRL"; //위에서 받은 api key를 추가
        String api_secret = "2LVQYEMQFBIBEG8WVXKQOWQ6KPDDVJQ9";  //위에서 받은 api secret를 추가

        com.todaylesson.Sms.Coolsms coolsms = new com.todaylesson.Sms.Coolsms(api_key, api_secret);
        //이 부분은 홈페이지에서 받은 자바파일을 추가한다음 그 클래스를 import해야 쓸 수 있는 클래스이다.
        

        HashMap<String, String> set = new HashMap<String, String>();
        set.put("from", "01026063254"); // 수신번호

        set.put("to", (String)request.getParameter("to")); // 발신번호, jsp에서 전송한 발신번호를 받아 map에 저장한다.
        set.put("text", (String)request.getParameter("text")); // 문자내용, jsp에서 전송한 문자내용을 받아 map에 저장한다.
        set.put("type", "sms"); // 문자 타입
        String text=(String)request.getParameter("text");
        model.addAttribute("to",(String)request.getParameter("to"));
        model.addAttribute("auth_num",text);
       
        System.out.println(set);

        JSONObject result = coolsms.send(set); // 보내기&전송결과받기

        if ((boolean)result.get("status") == true) {

          // 메시지 보내기 성공 및 전송결과 출력
          System.out.println("성공");
          System.out.println(result.get("group_id")); // 그룹아이디
          System.out.println(result.get("result_code")); // 결과코드
          System.out.println(result.get("result_message")); // 결과 메시지
          System.out.println(result.get("success_count")); // 메시지아이디
          System.out.println(result.get("에러메세지수: error_count")); // 여러개 보낼시 오류난 메시지 수
        } else {

          // 메시지 보내기 실패
          System.out.println("실패");
          System.out.println(result.get("code")); // REST API 에러코드
          System.out.println(result.get("message")); // 에러메시지
        }
		
        MemberDTO dto = hm_mymanageservice.MyInfolist(member_id);
		model.addAttribute("dto",dto);
		return "/TodayLesson_UserPage/hm_us_mymanageupdate";
	}
	
	
	
	
	
	@RequestMapping("/hm_us_mymanageupdate")
	public String hmusmymanageupsms(@RequestParam(value = "member_id") String member_id
			,@RequestParam("member_pwd") String member_pwd
			,@RequestParam("member_name") String member_name
			,@RequestParam("member_birth") String member_birth
			,@RequestParam("member_email") String member_email
			,@RequestParam("member_phone") String member_phone
			,@RequestParam("member_zipcode") int member_zipcode
			,@RequestParam("addrselect") int addrselect
			,@RequestParam("roadaddr") String roadaddr
			,@RequestParam("jibunaddr") String jibunaddr
			,@RequestParam("detailaddr") String detailaddr
			,@RequestParam("member_nick") String member_nick
			,MemberDTO dto, Model model)
	{

		member_pwd=encoder.encode(member_pwd);
		dto.setMember_id(member_id);
		dto.setMember_pwd(member_pwd);
		dto.setMember_name(member_name);
		dto.setMember_birth(member_birth);
		dto.setMember_email(member_email);
		dto.setMember_phone(member_phone);
		dto.setMember_zipcode(member_zipcode);
		dto.setMember_nick(member_nick);

		String fulladdr= "";	
		if(addrselect==0)
		{fulladdr=roadaddr;}
		else
		{fulladdr=jibunaddr;}

		dto.setMember_addr(fulladdr+" "+detailaddr);

		int result1 = hm_mymanageservice.MyInfoupdate(dto);
		model.addAttribute("result",result1);

		return "/TodayLesson_UserPage/hm_us_mymanageupdateresult";
	}

}
