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
$("#ordersuccess_btn").hide();
</script>

<h2 text align="center">�ֹ���û��</h2>
<h4>�ֹ��� ���</h4>
<hr>
<input type="hidden" name="product_no" value=${product_no }>
 <img src="${pdto.product_img}" id="ej_order_topimg" width="200">
<h4>${product_name }</h4><br>
<input type="hidden" name="product_name" value=${product_name }>
<input type="hidden" name="member_id" value='${pageContext.request.userPrincipal.name}'>

 ����: ${pdcount} ��<br>
 ��ü�ݾ�:
 ${product_after_cost}��X${pdcount}<input type="hidden" name="order_count" value=${pdcount }>
 =>${totalcost }��<br><hr>
 
 <input type="hidden" name="orderlist_cost" value=${totalcost }>
 <input type="hidden" name="product_after_cost" value="${product_after_cost }">
 ���� ����Ʈ: ${mdto.member_point}<br>
����Ʈ ��� <input type="text"  class="form-control" id="usepoint" value=0>
<button class='btn btn-primary' id="pointbtn">����</button><br>

 �����ݾ�<br>
 <input type="text" value="${totalcost }" id="paymentcost" class="paymentcost" readonly="readonly">

<script>
    
    $("#pointbtn").click(function(){
  
       console.log('usepoint');
  var memberpoint=${mdto.member_point};
  var usepoint=$("#usepoint").val();
  var member_id='${pageContext.request.userPrincipal.name}';
  var totalcost=${totalcost};
  var paymentcost=totalcost-usepoint;
  var data = {
        memberpoint: memberpoint,
       usepoint : usepoint,
       member_id: member_id,
       totalcost: totalcost,
       paymentcost: paymentcost
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
  	$('.paymentcost').val(paymentcost);
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
 �ֹ��ڸ�   <input type="text"   class="form-control" width="300" value=${mdto.member_name }><br>
 �̸���   <input type="text" name="member_email" class="form-control"  value=${mdto.member_email }><br>
����ó   <input type="text"  name="orderlist_phone" class="form-control" value=${mdto.member_phone }><br>
<input type="hidden" name="member_id" value='${pageContext.request.userPrincipal.name}'>
 <h4>����� ����</h4><hr>
 <input type="radio" name="deliveryaddr" value="same" checked="checked"   >�ֹ��������� ����
<input type="radio" name="deliveryaddr" value="newaddr"   >���ο� �����<br>
<input type="hidden" name="product_no" value=${product_no }>
<input type="hidden" name="order_count" value=${pdcount }>
�����ڸ�<input type="text"  name="orderlist_receiver" class="form-control"  value=${mdto.member_name }><br>
�޴���ȭ<input type="text"  name="orderlist_phone" class="form-control"  value=${mdto.member_phone }><br>
�߰���ȣ(����)<input type="text"  class="form-control"  name="phone2"><br>
�ּ�(�����ȣ)<input type="text" name="orderlist_addr" size="150"  class="form-control" value="${mdto.member_addr }"><br>
üũ(�⺻������� ���峪�߿� �߰�)<br>
��ۿ�û����<textarea rows="5"  class="form-control" cols="100"></textarea><br>
<!-- ---------------���ܿ����ȣ---------<br>
<label for='addr'>�ּ�</label>
               <div class='form-row'>
                  <div class='col-5'>
                     <input type="text" id="sample4_postcode" name='zipcode'
                        placeholder="�����ȣ" class='form-control'>
                  </div>
                  <input type="button" onclick="sample4_execDaumPostcode()"
                     value="�����ȣ ã��" readonly="readonly" class='btn btn-primary'><br>
               </div>
               <div class='juso'>
                  <input type="text" id="sample4_roadAddress" placeholder="���θ��ּ�"
                     name="roadaddr" readonly="readonly" class="form-control">
                  <input type="radio" id='roadAddress' name='addrselect' value="0"
                     required><label for="addrselect">���θ��ּ� ����</label> <br>

                  <input type="text" id="sample4_jibunAddress" placeholder="�����ּ�"
                     name="jibunaddr" readonly="readonly" class="form-control">
                  <input type="radio" id='jibunAddress' name='addrselect' value="1"><label
                     for="addrselect">�����ּ� ����</label><br>
               </div>
               <span id="guide" style="color: #999"></span> <label
                  for="detailaddr">���ּ�</label> <input type="text" id="detailaddr"
                  name="detailaddr" class="form-control"> 

<br> -->
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
       name: '�ֹ���:�����׽�Ʈ',
       //����â���� ������ �̸�
       amount: ${totalcost},
       //����
       buyer_email: '${dto.member_email}',
       buyer_name: '${dto.member_name}',
       buyer_tel: '${dto.member_phone}',
       buyer_addr: '${dto.member_addr}',
       buyer_postcode: '${dto.member_zipcode}',
       m_redirect_url: '/todaylessonlogin'
       /*
       ����� ������,
       ������ ������ �����Ǵ� URL�� ����
       (īī������, ������, �ٳ��� ���� �ʿ����. PC�� ���������� callback�Լ��� ����� ������)
       */
       }, function (rsp) {
       console.log(rsp);
       $("#ordersuccess_btn").show();
       if (rsp.success) {
       var msg = 'ȭ���� �ֹ��Ϸ� ��ư�� �����ּ���';
       msg += '����ID : ' + rsp.imp_uid;
       msg += '���� �ŷ�ID : ' + rsp.merchant_uid;
       msg += '���� �ݾ� : ' + rsp.paid_amount;
       msg += 'ī�� ���ι�ȣ : ' + rsp.apply_num;
       } else {
       var msg = '������ �����Ͽ����ϴ�.';
       msg += '�������� : ' + rsp.error_msg;
       }
       alert(msg);
       });
       });

    </script>
    <div id="ordersuccess_btn">
    <input type="submit" id="ordersuccess_btn" value="�ֹ��Ϸ�"></div>
    </form>
    <script>
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