<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="resources/JS/yi_findAddr.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>

</style>
</head>




<body>
<script>

//$("#ordersuccess_btn").hide();
</script>

<h2 text align="center">�ֹ���û��</h2>
<h4>�ֹ��� ���</h4>
<hr>
<input type="hidden" name="product_no" value=${product_no }>
<table>
<thead>
<th></th><th>��ǰ��</th><th>����ݾ�</th><th>����</th><th>��ۺ�</th><th>�ֹ��ݾ�</th>
</thead>
<tbody>
<tr>
<td><img src="${pdto.product_img}" id="ej_order_topimg" width="200"></td>
<td><h4>${product_name }</h4></td>
<td>${product_after_cost }</td>
 <td> ${pdcount} ��</td>
 <td>��ۺ� ����</td>
 <td>${totalcost }��</td>
 </tr>
</tbody>
</table>
<hr>
 <input type="hidden" name="product_name" value=${product_name }>
<input type="hidden" name="member_id" value='${pageContext.request.userPrincipal.name}'>
 <input type="hidden" name="order_count" value=${pdcount }>

 
 <%-- <input type="hidden" name="orderlist_cost1" value=${totalcost }> --%>
 <input type="hidden" name="product_after_cost" value="${product_after_cost }">
 ���� ����Ʈ: ${mdto.member_point}<br>
����Ʈ ��� <input type="text"  class="form-control" id="usepoint" value=0>
<button class='btn btn-primary' id="pointbtn">����</button><br>

 �����ݾ�<br>
 <input type="text" value=${totalcost } id="orderlist_cost1" class="paymentcost" readonly="readonly">

<script>
    
    $("#pointbtn").click(function(){
  
      
  var memberpoint=${mdto.member_point};
  var usepoint=$("#usepoint").val();
  console.log('usepoint: ',usepoint);
  var member_id='${pageContext.request.userPrincipal.name}';
  var totalcost=${totalcost};
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
   url :"/ej_us_orderform/applypointjson",
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
 <h4>�ֹ��� ����</h4><hr>
 �ֹ��ڸ�   <input type="text"   class="form-control" width="300" value=${mdto.member_name } readonly="readonly"><br>
 �̸���   <input type="text" name="member_email" class="form-control"  value=${mdto.member_email } readonly="readonly"><br>
����ó   <input type="text"  class="form-control" value=${mdto.member_phone } readonly="readonly"><br>
<input type="hidden" name="member_id" value='${pageContext.request.userPrincipal.name}'>
 <h4>����� ����</h4><hr>
 <input type="radio" name="deliveryaddr" value="same" checked="checked" id="sameaddr"  >�ֹ��������� ����
<input type="radio" name="deliveryaddr" value="newaddr" id="newaddr" >���ο� �����<br>
<input type="hidden" name="product_no" value=${product_no }>
<input type="hidden" name="order_count" value=${pdcount }>
<input type="hidden" name="orderlist_cost" id="orderlist_cost" value=${totalcost } class="paymentcost">
<input type="hidden" name="orderlist_usepoint" id="orderlist_usepoint" value=0>
<input type="hidden" name="remainpoint" class="remainpoint" value= ${mdto.member_point}>
�����ڸ�<input type="text"  name="orderlist_receiver" class="form-control"  id="rec" value=${mdto.member_name } readonly="readonly"><br>
�޴���ȭ<input type="text"  name="orderlist_phone" class="form-control"  id="phone" value=${mdto.member_phone } readonly="readonly"><br>
�ּ�<input type="text" name="orderlist_addr" size="150"  id="addr" class="form-control" value="${mdto.member_addr }" readonly="readonly"><br>


   <input type="radio"  name='addrselect' value="0" id="sameaddrselect" checked="checked">
 
 <div id="findaddr">
 <label for='addr'></label>
               <div class='form-row'>
                  <div class='col-5'>
                     <input type="text" id="sample4_postcode" name="orderlist_zipcode"
                        placeholder="�����ȣ" value=${mdto.member_zipcode } class='form-control' >
                  </div>
                  <input type="button" onclick="sample4_execDaumPostcode()"
                     value="�����ȣ ã��" readonly="readonly" class='btn btn-primary' ><br>
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
��ۿ�û����<textarea rows="5" name="orderlist_msg" class="form-control" cols="100"></textarea><br>
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
<h4>��������</h4><hr>
 �������� ����
  <input type="radio" name="paymethod" value="card">�ſ�ī��
<input type="radio" name="paymethod" value="kakaopay">īī������
<input type="radio" name="paymethod" value="payco">������
<input type="radio" name="paymethod" value="accountpay">�������Ա�
<br>

 ��ۺ� ����<br>
 �����ݾ�<br>
 <input type="text" name="paymentt2" value="${totalcost }" class="paymentcost" readonly="readonly">
 
  <button id="check_module" type="button" class='btn btn-primary'>�����ϱ�</button>



 <script>
    $("#check_module").click(function () {
  
       var IMP = window.IMP; // ��������
       var cost=$(".paymentcost").val();
    	// $("form").attr("action", "/orderlistdetail");
       IMP.init('imp65601532');
       
       // 'iamport' ��� �ο����� "������ �ĺ��ڵ�"�� ���
       // i'mport ������ ������ -> ������ -> �������ĺ��ڵ�
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
     
       $("#ordersuccess_btn").show();
       		if (rsp.success) {
      var msg = 'ȭ���� �ֹ��Ϸ� ��ư�� �����ּ���';
      /*  msg += '����ID : ' + rsp.imp_uid;
       msg += '���� �ŷ�ID : ' + rsp.merchant_uid;
       msg += '���� �ݾ� : ' + rsp.paid_amount;
       msg += 'ī�� ���ι�ȣ : ' + rsp.apply_num;  */
       			<%-- location.href='<%=request.getContextPath()%>/orderlistdetail'; --%>
       		} else {
    	   
       		var msg = '������ �����Ͽ����ϴ�.';
      		 msg += '�������� : ' + rsp.error_msg;
       }
      alert(msg);
       });
       });

    </script>
    <div id="ordersuccess_btn" class="text-center">
    <input type="submit" id="ordersuccess_btn"  class="btn btn-success btn-lg" value="�ֹ��Ϸ�"></div>
    </form>
    <script>
   $("#ordersuccess_btn").hide();
    $("#ordersuccess_btn").click(function () {
    $("form").attr("action", "/orderlistdetail");
    });
    </script>
    
</body>
 <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- Optional JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
 
</html>