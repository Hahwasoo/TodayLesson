<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

   
   
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/JS/yi_findAddr.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!--my style  -->	
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/CSS/ej_us_orderform.css">
  <!--my style/  -->
<title>Insert title here</title>
<style>
.orderInfo{
display:none;}
.fa-times-circle{
text-align: right;
}
.delete_product_cart:hover{
text-decoration: none;
}

/* span.ej_link{
color:black;} */
.ej_link:hover{
text-decoration: none;
}
.hs_us_mypage_nav{
display:none;}

</style>

</head>
<body>

<div id="ej_container">
<!-- <h2 style="text-align:center;"><b>��ٱ��� <i class="fas fa-shopping-cart" style="color: rgb(224, 62, 82) ;"></i></b></h2><br><br> -->
<img src="${pageContext.request.contextPath}/resources/IMG/cartbanner.png" alt="cart" style="width:105%;" >
<input type="hidden" name="member_id" value="${memberid }" id="member_id"> 
<table class="table" style="border:none;">
<thead>
<tr>
<th>����</th><th>����</th><th></th><th>�̸�</th><th>����</th><th>����</th>
</tr>
</thead>
<tbody>
<c:set var="total_cart" value="0" />
<c:forEach var="item" items="${list}">
<tr>
<c:set var="lesson_title" value="${item.lesson_title}"/>
<c:set var="product_name" value="${item.product_name}"/>


<c:choose>

<c:when test="${lesson_title==null}"><!--��ǰ����  -->
<td><a href="#" class="delete_product_cart" id="${item.product_no}" ><i class="fas fa-times" style="font-size:20px; color:black;"></i></a></td>
<td>��ǰ</td>
<td><a href="${pageContext.request.contextPath}/todaylesson/ej_store_detail/${item.product_no}"><img src="${item.product_thumb}" alt="thumb"></a></td>
<td style="color:black;"><a href="${pageContext.request.contextPath}/todaylesson/ej_store_detail/${item.product_no}" class="ej_link"><span class="ej_link">${item.product_name }</span></a></td>
<td><fmt:formatNumber value="${item.product_after_cost }" type="number" maxFractionDigits="3"/>��</td>
<td>${item.cart_amount }</td>
</c:when>

<c:when test="${product_name==null}"><!--��������  -->
<td><a href="#" class="delete_lesson_cart" id="${item.lesson_no}" ><i class="fas fa-times" style="font-size:20px;color:black;"></i></a></td>
<td>����</td>
<td><a href="${pageContext.request.contextPath}/todaylesson/lesson_detail/${item.lesson_no}"><img src="${item.lesson_thumb}" alt="thumb"></a></td>
<td style="color:black;"><a href="${pageContext.request.contextPath}/todaylesson/lesson_detail/${item.lesson_no}">${item.lesson_title}</a></td>
<td><fmt:formatNumber value="${item.lesson_cost }" type="number" maxFractionDigits="3"/>��</td>
<td>1</td>
</c:when>
</c:choose>
</tr>
<!--foreach�� ������ �հ� ���  -->
<c:set var="total_cart" value="${total_cart + (item.product_after_cost * item.cart_amount)+item.lesson_cost}" />
</c:forEach> 
</tbody>
</table>
<hr>
<div class="sum">
	<h3><b>��ٱ��� �հ�  <fmt:formatNumber value="${total_cart }" type="number" maxFractionDigits="3"/>��</b></h3>
</div>

<input type="button" class='ej_btn' id="open_orderform_btn"value="�ֹ��ϱ�" style="float:right;width:110px;"><br>


<div class="orderInfo">

<div class="ej_cost">
 <b>���� ����Ʈ</b> <fmt:formatNumber value="${mdto.member_point}" type="number" maxFractionDigits="3"/><!-- <a href="#"> --><i class="far fa-times-circle"  id="cancel_btn" style="font-size:20px;color:black;float:right;"></i><!-- </a> --><br>
<b>����Ʈ ���</b> <input type="text"  class="form-control" id="usepoint" value=0 style="width:100px;">
<button class='ej_btn' id="pointbtn">����</button>
<div class="ej_cost right">
 <b style="font-size:20px;">�����ݾ�</b><br>
 <input type="text" value="${total_cart }" id="orderlist_cost1" class="paymentcost" readonly="readonly" style="border:none;background-color:transparent;font-size:25px;font-weight:bold;width:100px;">��</div>
<br></div>

<script>
    
    $("#pointbtn").click(function(){
  
      
  var memberpoint=${mdto.member_point};
  var usepoint=$("#usepoint").val();
  console.log('usepoint: ',usepoint);
  var member_id='${pageContext.request.userPrincipal.name}';
  var totalcost=${total_cart};
  var paymentcost=totalcost-usepoint;
  var remainpoint=memberpoint-usepoint;
  var data = {
        memberpoint: memberpoint,
       usepoint : usepoint,
       member_id: member_id,
       totalcost: totalcost,
       paymentcost: paymentcost,
       remainpoint: remainpoint
    };
 

  $.ajax({
   url :"/todaylesson/ej_us_orderform/applypointjson",
   type : "post",
   data : data,
   success : function(){
   console.log('success');
	if(usepoint>=memberpoint)
		{
		alert('��������Ʈ�� �ʰ��Ͽ����ϴ�.');
		}
	else{
	
		$('.paymentcost').val(data.paymentcost);
  	 $('#orderlist_usepoint').val(data.usepoint);
  	 $('.remainpoint').val(data.remainpoint);
  	 console.log(data.paymentcost);
  	 console.log(data.usepoint);
  	
	}
    } 
   ,error: function(){
      console.log(data);
      console.log('error');
     // alert('�α����� �ʿ��մϴ�.');
      }
  }); 
 
 });
    </script>


 <form role="form" method="post" autocomplete="off"> 
 <h4><b>�ֹ��� ����</b></h4><hr>
 <b>�ֹ��ڸ�</b>   <input type="text"   class="form-control" width="300" value=${mdto.member_name } readonly="readonly"><br>
 <b>�̸���</b>   <input type="text" name="member_email" class="form-control"  value=${mdto.member_email } readonly="readonly"><br>
<b>����ó</b>   <input type="text"  class="form-control" value=${mdto.member_phone } readonly="readonly"><br>
<input type="hidden" name="member_id" value='${memberid}'>
 <h4><b>����� ����</b></h4><hr>
 <input type="radio" name="deliveryaddr" value="same" checked="checked" id="sameaddr"  >�ֹ��������� ����
<input type="radio" name="deliveryaddr" value="newaddr" id="newaddr" >���ο� �����<br>
<%-- <input type="hidden" name="product_no" value=${product_no }>
<input type="hidden" name="order_count" value=${pdcount }> --%>
<input type="hidden" name="orderlist_cost" id="orderlist_cost" value="${total_cart}" class="paymentcost">
<input type="hidden" name="orderlist_usepoint" id="orderlist_usepoint" value=0>
<input type="hidden" name="remainpoint" class="remainpoint" value= ${mdto.member_point}>
<b>�����ڸ�</b><input type="text"  name="orderlist_receiver" class="form-control"  id="rec" value=${mdto.member_name } readonly="readonly"><br>
<b>�޴���ȭ</b><input type="text"  name="orderlist_phone" class="form-control"  id="phone" value=${mdto.member_phone } readonly="readonly"><br>
<b>�ּ�</b><input type="text" name="orderlist_addr" size="150"  id="addr" class="form-control" value="${mdto.member_addr }" readonly="readonly"><br>


   <input type="radio"  name="addrselect" value=0 id="sameaddrselect" checked="checked">
 
 <div id="findaddr">
 <label for='addr'></label>
               <div class='form-row'>
                  <div class='col-5'>
                     <input type="text" id="sample4_postcode" name="orderlist_zipcode"
                        placeholder="�����ȣ" value=${mdto.member_zipcode } class='form-control' >
                  </div>
                  <input type="button" onclick="sample4_execDaumPostcode()"
                     value="�����ȣ ã��" readonly="readonly" class='ej_btn' ><br>
               </div>
               <div class='juso'>
            
                <input type="radio" id='roadAddress' name='addrselect' value="1" 
                     ><label for="addrselect">���θ��ּ� ����</label> <br>
                  <input type="text" id="sample4_roadAddress" placeholder="���θ��ּ�"
                     name="roadaddr" readonly="readonly" class="form-control" >
                 
				 <input type="radio" id='jibunAddress' name='addrselect' value="2"><label
                     for="addrselect" >�����ּ� ����</label><br>
                  <input type="text" id="sample4_jibunAddress" placeholder="�����ּ�"
                     name="jibunaddr" readonly="readonly" class="form-control" >
                 
               </div>
               <span id="guide" style="color: #999"></span> <label
                  for="detailaddr">���ּ�</label> <input type="text" id="detailaddr"
                  name="detailaddr" class="form-control"> 

<br>  
</div> 

<hr>
<b>��ۿ�û����</b><textarea rows="5" name="orderlist_msg" class="form-control" cols="100"></textarea><br>
<script>

$("#findaddr").hide();
$("#sameaddrselect").hide();
var h=$('addrselect').val();
console.log('addrselect',h);


var value = $('input[name=addrselect]').val();
console.log('addrselect input���� ������',value);
$('input[name=addrselect]').val('0');
$("#roadAddress").on('click', function() {
	$("#roadAddress").val("1");
});
$("#jibunAddress").on('click', function() {
	$("#jibunAddress").val("2");
});
	

</script>
<script>
$("#newaddr").on('click', function() {
	
	$('#rec').val('');
	$('#phone').val('');
	$("#addr").hide();
	$('#addr').val('');
	$('#zipcode').val('');
	$('#sample4_postcode').val('');
	$('#rec').attr("readonly",false);
	$('#phone').attr("readonly",false);
	$('#addr').attr("readonly",false);
	//$("#findaddr").attr("disabled", true);
	//$("#findaddr").show();
	$("#findaddr").toggle();
	//$("#findaddr").find("input").prop("disabled",false);
	//�����ȣ ã�� ��ư Ȱ��ȭ ��ų����
	
});
$("#sameaddr").on('click', function() {
	
	$('#rec').val('${mdto.member_name }');
	$('#phone').val('${mdto.member_phone }');
	$('#addr').val('${mdto.member_addr }');
	$('#rec').attr("readonly",true);
	$('#phone').attr("readonly",true);
	$('#addr').attr("readonly",true);
	$('#addr').toggle();
	$("#findaddr").toggle();
	//$("#findaddr").find("input").prop("disabled",true);
});
</script>
<br>
<div class="ej_cost2">
<h4><b>��������</b></h4><hr>
 <b>�������� ����</b>
  <input type="radio" name="paymethod" value="card">�ſ�ī��
<input type="radio" name="paymethod" value="kakaopay">īī������

<br>

 ��ۺ� ����<br>
 <div class="ej_cost2_right">
<b> �����ݾ�</b><br>
 <input type="text" name="paymentt2" value="${total_cart }" class="paymentcost" readonly="readonly" style="border:none;background-color:transparent;font-size:25px;font-weight:bold;width:100px;">��
 
  <button id="check_module" type="button" class='ej_btn' >�����ϱ�</button> </div>
  </div>
  <button id="test">�׽�Ʈ��ư ��������</button>
  <br>


<script>
$("#test").click(function () {
$("form").attr("action", "/todaylessonmypage/order_cart");
	$("form").submit();  });
</script>
 <script>
    $("#check_module").click(function () {
    	  $("#check_module").click(function () {
        	  var IMP = window.IMP; // ��������
        	  var cost=$(".paymentcost").val();

        	 var inputValue = $("input[name='paymethod']:checked").val();
             if(inputValue=='card')
            	 {IMP.init('imp57388060');//�̴Ͻý�
            	 }
             else if(inputValue=='kakaopay')
            	 {IMP.init('imp65601532');//īī��
            	 }
             
           IMP.request_pay({
           pg: 'inicis', // version 1.1.0���� ����.
           /*
       'kakao':īī������,
       html5_inicis':�̴Ͻý�(��ǥ�ذ���)
       'nice':���̽�����
       'jtnet':����Ƽ��
       'uplus':LG���÷���
       'danal':�ٳ�
       'payco':������
       'syrup':�÷�����
       'paypal':������
       */
       pay_method: 'card',
       /*
       'samsung':�Ｚ����,
       'card':�ſ�ī��,
       'trans':�ǽð�������ü,
       'vbank':�������,
       'phone':�޴����Ҿװ���
       */
       merchant_uid: 'merchant_' + new Date().getTime(),
       /*
       merchant_uid�� ���
       https://docs.iamport.kr/implementation/payment
       ���� url�� ���󰡽ø� ���� �� �ִ� ����� �ֽ��ϴ�.
       �����ϼ���.
       ���߿� ������ �غ��Կ�.
       */
       name: '�ֹ���ǰ:',
       //����â���� ������ �̸�
       amount: cost,
       //����
       buyer_email: '${mdto.member_email}',
       buyer_name: '${mdto.member_name}',
       buyer_tel: '${mdto.member_phone}',
       buyer_addr: '${mdto.member_addr}',
       buyer_postcode: '${mdto.member_zipcode}',
       m_redirect_url: '/orderlistdetail'
       /*
       ����� ������,
       ������ ������ �����Ǵ� URL�� ����
       (īī������, ������, �ٳ��� ���� �ʿ����. PC�� ���������� callback�Լ��� ����� ������)
       */
    	  
       }, function (rsp) {
     
      
       		if (rsp.success) {
      var msg = '�ֹ��� �Ϸ�Ǿ����ϴ�';
      /*  msg += '����ID : ' + rsp.imp_uid;
       msg += '���� �ŷ�ID : ' + rsp.merchant_uid;
       msg += '���� �ݾ� : ' + rsp.paid_amount;
       msg += 'ī�� ���ι�ȣ : ' + rsp.apply_num;  */
       			
       			$("form").attr("action", "/todaylessonmypage/order_cart");
       			$("form").submit();  
       		} else {
    	   
       		var msg = '������ �����Ͽ����ϴ�.';
      		 msg += '�������� : ' + rsp.error_msg;
       }
      alert(msg);
       });
       });
    });

    </script>
    
   

</form>
</div>
<script>
 $("#open_orderform_btn").click(function(){
  $(".orderInfo").slideDown();
  $("#open_orderform_btn").slideUp();
 }); 
 
 $("#cancel_btn").click(function(){
	 $(".orderInfo").slideUp();
	 $("#open_orderform_btn").slideDown();
	});   
</script>
<script>
/* ��ǰ���� */
$(".delete_product_cart").on('click',function(){
	
	console.log(this);
	console.log('delete pro');
	var product_no=$(this).prop('id');
	var member_id='${memberid }';
	var lesson_no=0;
	var data={
			product_no: product_no,
			member_id: member_id,
			lesson_no: lesson_no
	}
	
	$.ajax({
	      url:"/todaylessonmypage/deletecart_json",
	      type:"post",
	      data: data,
	      success: function(result){
	    	 if(result>0)
	    		 {
	    		// alert('�ش��ǰ�� ��ٱ��Ͽ��� ���� �Ͻðڽ��ϱ�?');
	    	  location.href="/todaylessonmypage/mycart/"+'${memberid}';
	    		 }
	    	 else{
	    		 alert('���� ���еǾ����ϴ�.');
	    	 }
	      },error: function(){
	         console.log('error');
	   }    
	  });

	
});
</script>
<script>
$(".delete_lesson_cart").on('click',function(){
	/* �������� */
	console.log(this);
	console.log('delete les');
	var product_no=0;
	var lesson_no=$(this).prop('id');
	var member_id='${memberid }';
	var data={
			product_no: product_no,
			lesson_no: lesson_no,
			member_id: member_id
	}
	
	$.ajax({
	      url:"/todaylessonmypage/deletecart_json",
	      type:"post",
	      data: data,
	      success: function(result){
	    	 if(result>0)
	    		 {
	    		// alert('�ش��ǰ�� ��ٱ��Ͽ��� ���� �Ͻðڽ��ϱ�?');
	    	  location.href="/todaylessonmypage/mycart/"+'${memberid}';
	    		 }
	    	 else{
	    		 alert('���� ���еǾ����ϴ�.');
	    	 }
	      },error: function(){
	         console.log('error');
	   }    
	  });

	
});
</script>

</body>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- Optional JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</html>