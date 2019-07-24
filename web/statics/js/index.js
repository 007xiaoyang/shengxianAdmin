
/*
$.validator.setDefaults({
    submitHandler: function() {
		var http = WEBPATH+'Index/login';
		$(".btn-block").attr("disabled",true);
		var param = $("#signupForm").serialize(); 
		$.ajax({ 
			url : http, 
			type : "post", 
			dataType : "json", 
			data: param, 
			success : function(result){ 
				if(result.result == '0'){ 
					location.href=result.data; 
					return false;
				}
				$(".verifyimg").attr("src", $(".verifyimg").attr("src").replace(/\?.*$/,'')+'?'+Math.random());
				$("#verify").val("");
				layer.alert(result.msg,{time: 2000, icon:2});
				$(".btn-block").attr("disabled",false);
			}
			
		});
    }
});
$(document).on("click",".btn-default",function(){
	  //刷新验证码
	var verifyimg = WEBPATH+'Index/verify';
	if( verifyimg.indexOf('?')>0){
		$(".verifyimg").attr("src", verifyimg+'&random='+Math.random());
	}else{
		$(".verifyimg").attr("src", verifyimg.replace(/\?.*$/,'')+'?'+Math.random());
	}
})
*/
