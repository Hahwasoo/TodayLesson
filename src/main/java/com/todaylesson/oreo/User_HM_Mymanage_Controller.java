package com.todaylesson.oreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
@RequestMapping("/todaylessonmypage/")
public class User_HM_Mymanage_Controller {

	//��ȣȭ �޼���
	@Resource(name="passwordEncoder")
	private BCryptPasswordEncoder encoder;

	//����
	@Resource(name="hm_us_mymanage")
	private Hm_Us_MymanageService hm_mymanageservice;

	//������Ʈ ��ū
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
	
	

	//�� ��������
	@RequestMapping("/hm_us_mymanage")
	public String hm_us_mymanage1()
	{

		return "/TodayLesson_UserPage/hm_us_mymanage.us_my_section";
	}
	//�� �������� ��й�ȣ ���� �� true�� �� �� ���� ����
	@RequestMapping("/hm_us_mymanage2")
	public String currentUserName(@RequestParam("member_id") String member_id
			,@RequestParam("member_pwd") String member_pwd
			,Model model, HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		
		String new_pwd = member_pwd;
		String old_pwd = hm_mymanageservice.matchpwd(member_id);
		
		System.out.println(new_pwd);
		System.out.println(old_pwd);
		
		boolean result = encoder.matches(new_pwd, old_pwd);
		
		System.out.println(result);

		if(result==true)
		{
			MemberDTO dto = hm_mymanageservice.MyInfolist(member_id);
			model.addAttribute("dto",dto);
			
			String imp_key 		=	"5422837446408379";
			String imp_secret	=	"FhzhNcakGqAxLiWaXndMLWKpsouBVOQB5pTTC3eitOPe6Mp39CPVyAl1YPCUEtwJTpDvsSOWGEaNqzQz";

			JSONObject json = new JSONObject();
			json.put("imp_key", imp_key);
			json.put("imp_secret", imp_secret);
		
			String token = getToken(request, response, json, "https://api.iamport.kr/users/getToken"); 
			model.addAttribute("token",token);

			return "/TodayLesson_UserPage/hm_us_mymanageupdate.us_my_section";
		}
		else {

			return "/TodayLesson_UserPage/hm_us_mymanage.us_my_section";
		}
	}

	//������ ����
	@RequestMapping("/hm_us_mymanagesms")
	public String hmusmymanageupdatesms(Authentication authentication, Model model,HttpServletRequest request)
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
		return "/TodayLesson_UserPage/hm_us_mymanageupdate.us_my_section";
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
			,@RequestParam("member_bank_name") int member_bank_name
			,@RequestParam("member_account_name") String member_account_name
			,@RequestParam("member_account_num") String member_account_num
			,MemberDTO dto, Model model
			)
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
		
		if(member_account_name == null) {
			String fulladdr= "";	
			if(addrselect==0)
			{fulladdr=roadaddr;}
			else
			{fulladdr=jibunaddr;}

			dto.setMember_addr(fulladdr+" "+detailaddr);
			
			int result2 = hm_mymanageservice.MyInfoupdate2(dto);
			model.addAttribute("result",result2);
		}else {
		dto.setMember_bank_name(member_bank_name);
		dto.setMember_account_name(member_account_name);
		dto.setMember_account_num(member_account_num);
		
		String fulladdr= "";	
		if(addrselect==0)
		{fulladdr=roadaddr;}
		else
		{fulladdr=jibunaddr;}

		dto.setMember_addr(fulladdr+" "+detailaddr);

		int result1 = hm_mymanageservice.MyInfoupdate(dto);
		model.addAttribute("result",result1);
		}
		
		return "/TodayLesson_UserPage/hm_us_mymanageupdateresult.us_my_section";
	}
	
	
	
/*	ȸ��Ż�� 
	@RequestMapping("/hm_us_memberwithdraw")
	public String memberwithdraw(Authentication authentication,Model model) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();
		
		String encoded_pwd=encoder.encode(member_pwd);
		boolean result=encoder.matches(member_pwd , encoded_pwd);


		System.out.println(member_pwd);
		System.out.println(result);

		if(result==true)
		{
			MemberDTO dto = hm_mymanageservice.MyInfolist(member_id);
			model.addAttribute("dto",dto);
			
			String imp_key 		=	"5422837446408379";
			String imp_secret	=	"FhzhNcakGqAxLiWaXndMLWKpsouBVOQB5pTTC3eitOPe6Mp39CPVyAl1YPCUEtwJTpDvsSOWGEaNqzQz";

			JSONObject json = new JSONObject();
			json.put("imp_key", imp_key);
			json.put("imp_secret", imp_secret);
		
			String token = getToken(request, response, json, "https://api.iamport.kr/users/getToken"); 
			model.addAttribute("token",token);

			return "/TodayLesson_UserPage/hm_us_mymanageupdate";
		}
		else {

			return "/TodayLesson_UserPage/hm_us_mymanage2";
		}
		
		
		
	}*/

}
