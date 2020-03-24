<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
<style>
.orderInfo{
display:none;}
</style>
</head>
<body>

<form method="post" action="/order_cart">
<h2>${memberid }���� ��ٱ���<br></h2>

<input type="hidden" name="member_id" value="${memberid }" id="member_id"> 
<table>
<thead>
<tr>
<th>����</th><th>��ǰ��ȣ</th><th></th><th>��ǰ��</th><th>������ȣ</th><th>����</th><th>����</th>
</tr>
</thead>
<tbody>
<c:forEach var="item" items="${list}">
<tr>
<td><i class="fas fa-times" value="${item.product_no}" id="${item.product_no}" ></i></td>
<td>${item.product_no }</td>
<td><a href="${pageContext.request.contextPath}/ej_store_detail/${item.product_no}"><img src="${item.product_thumb}" alt="thumb"></a></td>
<td><a href="${pageContext.request.contextPath}/ej_store_detail/${item.product_no}">${item.product_name }</a></td>
<td>${item.lesson_no }</td>
<td>${item.product_after_cost }</td>
<td>${item.cart_amount }</td>
</tr>
</c:forEach> 
</tbody>
</table>
<hr>
<!-- <section id="checkedlist">
<th>��ǰ��</th><th>����</th><th>����</th>
</section> -->

<input type="button" class='btn btn-primary' id="open_orderform_btn"value="�ֹ��ϱ�"><br>
<div class="orderInfo">
�ֹ��� ���� div
<button class="cancel_btn">���</button>
</div>
<input type="submit" class='btn btn-primary' value="�ֹ��ϱ�2">
</form>
<script>
 $("#open_orderform_btn").click(function(){
  $(".orderInfo").slideDown();
  $("#open_orderform_btn").slideUp();
 }); 
 
 $(".cancel_btn").click(function(){
	 $(".orderInfo").slideUp();
	 $(".orderOpne_bnt").slideDown();
	});   
</script>
<script>
$(".fas.fa-times").on('click',function(){
	
	console.log(this);
	var product_no=$(this).prop('id');
	var member_id='${memberid }';
	var data={
			product_no: product_no,
			member_id: member_id
	}
	
	$.ajax({
	      url:"/deletecart_json",
	      type:"post",
	      data: data,
	      success: function(){
	    	  console.log(data);
	       /*   
	         var cartlist="";
	            cartlist+="<tr><td>"+data.product_name+"</td>"
	            cartlist+="<td><input type='text' value="+data.cart_amount+"></td></tr>"
	         console.log(cartlist);
	            $('#checkedlist').append(cartlist); */
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