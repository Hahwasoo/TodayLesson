package com.todaylesson.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;

@Service(value="kakaologinservice")
public class User_HS_KakaoLoginService {
	
	private final static String K_CLIENT_ID = "250c99d78fb89584382252365272f520"; // REDIRECT_URI 
	private final static String K_REDIRECT_URI = "http://localhost:9080/todaylessonkakaologin"; 
	
	public static String getAuthorizationUrl(HttpSession session) {
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + K_CLIENT_ID + 
				           "&redirect_uri=" + K_REDIRECT_URI + "&response_type=code"; 
		return kakaoUrl; 
	}

	public static JsonNode getAccessToken(String autorize_code) {
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", "250c99d78fb89584382252365272f520")); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:9080/todaylessonkakaologin")); // �����̷�Ʈ URI
		postParams.add(new BasicNameValuePair("code", autorize_code)); // �α��� ������ ���� code ��

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;

		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();  //����ڵ�

			//System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			//System.out.println("Post parameters : " + postParams);
			//System.out.println("Response Code : " + responseCode);

			// JSON ���� ��ȯ�� ó��
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		return returnNode;

	}

	public static JsonNode getKakaoUserInfo(JsonNode autorize_code) {

		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + autorize_code);

		JsonNode returnNode = null;

		try {
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode(); //����ڵ�
			final String responseMSG = response.getStatusLine().getReasonPhrase(); //��� �޼���

			//System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			//System.out.println("Response Code : " + responseCode);
			//System.out.println("Response MSG : " + responseMSG);
			
			//�� �ּ�ó�� �Ǿ��ִ� �ڵ� = ������ ���� ��Ȳ�̶�� ���ְ� �����ϱ� ������������ �˷��ش�
			/*HttpEntity entity = response.getEntity();  
            String responseString = EntityUtils.toString(entity, "UTF-8");
            Logger.getLogger("responseString----->"+responseString);*/
            
			

			// JSON ���� ��ȯ�� ó��
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;

	}
}
