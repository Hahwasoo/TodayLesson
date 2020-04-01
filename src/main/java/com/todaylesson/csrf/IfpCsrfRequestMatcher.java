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
        else if ("/todaylesson/freeboard".equals(strUri))                   return false;
      //�Խñۻ󼼺���
        else if ("/todaylesson/freeboard_detail/{freeboard_no}".equals(strUri))                   return false;   
      //���ôޱ� json
        else if ("/todaylesson/freeboard_detailjson/{freeboard_no}".equals(strUri))    return false;
       //���ôޱ� 
        else if ("/todaylesson/insert_boardreply/".equals(strUri))    return false;
        //�����Խ��� �۾���
        else if ("/todaylesson/freboard_insert/".equals(strUri))    return false;
        //�����Խ��� �� �����ϱ�
        else if ("/todaylesson/freeboard_modify/{freeboard_no}".equals(strUri))   return false;
        
   //����
      //���̺��ã�� 
        else if ("/findPw".equals(strUri))                   
            return false; 
      //���� �̸��� & ���̵� �� �޾Ƽ� �ӽ� ��й�ȣ ����
        else if("/findPassword".equals(strUri))
             return false;
     //���� �� �������� �������� ����-�ʿ��
        else if("/todaylessonmypage/hm_us_mymanage".equals(strUri))
             return false;
     //���� �� ���� ���� 1�� ��й�ȣ �����ޱ�
        else if("/todaylessonmypage/hm_us_mymanage2".equals(strUri))
             return false; 
     //���� �� ���� ���� �Ϸ� �� �� ���� ������
        else if("/todaylessonmypage/hm_us_mymanageupdate".equals(strUri))
           return false;
        //���� ������ ȸ������ ������
        else if("/todaylessonadmin/admin_hm_memmanage".equals(strUri))
           return false;
        //���� ������ ���� ���� ������
        else if("/todaylessonadmin/adminmember_levelupdate".equals(strUri))
           return false;
        //���� ������ ȸ�� ������
        else if("/todaylessonadmin/hm_memmanagedetail".equals(strUri))
           return false;
        //���� ����� 1:1���� �������� �̵�-�ʿ��
        else if("/todaylessonmypage/hm_us_question".equals(strUri))
           return false;
        //���� ����� 1:1���� ��� �������� �̵�-�ʿ��
        else if("/todaylessonmypage/hm_question_insert".equals(strUri))
           return false;
        //���� 1:1���� ���
        else if("/todaylessonmypage/hm_question_create".equals(strUri))
         return false;
        //���� ������ 1:1���� ���������� �̵� -�ʿ��
        else if("/todaylessonmyadmin/todaylessonadmin/hm_ad_question".equals(strUri))
           return false;
        //���� ȸ���������� updatesms
        else if("/todaylessonmypage/hm_us_mymanageupdatesms".equals(strUri))
           return false;
        //���� �̺�Ʈ �������� �̵�-�ʿ��
        else if("/todaylessonadmin/hm_ad_event_manage".equals(strUri))
           return false;
        //���� �̺�Ʈ insert
        else if("/todaylessonadmin/hm_ad_event_insert".equals(strUri))
           return false;
        //���� �̺�Ʈ insertresult�̵� - �̰� �ʿ���µ�... ���߿� �� ������
        else if("/todaylessonadmin/hm_ad_event_insertresult".equals(strUri))
        	return false;
        //���� �̺�Ʈ ������
        else if("/todaylessonadmin/hm_ad_event_modify".equals(strUri))
        	return false;
        //���� ����� �̺�Ʈ
        else if("/todaylesson/hm_us_event".equals(strUri))
        	return false;
        //���� ����� 1:1���� ������ ������ �̵�
      /*  else if("/todaylessonadmin/hm_us_question_detail/{no}".equals(strUri))
           return false;*/
        //���� ������ 1:1���� ������ ������ �̵�
        /*else if("/todaylessonadmin/hm_ad_question_detail".equals(strUri))
           return false;*/

        //���� ������ 1:1����  ������ �亯�ޱ�
       /* else if("/todaylessonadmin/hm_ad_question_update".equals(strUri))
           return false;*/

   //����
        //���� ������ ��ǰ��ȸ
        else if ("/todaylessonadmin/ej_ad_product_productlist".equals(strUri))                   
           return false; 
        //���� ������ ��ǰ������
        else if ("/todaylessonadmin/ej_ad_product_productdetail".equals(strUri))                   
              return false; 
        //���� ������ ��ǰ���
        else if ("/todaylessonadmin/ej_ad_product_productregister".equals(strUri))                   
           return false; 
        //���� ������ ��ǰ��Ͻ� ������ â(�߰�����/����)
        else if ("/todaylessonadmin/ej_ad_product_insertresult".equals(strUri))                   
            return false; 
        //���� ������ ��ǰ ����
        else if("/todaylessonadmin/ad_product_update".equals(strUri))
            return false;
      //���� ������ ��ǰ �������
        else if("/todaylessonadmin/ad_product_update_result".equals(strUri))
            return false;
        //���� ������ ��ǰ ����
        else if("/todaylessonadmin/ad_product_delete".equals(strUri))
            return false;
       //�ı⸮��Ʈ 
        else if("/todaylessonadmin/ad_product_reviewlist".equals(strUri))
            return false;
        //�ı������
        else if("/todaylessonadmin/ad_product_reviewdetail".equals(strUri))
            return false;
        //�ı� ����
        else if("/todaylessonadmin/ad_product_reviewdelete".equals(strUri))
            return false;
        
        
        //����� ��������
        //���� ����� ������
        else if ("/todaylesson/ej_store_detail".equals(strUri))                   
            return false; 
        //��������� ����
        else if ("/todaylesson/ej_store_main".equals(strUri))                   
            return false; 
        //���� �ֹ���
        else if ("/todaylesson/ej_us_orderform".equals(strUri))                   
           return false; 
      //���� �ֹ�������
        else if ("/todaylesson/orderlistdetail".equals(strUri))                   
           return false; 
        
        //���������� 
      //���� ���ƿ�
        else if ("/todaylessonmypage/mylike".equals(strUri))                   
           return false; 
      //���� ��ٱ���
        else if ("/todaylessonmypage/mycart".equals(strUri))                   
           return false; 
        
      //���� ��ٱ��Ͽ��� �ֹ��Ҷ�
        else if ("/todaylessonmypage/order_cart".equals(strUri))                   
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
        else if ("/todaylessonsenior/insert_result".equals(strUri))    
              return false;  
        
        //���� ������
        /*else if (strUri.equals("/lesson_detail/{member_id}"))    
              return false;*/
        
      //������ ������ ����
        else if ("/multiple_time_lesson_date".equals(strUri))    
              return false; 
        
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
        else if ("/todaylessonsenior/you_are_senior".equals(strUri))    
              return false;
        
        // �ôϾ� ������ �� ���� �Ѿ�� ��
        else if ("/todaylessonsenior/plus_senior".equals(strUri))    
              return false;
        
        // ���� �ۼ� �Ϸ�
        else if ("/todaylessonsenior/insert_result".equals(strUri))    
              return false;
       
        // ��ü ���� ��ȸ
        else if ("/todaylessonadmin/alllesson".equals(strUri))    
              return false; 
        
        // ��û �Ϸῡ�� �ɻ������� �ѱ�� ������
        else if ("/todaylessonadmin/apply_lesson".equals(strUri))    
              return false; 
        
        // ������ �ɻ������� �ѱ��
        else if ("/todaylessonadmin/admin_apply_exam".equals(strUri))    
              return false;
        
        // �ɻ� �ʿ��� ���� ��ȸ
        else if ("/todaylessonadmin/wait_lesson".equals(strUri))    
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
        else if ("/todaylessonadmin/all_senior".equals(strUri))    
              return false; 
       
        //���� ����
        /* else if ("/lesson_update/{lesson_no}".equals(strUri))    
          return false;*/
          
        //���� ������Ʈ ���
        else if ("/todaylessonsenior/lesson_update_result".equals(strUri))    
              return false; 
        
        //���� ����
        /* else if ("/lesson_delete/{lesson_no}".equals(strUri))    
          return false;*/
        
        //���� ���� ���
        else if ("/todaylessonsenior/lesson_delete_result".equals(strUri))    
              return false; 
        
        //���� ������ �߰� ���
        else if ("/todaylessonsenior/mal_lesson_upload_result".equals(strUri))    
              return false; 
        
        //���� ������ ���� ���
        else if ("/todaylessonsenior/mal_lesson_update_result".equals(strUri))    
       	 	return false; 

        else if ("/todaylessonsenior/update_senior_result".equals(strUri))    
       	 	return false; 
        
        //���� �Ϸ�
        else if("/orderlist_detail".equals(strUri))
        	return false;
        
        else if("/update_lesson_comp".equals(strUri))
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