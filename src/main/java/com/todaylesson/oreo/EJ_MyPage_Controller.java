package com.todaylesson.oreo;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todaylesson.DTO.CartDTO;
import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.DTO.MyLikeDTO;
import com.todaylesson.DTO.OrderDetailDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.ProductDTO;
import com.todaylesson.service.EJ_All_Product_Service;

@RequestMapping("/todaylessonmypage/")
@Controller
public class EJ_MyPage_Controller {
	
	@Resource(name="us_store_service")
	private EJ_All_Product_Service service;
	
	@RequestMapping("/mylike/{member_id}")
	public String mylike(@PathVariable(value="member_id") String member_id
			,@RequestParam(required=false) String category
			,Model model)
	{
		List<MyLikeDTO> likedto=service.selectMyLike(member_id,category);
		model.addAttribute("list",likedto);
		return "TodayLesson_UserPage/ej_us_mylike.us_my_section";
	}

	@RequestMapping("/mycart/{member_id}")
	public String mycart(@PathVariable(value="member_id") String member_id,Model model)
	{
		List<CartDTO> cartdto=service.selectMyCart(member_id);
		model.addAttribute("list",cartdto);
		model.addAttribute("memberid",member_id);
		MemberDTO mdto = service.selectm(member_id);
		model.addAttribute("mdto",mdto);
		
		return "TodayLesson_UserPage/ej_us_mycart.us_my_section";
	}
	
	@ResponseBody
	@RequestMapping("/deletecart_json")
	public int  deletecart(@RequestParam(value="product_no")int product_no
			,@RequestParam(value="lesson_no")int lesson_no
			,@RequestParam(value="member_id")String member_id)
	{
		System.out.println("deletecart_prono:"+product_no);
		System.out.println("deletecart_lesson no:"+lesson_no);
		
		CartDTO cartdto=new CartDTO();
		cartdto.setMember_id(member_id);
		
		int result=0;
		
		if(lesson_no==0) {//��ǰ����
			cartdto.setProduct_no(product_no);
		 result=service.deletecart(cartdto);
		 }
		else if(product_no==0) {//��������
			cartdto.setLesson_no(lesson_no);
			result=service.deletecart_lesson(cartdto);
		}
				return result;
	}
	
	@RequestMapping("/order_cart")
	public String  order_cart(OrderListDTO oldto
			
			, @RequestParam(value="addrselect") int addrselect
			,@RequestParam(value="roadaddr",required=false) String roadaddr
			,@RequestParam(value="jibunaddr",required=false) String jibunaddr
			, @RequestParam(value="detailaddr",required=false) String detailaddr
			
			,@RequestParam(value="orderlist_cost", required=false) int orderlist_cost
			,@RequestParam(value="orderlist_usepoint", required=false) int orderlist_usepoint
			,@RequestParam(value="remainpoint", required=false) int remainpoint
			
			,@RequestParam(value="member_id") String member_id
			, Model model)
	{
		
		System.out.println("addrselect"+addrselect);
		 
	if(addrselect>0)
		{
		 String fulladdr= "";	
		 if(addrselect==1) 
				{fulladdr=roadaddr;}
				else if(addrselect==2)
				{fulladdr=jibunaddr;}
		 oldto.setOrderlist_addr(fulladdr+" "+detailaddr);
		}//�ּ�
	
	
	MemberDTO memberdto=new MemberDTO();
		int updatedpoint=(int) (remainpoint+(0.1*orderlist_cost));
		//remainpoint�� ����Ʈ ����� ���� �ݾ�
		//���� �ݾ׿� �����ݾ��� 10%�� �����ؼ� update����
		memberdto.setMember_id(member_id);
		memberdto.setMember_point(updatedpoint);
		service.updatepoint(memberdto);
	
	
	//�ֹ���ȣ ����
		 Calendar cal = Calendar.getInstance();
		 int year1 = cal.get(Calendar.YEAR);
		 String year2=Integer.toString(year1);
		 String year=year2.substring(2, 4);
		 
		 System.out.println("���ڸ����⵵:"+year);
		 String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		 System.out.println();
		 System.out.println(ym);
		 String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		 String subNum = "";
		 
		 for(int i = 1; i <= 4; i ++) {
		  subNum += (int)(Math.random() * 10);
		 }
		 
		 String orderId =ymd+subNum;
		 System.out.println("�������̵�:"+orderId);
		 System.out.println("������Ÿ��Ȯ��:"+orderId instanceof String);
		int orderlist_no=Integer.parseInt(orderId);
			System.out.println("orderlist_NO:"+orderlist_no);
		oldto.setOrderlist_no(orderlist_no); //�ֹ���ȣ����
		service.insertorderlist(oldto);//�ֹ����� ���
		
		
		OrderDetailDTO oddto=new OrderDetailDTO();//�ֹ������� ��ü ����
		List<CartDTO> cartdto=service.selectMyCart(member_id);
		//cart���̺� �մ°� �� �޾ƿ���
		
	
		for(CartDTO cart: cartdto)
		{
		service.insertorder_cart(cart, orderlist_no);
		//�޾ƿ� ����Ʈ�� order_detail���̺� insert�ϱ�
		System.out.println("cart��ü"+cart);
		//������ ��ǰ �� ��ǰ stock  update�ϱ�
		//Product���̺� stock update  ..pdcount�޾ƿ;���
		ProductDTO productdto=new ProductDTO();
	    int product_no=cart.getProduct_no();
	   // int lesson_no=cart.getLesson_no();
		int orderamount=cart.getCart_amount();
		
		int oldstock=service.selectstock(product_no);//���� ��� �޾ƿ���...�������ؾߵǴµ� ��
		int newstock=oldstock-orderamount;
		
		productdto.setProduct_no(product_no);
		productdto.setProduct_stock(newstock);

		service.updatestock(productdto);
		/*System.out.println("pdcount"+order_count);
		
		int newstock=oldstock-order_count;
		System.out.println("newstock"+newstock);
		productdto.setProduct_no(product_no);
		productdto.setProduct_stock(newstock);*/
		}
		
		
		
		
		
		service.delet_all_cart(member_id);//īƮ�� ��� �ִ°� �� delete
	
		OrderListDTO orderlistdto=service.selectorderlist(orderlist_no);//�������� �޾ƿ���
		List<OrderDetailDTO> list=service.selectorderdetail(orderlist_no);//���� ������ ���� �޾ƿ���
		model.addAttribute("list",list);
		model.addAttribute("orderlistdto",orderlistdto);
		
		return "TodayLesson_UserPage/ej_us_ordercart.us_my_section";
	}
	
	

}
