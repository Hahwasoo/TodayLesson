package com.todaylesson.oreo;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.todaylesson.service.EJ_All_Product_Service;
import com.todaylesson.upload.UploadFileUtils;
import com.todaylesson.DTO.CartDTO;
import com.todaylesson.DTO.EventDTO;
import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.DTO.MyLikeDTO;
import com.todaylesson.DTO.PdReviewDTO;
import com.todaylesson.DTO.OptionsDTO;
import com.todaylesson.DTO.OrderDetailDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.ProductDTO;


@Controller
@RequestMapping("/todaylesson/")
public class EJ_User_Store_Controller {

	
	@Resource(name="us_store_service")
	private EJ_All_Product_Service service;
	
	
	
	//����� ����
	@RequestMapping("/ej_store_main/{product_category}")
	public String slist(@PathVariable("product_category") int product_category
			,@RequestParam(required=false, defaultValue="recent") String order
			,Model model) {
		System.out.println("product_category"+product_category);
		
		if(product_category==0)
		{
			List<ProductDTO> list = service.selectAll(order);
			model.addAttribute("list",list);
		
		}
		else {
				List<ProductDTO> list = service.selectcategory(product_category);
				model.addAttribute("list",list);
		}
		
	
		return "TodayLesson_UserPage/ej_store_main.us_main_section";
	}
	
	
	//��ǰ ������
	@RequestMapping("/ej_store_detail/{product_no}")
	public String sdetail(@PathVariable("product_no") int product_no
			,@RequestParam(required=false, defaultValue="recent") String order
			, Model model) {
		
		List<ProductDTO> list = service.selectAll(order);
		model.addAttribute("list",list);
		
		List<PdReviewDTO> reply = service.replyList(product_no);
		 System.out.println("reply object:"+reply);
		 model.addAttribute("reply",reply);
			ProductDTO dto = service.select(product_no);
			model.addAttribute("dto",dto);
			
		
	
		return "TodayLesson_UserPage/ej_store_detail.us_main_section";
	}
	
	
	
	
	
	// ��ǰ ����(���) �ۼ� registReply
	@ResponseBody
	@RequestMapping(value = "/ej_store_detail/registReply", method = RequestMethod.POST)
	public void registReply (/*PdReviewDTO reply*/
			@RequestParam String member_id,
			@RequestParam String pdreview_title ,
			@RequestParam String pdreview_content ,
			@RequestParam int product_no,
			HttpSession session) throws Exception {
	
	 
		PdReviewDTO pdreviewdto=new PdReviewDTO();
		pdreviewdto.setMember_id(member_id);
		pdreviewdto.setProduct_no(product_no);
		pdreviewdto.setPdreview_title(pdreview_title);
		pdreviewdto.setPdreview_content(pdreview_content);
		
		int result=service.registReply(pdreviewdto);
	 System.out.println("registReply Controller");

}
	
	
	
	
	
	//���ƿ�
	@ResponseBody
	@RequestMapping("/likejson")
	public String likemain(@RequestParam(value="product_no") int product_no
			,@RequestParam(value="member_id") String member_id)
	{
		System.out.println("productno:"+product_no+member_id);
		MyLikeDTO likedto=new MyLikeDTO();
		likedto.setMember_id(member_id);
		likedto.setProduct_no(product_no);
	
		String result;
		
		List<MyLikeDTO> product_in_mylike = service.has_mylike_product(likedto);
		service.updateproductlike(product_no);
		
		if (product_in_mylike.isEmpty()) {
			service.insertmylike(likedto);
			result="success"; 
		} else {
			result="false";
		}
		return result;
		
	}
	//��ٱ���
	@ResponseBody
	@RequestMapping("/cartjson")
	public String cartmain(@RequestParam(value="product_no") int product_no
			,@RequestParam(value="member_id") String member_id)
	{
		System.out.println("cart��");
		System.out.println("productno:"+product_no+member_id);
		CartDTO cartdto=new CartDTO();
		cartdto.setMember_id(member_id);
		cartdto.setProduct_no(product_no);
		
		String result;
		List<CartDTO> product_in_cart = service.has_cart_product(cartdto);
		
		if (product_in_cart.isEmpty()) {
			cartdto.setCart_amount(1);
			service.insertcart(cartdto);
			result="success"; 
		} else {
			result="false";
		}
		return result;
	}
	
	//�����Ͽ��� ���� ���������� ��ٱ���
		@ResponseBody
		@RequestMapping("/cartwith_amount_json")
		public String cartwith(@RequestParam(value="product_no") int product_no
				,@RequestParam(value="member_id") String member_id
				,@RequestParam(value="cart_amount") int cart_amount)
		{
			
			CartDTO cartdto=new CartDTO();
			cartdto.setMember_id(member_id);
			cartdto.setProduct_no(product_no);
		
		

			String result;
			List<CartDTO> product_in_cart = service.has_cart_product(cartdto);
			
			if (product_in_cart.isEmpty()) {
				cartdto.setCart_amount(cart_amount);
				service.insertcart(cartdto);
				result="success"; 
			} else {
				result="false";
			}
			return result;
		}
	
	//�ֹ����
	@RequestMapping("/ej_us_orderform")
	public String orderform(@RequestParam("product_no") int product_no
			,@RequestParam("pdcount") int pdcount
			,@RequestParam("product_name") String product_name
			,@RequestParam("product_after_cost") int product_after_cost
			,@RequestParam("member_id") String member_id
			,Model model){
		System.out.println("�ֹ����������� ��ǳ��ȣ:"+product_no);
		System.out.println("�ֹ����������� ����:"+pdcount);
		model.addAttribute("product_no",product_no);
		model.addAttribute("product_name",product_name);
		model.addAttribute("product_after_cost",product_after_cost);
		model.addAttribute("pdcount",pdcount);
		model.addAttribute("member_id",member_id);
		MemberDTO mdto = service.selectm(member_id);
		model.addAttribute("mdto",mdto);
		
		ProductDTO dto = service.select(product_no);
		model.addAttribute("pdto",dto);
		int totalcost=pdcount*product_after_cost;
		model.addAttribute("totalcost", totalcost);
		
		return "TodayLesson_UserPage/ej_us_orderform.us_main_section";
	}
	

	//�ֹ���Ŀ��� ����Ʈ ����
	@ResponseBody
	@RequestMapping("/ej_us_orderform/applypointjson")
	public void applypoint(@RequestParam(value="usepoint") int usepoint
			,@RequestParam(value="memberpoint") int member_point
			,@RequestParam(value="member_id") String member_id
			,@RequestParam(value="totalcost") int totalcost
			,@RequestParam(value="paymentcost") int paymentcost)
	{
		
		System.out.println("");
	}
	
	
	//�ֹ��Ϸ�
	@RequestMapping("/orderlistdetail")
	public String orderlistdetail(OrderDetailDTO oddto, OrderListDTO oldto
			,@RequestParam(value="orderlist_cost", required=false) int orderlist_cost
			,@RequestParam(value="product_no", required=false) int product_no
			,@RequestParam(value="order_count") int order_count
			, @RequestParam(value="addrselect",required=false) int addrselect
			,@RequestParam(value="roadaddr",required=false) String roadaddr
			,@RequestParam(value="jibunaddr",required=false) String jibunaddr
			, @RequestParam(value="detailaddr",required=false) String detailaddr
			,@RequestParam(value="orderlist_usepoint", required=false) int orderlist_usepoint
			,@RequestParam(value="remainpoint", required=false) int remainpoint
			,@RequestParam(value="member_id") String member_id
			, Model model)
	{
		
		
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
	
	//Product���̺� stock update  ..pdcount�޾ƿ;���
	ProductDTO productdto=new ProductDTO();
	System.out.println("pdcount"+order_count);
	int oldstock=service.selectstock(product_no);
	int newstock=oldstock-order_count;
	System.out.println("newstock"+newstock);
	productdto.setProduct_no(product_no);
	productdto.setProduct_stock(newstock);

	service.updatestock(productdto);
	
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
		int orderlist_no=Integer.parseInt(orderId);
		
		oldto.setOrderlist_no(orderlist_no); 
		service.insertorderlist(oldto);
		oddto.setOrderlist_no(orderlist_no);
		service.insertorderdetail(oddto);
		//����Ʈ ����
		

		OrderListDTO orderlistdto=service.selectorderlist(orderlist_no);//�������� �޾ƿ���
		List<OrderDetailDTO> list=service.selectorderdetail(orderlist_no);//���� ������ ���� �޾ƿ���
		model.addAttribute("list",list);
		model.addAttribute("orderlistdto",orderlistdto);
		return "TodayLesson_UserPage/ej_us_orderlistdetail.us_main_section";
	}
	
	
		
	
	}
	
