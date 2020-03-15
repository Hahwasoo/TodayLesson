<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

$(document).ready(function(){
	let con = $('#content').html();
});

</script>
<script> 
/* alert('${dto.product_no}'); */
function replyList(){
 var gdsNum = ${dto.product_no};
 var member_id=${pageContext.request.userPrincipal.name};
 var repCon = $("#repCon").val();
 alert('replyList()');
  $.getJSON("/ej_store_detail/replyList" + "?product_no="+gdsNum+"&member_id="+member_id+"&pdreview_content="+repCon, function(data){
	/*   $.getJSON("/ej_store_detail/replyList",function(data){ */
		/* $.getJSON("/ej_store_detail/registReply", function(data){ */
  var str = "";
	alert('funtion');
  $(data).each(function(){
   alert(data+"<=data");//data 는 컨트롤러에서 list매서드가 리턴한 리스트임.
   alert(this+"<=thiss");//this 는 컨트롤러에서 list매서드가 리턴한 리스트임.
   //this가 data네
   
/*    var repDate = new Date(this.repDate);
   repDate = repDate.toLocaleDateString("ko-US") */
   //테이블에 저장된 날짜 데이터와 컨트롤러에서 뷰로 보낼때의 날짜 데이터 형식이 다르기 때문에, 컨트롤러에서 toLocaleDateString() 를 이용해 1차적으로 데이터를 가공
   //this.gdsNum this.userName repDate repCon으로 되잇음
   str += "<li data-gdsNum='" + this.product_no + "'>"
    /*  + "<div class='userInfo'>" */
    /*  + "<span class='userName'>" + this.userName + "</span>" */
     /* + "<span class='date'>" + repDate + "</span>" */
   /*   + "</div>" */
     + "<div class='replyContent'>" + this.pdreview_content + "</div>"
     + "</li>";           
  });
  
  $("section.replyList ol").html(str);
 });
}
</script>

<style>
 #ej_sdetail_top{
border: 1px solid silver;
width:400px;

} 
#ej_sdetail_top{
border: 1px solid silver;
}
#ej_sdetail_right{
border: 1px solid silver;
width:400px;
float:right;
}
#ej_top{
border: 1px solid silver;}

</style>

</head>
<body>


<span id="ej_topimg">
<!--썸네일 이미지와 동일. 크기는 큼 -->
<img src="${dto.product_img }" id="ej_sdetail_topimg" width="600">
</span>

<form action="/ej_us_orderform" method="post">
<nav id="ej_sdetail_right">
카테고리<br>
상품명: <c:out value="${dto.product_name}"></c:out><br>
가격: <c:out value="${dto.product_cost}"></c:out><br>
할인율 옆에 표기<br>
<input type="hidden" name="product_no" value="${dto.product_no }"/>
<input type="hidden" name="product_name" value="${dto.product_name }"/>
<input type="hidden" name="product_cost" value="${dto.product_cost }"/>

배송비 무료<br>
수량: <input type=text size="1" name="pdcount" placeholder="1" required="required"><br>
하트랑 장바구니
<input type="text" name="memberid" value="${pageContext.request.userPrincipal.name}" id="memberid">
<%-- <a href="http://localhost:9080/ej_us_orderform"+"?product_no="+"${dto.product_no }" >구매</a> --%>
<input type="submit">
</nav>
안녕
<!-- </form> -->

<nav id="ej_sdetail_top">
<span id="ej_top">
상품소개
</span>
<span id="ej_top">
후기</span>
<span id="ej_top">
배송/교환/환불
</span>
</nav>

상품소개
<hr>
<div id="content">

${dto.product_content}

</div>

<!-- 후기 -->
<br>
후기
<hr>
<!-- <section class="replyList">
<ol>
</ol>

<script>
 replyList();
</script>
</section> -->
<div id="replyListTest">
</div>
<section class="replyForm">
<form role="form" method="post" autocomplete="off">
<input type="text" name="gdsNum" id="gdsNum" value="${dto.product_no }">

<div class="input_area">
	<textarea name="repCon" id="repCon"></textarea>
</div>

<div class="input_area">
<button type="button" id="reply_btn">후기 남기기</button>
<script>
 $("#reply_btn").click(function(){
/*   alert('replye_btn'); */
  var formObj = $(".replyForm form[role='form']");
  var gdsNum = $("#gdsNum").val();
  var repCon = $("#repCon").val();
  var memberid=$("#memberid").val();
  
  var data = {
    /* gdsNum : gdsNum,
    repCon : repCon */
		 product_no : gdsNum,
		 pdreview_content : repCon,
		 member_id: memberid
    };
  
  $.ajax({/* "/shop/view/registReply" */
   url :"/ej_store_detail/registReply ",// 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
   //request mapping value랑 맞추면되는듯
   type : "post",
   data : data,
   success : function(){
    console.log('success');
    console.log(data);
    $("#repCon").val("");
	   replyList();
	  // $("#replyListTest").html(data);
 //textarea초기화
   }
  
  
   ,error: function(){
	   console.log(data);
	   console.log('error');
	   }
  });
 });
</script>
</div>
</form>
</section>

<!--  -->
배송/교환/환불
<hr>

<%-- <a href="${pageContext.request.contextPath }/list">목록으로</a> --%>

    

</body>
<!--컨트롤러  -->
<!-- // 상품 소감(댓글) 목록 /view/replyList엿음..replyList        
	/*List<PdReviewDTO>*/
	@ResponseBody
	@RequestMapping(value = "/ej_store_detail/replyList", method = RequestMethod.GET)
	public  List<PdReviewDTO>getReplyList(@RequestParam("product_no") int product_no) throws Exception {
//원래는 RequestParam임 pathvariable로 해
	   
	 List<PdReviewDTO> reply = service.replyList(product_no);
	 System.out.println("getReplyListController");
	 return reply;
		/*return 3;*/
		//여기서 리턴한게 replyList()함수에서 data로 받아지네
		
	} 
// 상품 소감(댓글) 작성 registReply
	@ResponseBody
	@RequestMapping(value = "/ej_store_detail/registReply", method = RequestMethod.POST)
	public void registReply (/*PdReviewDTO reply*/
			@RequestParam String member_id,
			@RequestParam String pdreview_content ,
			@RequestParam int product_no,
			HttpSession session) throws Exception {
	
	 
	/* MemberDTO member = (MemberDTO)session.getAttribute("member");*/
		PdReviewDTO pdreviewdto=new PdReviewDTO();
		pdreviewdto.setMember_id(member_id);
		pdreviewdto.setProduct_no(product_no);
		pdreviewdto.setPdreview_content(pdreview_content);
		
		int result=service.registReply(pdreviewdto);
	 System.out.println("registReply Controller");
/*	 reply.setMember_id(member.getMember_id());
	 
	 service.registReply(reply)*/;
	
} -->
</html>


