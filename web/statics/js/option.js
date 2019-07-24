$(document).on("click",".btn-whites",function(){
	$("#fileuploads").click();
})
$(document).ready(function(){$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"})});
$("#fileuploads").wrap("<form id='myuploads' action='"+WEBPATH+'Index/avatar'+"' method='post' enctype='multipart/form-data'></form>"); 
$("#fileuploads").change(function(){ //选择文件 
	$("#myuploads").ajaxSubmit({ 
		dataType:  'json', //数据格式为json 
		success: function(data) { //成功 
			if(data.result == 0){
				$("#avatar").attr("src",data.data);
				$(".fancybox").attr("href",data.data);
				$("#avatar_input").val(data.data);
				
			}else{
				toastr.error(data.msg);
			}
		}, 
		error:function(xhr){ //上传失败 
			toastr.error("上传失败");
		} 
	}); 
}); 
function handle_coupon_total_price(obj) {
	coupon_total_price = obj.value;
	if ("" == coupon_total_price) {
		obj.value = "0.00"
	}
	if ("" != coupon_total_price.replace(/\d{1,}\.{0,1}\d{0,}/, "")) {
		coupon_total_price = coupon_total_price.match(/\d{1,}\.{0,1}\d{0,}/) == null ? "" : coupon_total_price.match(/\d{1,}\.{0,1}\d{0,}/);
		obj.value = coupon_total_price
	} else {
		if (coupon_total_price.indexOf(".") > -1) {
			str = coupon_total_price.indexOf(".");
			coupon_total_price = coupon_total_price.substring(0, str + 3);
			if (coupon_total_price) {
				obj.value = coupon_total_price
			}
		}
	}
}