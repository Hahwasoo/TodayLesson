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
<form method="post" action="/orderlistdetail">
<h2 text align="center">�ֹ���û��</h2>
<h4>�ֹ��� ���</h4>
<hr>
 <%-- ${product_no } --%>
 <img src="${pdto.product_img}" id="ej_order_topimg" width="200">
<h4>${product_name }</h4><br>


 ����: ${pdcount } ��<br>
 ��ü�ݾ�:
 ${product_after_cost}��X${pdcount}
 =>>>>${totalcost }��
<%--  �α��ξ��̵�:${member_id}
${mdto.member_id }
${mdto.member_addr } --%>

 
 <h4>�ֹ��� ����</h4><hr>
 �ֹ��ڸ�   <input type="text"  class="form-control" width="300" value=${mdto.member_name }><br>
 �̸���   <input type="text" class="form-control"  value=${mdto.member_email }><br>
����ó   <input type="text"  class="form-control" value=${mdto.member_phone }><br>

 <h4>����� ����</h4><hr>
 <input type="radio" name="deliveryaddr" value="same" checked="checked"   >�ֹ��������� ����
<input type="radio" name="deliveryaddr" value="newaddr"   >���ο� �����<br>

�����ڸ�<input type="text"  class="form-control"  value=${mdto.member_name }><br>
�޴���ȭ<input type="text"  class="form-control"  value=${mdto.member_phone }><br>
�߰���ȣ(����)<input type="text"  class="form-control"  name="phone2"><br>
�ּ�(�����ȣ)<input type="text" size="150"  class="form-control" value="${mdto.member_addr }"><br>
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
<input type="radio" name="paymethod" value="accou
ntpay">�������Ա�
<br>
���� ����Ʈ: ${mdto.member_point }<br>
 ���ϸ��� ��� <input type="text"  class="form-control" id="usepoint"><button class='btn btn-primary'>����</button><br>

 ��ǰ�ݾ�:${totalcost}-����Ʈ<br><!-- ���â ����ߵɵ� -->
 ��ۺ� ����<br>
 ��ü �ֹ��ݾ�:<br>
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
    <input type="submit" value="�ֹ��Ϸ�">

    </form>
</body>
 <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- Optional JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
 
</html>