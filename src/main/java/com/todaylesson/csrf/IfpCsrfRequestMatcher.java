package com.todaylesson.csrf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

import com.todaylesson.DTO.MemberDTO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//�����߰��Ѱ�
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
/**

 * IfpCsrfRequestMatcher.java

 * - ��û�� ���� ������ ��ť��Ƽ���� CSRF�� �����Ұ��� �ƴҰ��� �Ǵ�

 * 

 * ################ Spring Security CSRF Protection ���� ##################

 * [CsrfTokenAdder.java, ��������] ###"���信 �±� ����"###

 * - Spring Security4 �̻��� CSRF Enabled�� �⺻����

 * - HttpServletRequest(�Ϲ�  JSP��û), XMLHttpRequest(AJAX) ��û�̵� ���信 Csrf �����ױ� ����

 * - CSRF Protection�� ���� Text/Html ���信 �����±� Xsrf Token ����

 * - AJAX JSON����(�޺� ä����)�� �����±� Csrf Token ������ �ʿ����.

 * 

 * [security-config.xaml]

 * - �ִ��� Spring Security ������ �ʿ���� �κп� ���� ����(/resources, /images ��)

 * - RequestMatcher�� ���� ����(������ ��ť��Ƽ���� ��û�� ���� CSRF�� �����Ұ��� �ƴҰ��� �Ǵ�)

 * - AccessDeniedHandler�� ���� ����(403 �����߻��� ����)

 * 

 * [IfpCsrfRequestMatcher.java] ###"��û�� ���� CSRF ���뿩�� ����"###

 * - ������ ��ť��Ƽ���� CSRF�� �����Ұ��� �ƴҰ��� �Ǵ�

 * - AJAX CALL, ùȭ��, �����޴��ε�, �˾��ε� ��û�� CSRF �������� ����

 * - (TEXT/HTML�� �������� ������ �κ��� CsrfTokenAdder ���Ϳ��� Csrf �����±� ������)

 * - 403 Forbidden ������ �߻��ϴ� �κ��� �ִٸ� �̰����� �߰��� ��

 * 

 * [IfpAccessDeniedHandler.java]

 * - 403 Forbidden ���� �߻��ϴ� ��� ó���ϴ� �ڵ鷯

 * ######################################################################


 */

@Component  //���� �߰��Ѱ�
public class IfpCsrfRequestMatcher implements RequestMatcher {
   

  final Log logger = LogFactory.getLog( IfpAccessDeniedHandler.class );
  
      private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$"); //���� �߰��Ѱ�

      private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/unprotected", null); //���� �߰��Ѱ�

   

    @Override

    public boolean matches(HttpServletRequest request) {

       

       String strUrl = request.getRequestURL().toString();

       String strUri = request.getRequestURI();

       String queryString = request.getQueryString() == null ? "" : request.getQueryString();

       String contentType = request.getContentType() == null ? "" : request.getContentType();
       
 

       //---------------------------------------------------------

       // CSRF ���͸�, �ʿ�� �߰� �ϼ���. ���⿡ �߰����Ͻø� 403 ���� �߻�!

       //---------------------------------------------------------

       // AJAX CALL

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")))                                                      

                                                      return false;
  //ȭ��
     // ùȭ��
        else if ("/".equals(strUri))                   return false;        
     // ùȭ��
        else if ("/todaylesson".equals(strUri))                   return false;
     //������ȭ��
        else if ("/todaylessonadmin".equals(strUri))                   return false;  
     //�ôϾ�ȭ��
        else if ("/todaylessonsenior".equals(strUri))                   return false;   
     //����������
        else if ("/todaylessonmypage".equals(strUri))                   return false; 
     //�α���
        else if ("/todaylessonlogin".equals(strUri))                   return false;
     //īī���α���
        else if ("/kakaologinurl".equals(strUri))                   return false;
        else if ("/todaylessonkakaologin".equals(strUri))                   return false;
     //�α׾ƿ�
        else if ("/logout".equals(strUri))                   return false;
        
      
 
        
        
  //����
      //TodayLessonController
      //ȸ������
        else if ("/join".equals(strUri))                   return false;
      //���̵�ã��
        else if ("/findId".equals(strUri))                   return false;
      //ȸ�����԰��
        /*else if ("/joinresult".equals(strUri))                   return false;*/
      //idã��
        else if ("/userSearch".equals(strUri))                   return false;
      //User_YI_FreeBoard_Controller
      //�����Խ���
        else if ("/freeboard".equals(strUri))                   return false;
      //�Խñۻ󼼺���
        else if ("/freeboard_detail/{freeboard_no}".equals(strUri))                   return false;   
      //���ôޱ� json
        else if ("/freeboard_detailjson/{freeboard_no}".equals(strUri))    return false;
       //���ôޱ� 
        else if ("/insert_boardreply/".equals(strUri))    return false;
        //�����Խ��� �۾���
        else if ("/freboard_insert/".equals(strUri))    return false;
        //�����Խ��� �� �����ϱ�
        else if ("/freeboard_modify/{freeboard_no}".equals(strUri))   return false;
        
   //����
      //���̺��ã�� 
        else if ("/findPw".equals(strUri))                   
            return false; 
      //���� �̸��� & ���̵� �� �޾Ƽ� �ӽ� ��й�ȣ ����
        else if("/findPassword".equals(strUri))
             return false;
     //���� �� �������� �������� ����
        else if("/hm_us_mymanage".equals(strUri))
             return false;
     //���� �� ���� ���� 1�� ��й�ȣ �����ޱ�
        else if("/hm_us_mymanage2".equals(strUri))
             return false; 
     //���� �� ���� ���� �Ϸ� �� �� ���� ������
        else if("/hm_us_mymanageupdate".equals(strUri))
           return false;
        //���� ������ ȸ������ ������
        else if("/admin_hm_memmanage".equals(strUri))
           return false;
        //���� ������ ���� ���� ������
        else if("/adminmember_levelupdate".equals(strUri))
           return false;
        //���� ������ ȸ�� ������
        else if("/hm_memmanagedetail".equals(strUri))
           return false;
        //���� ����� 1:1���� �������� �̵�
        else if("/hm_us_question".equals(strUri))
           return false;
        //���� ����� 1:1���� ��� �������� �̵�
        else if("/hm_question_insert".equals(strUri))
           return false;
        //���� 1:1���� ���
        else if("/hm_question_create".equals(strUri))
         return false;
        //���� ������ 1:1���� ���������� �̵�
        else if("/hm_ad_question".equals(strUri))
           return false;
        else if("/hm_us_mymanageupdatesms".equals(strUri))
           return false;
        else if("/hm_ad_event_manage".equals(strUri))
           return false;
        else if("/hm_ad_event_insert".equals(strUri))
           return false;
        else if("/hm_ad_event_insertresult".equals(strUri))
        	return false;
        //���� ����� 1:1���� ������ ������ �̵�
      /*  else if("/hm_us_question_detail/{no}".equals(strUri))
           return false;*/
        //���� ������ 1:1���� ������ ������ �̵�
        /*else if("hm_ad_question_detail".equals(strUri))
           return false;*/

        //���� ������ 1:1����  ������ �亯�ޱ�
       /* else if("hm_ad_question_update".equals(strUri))
           return false;*/

   //����
        //���� ������ ��ǰ��ȸ
        else if ("/ej_ad_product_productlist".equals(strUri))                   
           return false; 
        //���� ������ ��ǰ������
        else if ("/ej_ad_product_productdetail".equals(strUri))                   
              return false; 
        //���� ������ ��ǰ���
        else if ("/ej_ad_product_productregister".equals(strUri))                   
           return false; 
        //���� ������ ��ǰ��Ͻ� ������ â(�߰�����/����)
        else if ("/ej_ad_product_insertresult".equals(strUri))                   
            return false; 
        //���� ����� ������
        else if ("/ej_store_detail".equals(strUri))                   
            return false; 
        //��������� ����
        else if ("/ej_store_main".equals(strUri))                   
            return false; 
        //���� �ֹ���
        else if ("/ej_us_orderform".equals(strUri))                   
           return false; 
      //���� �ֹ�������
        else if ("/orderlistdetail".equals(strUri))                   
           return false; 
               
        	
        //�ɼ��߰�������..�ȵ�..                           
        else if ("/ad_add_pdOption".equals(strUri))                   
           return false; 
        else if ("/ad_add_pdOption/insertOption_json".equals(strUri))                   
        	 return false; 

   //����
     
        //�����ּ��˾�
        else if ("/jusoPopup".equals(strUri))    
            return false;   
     
        //���� ���� ����Ʈ 
        /*else if (strUri.equals("/lesson_list/{member_id}"))    
             return false; */
        
        //���� �ۼ�
        /*else if (strUri.equals("/lesson_write/{member_id}"))    
              return false;*/
        
        //���� insert���
        else if ("/insert_result".equals(strUri))    
              return false;  
        
        //���� ������
        /*else if (strUri.equals("/lesson_detail/{member_id}"))    
              return false;*/
        
        
        
        //�ôϾ� ���� ��ư
        else if ("/senior_request".equals(strUri))    
              return false;
        
        //�ôϾ� ���� �˾�
        /*else if (strUri.equals("senior_request_form/{member_id}"))    
              return false; */
        
        //�ôϾ� ���� ��(�ôϾ� ��, �̷��� ���°�)
        /* else if ("/senior_switch/{member_id}".equals(strUri))    
              return false;*/
        
        // �̹� �ôϾ��� ��
        else if ("/you_are_senior".equals(strUri))    
              return false;
        
        // �ôϾ� ������ �� ���� �Ѿ�� ��
        else if ("/plus_senior".equals(strUri))    
              return false;
        
        // ���� �ۼ� �Ϸ�
        else if ("/insert_result".equals(strUri))    
              return false;
       
        // ��ü ���� ��ȸ
        else if ("/alllesson".equals(strUri))    
              return false; 
        
        // ��û �Ϸῡ�� �ɻ������� �ѱ�� ������
        else if ("/apply_list".equals(strUri))    
              return false; 
        
        // ������ �ɻ������� �ѱ��
        else if ("/admin_apply_exam".equals(strUri))    
              return false;
        
        // �ɻ� �ʿ��� ���� ��ȸ
        else if ("/wait_lesson".equals(strUri))    
              return false; 
        
        // ����������ϴ� ���� ������
        /* else if ("/admin_wait_lesson_detail/{lesson_no}".equals(strUri))    
          return false;*/
        
        // ���� ������
        /* else if ("/admin_lesson_detail/{lesson_no}".equals(strUri))    
          return false;*/
        
        // ���� �ɻ� �˾�
        /* else if ("/lesson_result_update/{lesson_no}".equals(strUri))    
          return false;*/
        
        // ���� ����
        /* else if ("/lesson_approve/{lesson_no}".equals(strUri))    
          return false;*/
        
        // ���� ����
        /* else if ("/lesson_reject/{lesson_no}".equals(strUri))    
          return false;*/
        
        // ������ �ôϾ� ����
        else if ("/all_senior".equals(strUri))    
              return false; 
       
        //���� ����
        /* else if ("/lesson_update/{lesson_no}".equals(strUri))    
          return false;*/
          
        //���� ������Ʈ ���
        else if ("/lesson_update_result".equals(strUri))    
              return false; 
        
        //���� ����
        /* else if ("/lesson_delete/{lesson_no}".equals(strUri))    
          return false;*/
        
        //���� ���� ���
        else if ("/lesson_delete_result".equals(strUri))    
              return false; 
        
        //���� ������ �߰� ���
        else if ("/mal_lesson_upload_result".equals(strUri))    
              return false; 
        
        //���� ������ ���� ���
        else if ("/mal_lesson_update_result".equals(strUri))    
       	 	return false; 

        else if ("/update_senior_result".equals(strUri))    
       	 	return false; 
        
        
       else if(allowedMethods.matcher(request.getMethod()).matches()){  //���� �߰��Ѱ�

            return false;   //���� �߰��Ѱ�

        }
        
        
        

        else {

           logger.info("###################################### request.getRequestURL() :: " + strUrl);

           logger.info("###################################### request.getRequestURI() :: " + strUri);

           logger.info("###################################### request.getQueryString() :: " + queryString);

           logger.info("###################################### request.getContentType() :: " + contentType);           

        }

       // return true;
        
        //��ť��Ƽ ���� ������ ���� ������ �ذ���(�����۱��� ó���Ǵ����� ���� Ȯ�� ����.. �ΰ� ��ģ�Ŷ� ���ε��� �и��ؼ� Ȯ���ʿ���)
        return !unprotectedMatcher.matches(request);   //���� �߰��Ѱ�

    }
}