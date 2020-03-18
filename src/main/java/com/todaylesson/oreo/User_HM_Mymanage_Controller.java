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
/*@RequestMapping("/todaylessonuser/")*/
public class User_HM_Mymanage_Controller {

	//��ȣȭ �޼���
	@Resource(name="passwordEncoder")
	private BCryptPasswordEncoder encoder;


	//����
	@Resource(name="hm_us_mymanage")
	private Hm_Us_MymanageService hm_mymanageservice;


	//�� ��������
	@RequestMapping("/hm_us_mymanage")
	public String hm_us_mymanage1()
	{

		return "/TodayLesson_UserPage/hm_us_mymanage";
	}
	//�� �������� ��й�ȣ ���� �� true�� �� �� ���� ����
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

	//������ ����
	@RequestMapping("/hm_us_mymanagesms")
	public String hmusmymanageupdatesms(Authentication authentication, Model model, HttpServletRequest request)throws Exception
	{

		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();
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
