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
<script type="text/javascript" src="resources/JS/yi_findAddr.js"></script>
</head>
<body>
<h1 text align="center">�ֹ���û��</h1>
<h2>�ֹ��� ���</h2>
<hr>
 <%-- ${product_no } --%>
 <img src="${pdto.product_img}" id="ej_order_topimg" width="200">
<h3>${product_name }</h3><br>


 ����: ${pdcount } ��<br>
 ��ü�ݾ�:
 ${product_cost}��X${pdcount}
<%--  �α��ξ��̵�:${member_id}
${mdto.member_id }
${mdto.member_addr } --%>
 
 <section class="total" >
 </section><br>
 
 <h2>�ֹ��� ����</h2><hr>
 �ֹ��ڸ�   <input type="text" value=${mdto.member_name }><br>
 �̸���   <input type="text" value=${mdto.member_email }><br>
����ó   <input type="text" value=${mdto.member_phone }><br>

 <h2>����� ����</h2><hr>
 <input type="radio" name="deliveryaddr" value="same" checked="checked">�ֹ��������� ����
<input type="radio" name="deliveryaddr" value="newaddr">���ο� �����<br>

�����ڸ�<input type="text" value=${mdto.member_name }><br>
�޴���ȭ<input type="text" value=${mdto.member_phone }><br>
�߰���ȣ(����)<input type="text" name="phone2"><br>
�ּ�(�����ȣ)<input type="text" size="150" value="${mdto.member_addr }"><br>
üũ(�⺻������� ���峪�߿� �߰�)<br>
��ۿ�û����<textarea rows="5" cols="100"></textarea><br>
---------------���ܿ����ȣ---------<br>
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

<br>
 <h2>����� ����</h2><hr>
 �������� ����
  <input type="radio" name="paymethod" value="same">�ſ�ī��
<input type="radio" name="paymethod" value="newaddr">īī������
<input type="radio" name="paymethod" value="newaddr">������
<input type="radio" name="paymethod" value="newaddr">�������Ա�
<br>
 ���ϸ��� ��� <input type="text" ><button>����</button><br>
 ��ǰ�ݾ�:${total}<br>
 ��ۺ� ����<br>
 ��ü �ֹ��ݾ�:<br>
<button>�����ϱ�</button>
���Ͷ��::${pageContext.request.contextPath}

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <!-- Optional JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>/* �Ѱ��ݰ��(�������� �����Ұ�) */
 var cost=${product_cost};
 var cnt=${pdcount};
 var total=cost*cnt;

 $("section.total").html(+total+'��');
 //�����۽� ��ߵǳ�?
 </script>
</body>
 
</html>