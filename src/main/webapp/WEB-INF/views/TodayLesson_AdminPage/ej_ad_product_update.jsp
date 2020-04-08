<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
<!-- include summernote-ko-KR -->
 <script src="/resources/JS/summernote-ko-KR.js"></script>
<%--  <script src = "https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/JS/hm_ad_event_update.js"></script> --%>
<title>Insert title here</title>
 <script>
 	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : 'content',
			minHeight : 370,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR', 
		
		});
		
		$('#product_thumbnail').on('click',function(){
			$(this).removeAttr("style");
		});
	

		
}); 
</script> 
<style>
 .ej_btn{
border: 1px transparent;
padding: 5px 15px; 
background-color: rgb(224, 62, 82);
height:40px;
font-weight : 600;
font-size : 14px;
color: white;

}
</style>
</head>
<body>
	<h2 style="text-align: center;">제품정보 수정</h2>
	<br>
	<br>
	<br>

	<div style="width: 80%; margin: auto;">
		
		<form role="form"  name="productupdateform" method="post" autocomplete="off" enctype="multipart/form-data"'
		 action="${pageContext.request.contextPath}/todaylessonadmin/ad_product_update_result"> 
		<input type ="hidden" id="product_no" name="product_no" value="${dto.product_no}">
		<br>
		<label>카테고리</label>
		<select name="product_category">
		<option value="1">외국어</option>
		<option value="2">IT</option>
		<option value="3">요리</option>
		<option value="4">DIY</option>
		<option value="5">운동</option>
		<option value="6">기타</option>
		</select>
		<br>
		<label>제품명</label><br>
		 <input type="text" name="product_name" style="width: 40%;" value="${dto.product_name }" required="required"/> <br>
			<br>
				<label>가격</label><br>
				 <input type="text" name="product_cost" style="width: 40%;" placeholder="가격"  value="${dto.product_cost }" required="required"/>원<br>
				 
				  <label>할인율</label><br>
				 <input type="text" name="product_sale" style="width: 40%;" value="${dto.product_sale }" placeholder="%" required="required"/>%<br>
			
				 <label>수량</label><br>
				 <input type="text" name="product_stock" style="width: 40%;" value="${dto.product_stock }" placeholder="수량" required="required"/>개<br><br>
				<%-- 
				<label>상세설명</label><br>
				<textarea id="summernote" name="product_content"  value="${dto.product_content }"></textarea>
	 --%>
	 
	 <textarea id="summernote" name="product_content" style="display:none"><c:out value="${dto.product_content}"/></textarea>
			<div class="inputArea">

<br>
 <label for="product_thumbnail">썸네일 수정</label>
 <input type="file" id="product_thumbnail" name="file" style="width: 80px;"/>
 <br>
 <div class="select_img"><img src="${dto.product_thumb}" /></div>
 <br>

 <script>
  $("#product_thumbnail").change(function(){
   if(this.files && this.files[0]) {
    var reader = new FileReader;
    reader.onload = function(data) {
     $(".select_img img").attr("src", data.target.result).width(300);        
    }
    reader.readAsDataURL(this.files[0]);
   }
  });
 </script>
 <br><br>
<%--  <li style="list-style: none;">이벤트 간단설명</li>
 <textarea rows="5" cols="40" id="event_thumbexplain" name="event_thumbexplain" maxlength="100">${dto.event_thumbexplain}</textarea> --%>
<br>
<br>
 
 
<br>
 <input type="button" id="product_update_btn"  onclick="updatethumbnail();"  value="상품 수정" style="float: right;" class="ej_btn"/>
  <script>
  
  function updatethumbnail(){
	  
	var updateform = document.productupdateform;  
	var thumbnail =  updateform.file.value;
	var newthumbnail = document.getElementById('product_thumbnail');
	console.log(thumbnail);
	console.log(newthumbnail);
	if(thumbnail==null){
		newthumbnail.disabled = true;
		console.log(newthumbnail);
		updateform.submit();
	}else{
		updateform.submit();	
	}
  }
  </script>
  
  <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}" />
</div>
			
		</form>
	</div>


</body>
</html>