<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Required meta tags -->
  <!--   <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->
    <!-- Bootstrap CSS -->
   <!--  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"> -->
     <!-- Bootstrap CSS/ -->
    <!-- �ּ�ã��-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/JS/yi_findAddr.js"></script>
<!--  �ּ�ã��/-->
<!--���� ������Ʈ  -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!--����������Ʈ/  -->
<!--mystyle  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/CSS/ej_us_orderform.css">  
<!--mystyle/  -->
</head>




<body>

<div id="ej_container">
<h2 text align="center"><b>�ֹ���û��    </b><img alt="ordericon" src="${pageContext.request.contextPath}/resources/IMG/contract.svg"
style="width:40px; display:inline;"></h2>
<h4><b>�ֹ��� ���</b></h4>
<hr>
<input type="hidden" name="product_no" value=${product_no }>
<table class="table">
<thead>
<th></th><th>��ǰ��</th><th>����ݾ�</th><th>����</th><th>��ۺ�</th><th>�ֹ��ݾ�</th>
</thead>
<tbody>
<tr>
<td><img src="${pdto.product_thumb}" id="ej_order_topimg" s"></td>
<td><h4>${product_name }</h4></td>
<td><fmt:formatNumber value="${pdto.product_after_cost}" type="number" maxFractionDigits="3"/>�� </td>
 <td> ${pdcount} ��</td>
 <td>��ۺ� ����</td>
 <td><fmt:formatNumber value="${totalcost }" type="number" maxFractionDigits="3"/>��</td>
 </tr>
</tbody>
</table>
<hr>
 <input type="hidden" name="product_name" value=${product_name }>
<input type="hidden" name="member_id" value='${pageContext.request.userPrincipal.name}'>
 <input type="hidden" name="order_count" value=${pdcount }>
 <input type="hidden" name="product_after_cost" value="${product_after_cost }">
 
 <div class="ej_cost">
 <b>���� ����Ʈ</b><fmt:formatNumber value="${mdto.member_point}" type="number" maxFractionDigits="3"/><br>
<b>����Ʈ ���</b> <input type="text"  id="usepoint" value=0>
<button class="ej_btn point" id="pointbtn">����</button>
<div class="ej_cost right">
<b style="font-size:25px;">�����ݾ�</b>
<input type="text" style="border:none; font-size:30px; font-weight:bolder; width:90px; background-color:transparent;" value="${totalcost }" id="orderlist_cost1" class="paymentcost" readonly="readonly">�� 
</div>
</div>
<script>
    
    $("#pointbtn").click(function(){
  
      
  var memberpoint=${mdto.member_point};
  var usepoint=$("#usepoint").val();
  console.log('usepoint: ',usepoint);
  var member_id='${pageContext.request.userPrincipal.name}';
  var totalcost=${totalcost};
  var paymentcost=totalcost-usepoint;
  var remainpoint=memberpoint-usepoint;
  var element = $(".paymentcost3");//Ŭ������ �޾ƿ�
  var paymentnum = element.children();
  var hh=$("fmt").val();
  console.log('ele',element);
console.log('paymentnum',paymentnum);
console.log('val',hh);
  var data = {
        memberpoint: memberpoint,
       usepoint : usepoint,
       member_id: member_id,
       totalcost: totalcost,
       paymentcost: paymentcost,
       remainpoint: remainpoint
    };
 

  $.ajax({
   url :"${pageContext.request.contextPath}/todaylesson/ej_us_orderform/applypointjson",
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
		totalcost=data.paymentcost;
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

<br><br>
 <form role="form" method="post"> 
 <h4><b>�ֹ��� ����</b></h4><hr>
 <b>�ֹ��ڸ�</b>   <input type="text" style="border:none; width="300" value=${mdto.member_name } readonly="readonly"><br>
 <b>�̸���</b>  <input type="text" style="border:none; name="member_email"   readonly="readonly"  value=${mdto.member_email } ><br>
<b>����ó</b>   <input type="text"   style="border:none;"  readonly="readonly"  value=${mdto.member_phone }><br><br>
<input type="hidden" name="member_id" value='${pageContext.request.userPrincipal.name}'>
<h4><b>����� ����</b></h4><hr>
 <input type="radio" name="deliveryaddr" value="same" checked="checked" id="sameaddr"  >�ֹ��������� ����    
<input type="radio" name="deliveryaddr" value="newaddr" id="newaddr" >���ο� �����<br>
<input type="hidden" name="product_no" value=${product_no }>
<input type="hidden" name="order_count" value=${pdcount }>
<input type="hidden" name="orderlist_cost" id="orderlist_cost" value=${totalcost } class="paymentcost">
<input type="hidden" name="orderlist_usepoint" id="orderlist_usepoint" value=0>
<input type="hidden" name="remainpoint" class="remainpoint" value= ${mdto.member_point}>
<b>�����ڸ�</b><br><input type="text"  name="orderlist_receiver"    id="rec"  readonly="readonly" value=${mdto.member_name }><br>
<b>�޴���ȭ</b><br><input type="text"  name="orderlist_phone"    id="phone" readonly="readonly" value=${mdto.member_phone } ><br>
<b>�ּ�</b><br><input type="text" name="orderlist_addr" size="150"  id="addr"   readonly="readonly" value="${mdto.member_addr }"><br>


   <input type="radio"  name='addrselect' value="0" id="sameaddrselect" checked="checked">
 
 <div id="findaddr">
 <label for='addr'></label>
               <div class='form-row'>
                  <div class='col-5'>
                     <input type="text" id="sample4_postcode" name="orderlist_zipcode"
                        placeholder="�����ȣ" value=${mdto.member_zipcode } >
                  </div>
                  <input type="button" onclick="sample4_execDaumPostcode()" value="�����ȣ ã��" readonly="readonly" class='ej_btn' ><br>
               </div>
               <div class='juso'>
            
                <input type="radio" id='roadAddress' name='addrselect' value="1" 
                     ><label for="addrselect">���θ��ּ� ����</label> <br>
                  <input type="text" style="width:600px;" id="sample4_roadAddress" placeholder="���θ��ּ�"
                     name="roadaddr" readonly="readonly"   ><br>
                 
				 <input type="radio" id='jibunAddress' name='addrselect' value="2">
				 <label for="addrselect" >�����ּ� ����</label><br>
                  <input type="text" id="sample4_jibunAddress" style="width:600px;" placeholder="�����ּ�"
                     name="jibunaddr" readonly="readonly"  >
                 
               </div>
               <span id="guide" style="color: #999"></span> 
               <label for="detailaddr">���ּ�</label><br>
                <input type="text" id="detailaddr"name="detailaddr" style="width:600px;"   placeholder="���ּ�"> 

<br>  
</div> 

<hr>
<b>��ۿ�û����</b><br><textarea rows="5" name="orderlist_msg"   cols="100"></textarea><br><br>
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
 <b>�����ݾ�</b><br>
 <input type="text" style="border:none; font-size:30px; font-weight:bolder; width:90px; background-color:transparent" name="paymentt2" value="${totalcost }" class="paymentcost" readonly="readonly">��
 
  <button id="check_module" type="button" class='ej_btn' >�����ϱ�</button>
  </div>
  </div>
  <br>


	
 <script>

 
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
       name: '�ֹ���ǰ:${product_name}',
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
  /*     var msg = 'ȭ���� �ֹ��Ϸ� ��ư�� �����ּ���'; */
      /*  msg += '����ID : ' + rsp.imp_uid;
       msg += '���� �ŷ�ID : ' + rsp.merchant_uid;
       msg += '���� �ݾ� : ' + rsp.paid_amount;
       msg += 'ī�� ���ι�ȣ : ' + rsp.apply_num;  */
      
       			
       			$("form").attr("action", "${pageContext.request.contextPath}/todaylesson/orderlistdetail");
    
       			$("form").submit();  
       			
       		} else {
    	   
       		var msg = '������ �����Ͽ����ϴ�.';
      		 msg += '�������� : ' + rsp.error_msg;
       }
      alert(msg);
       });
       });

    </script>
  
 
    </form>
  </div>
    
</body>
 <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- Optional JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
 
</html>