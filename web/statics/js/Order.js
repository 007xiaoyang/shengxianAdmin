$(".operation .btn-success").click(function(){
		var id = $(this).attr("val");
		layer.open({
		  type: 2,
		  title: '订单详情',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['100%', '100%'],		  
		  //content: WEBPATH+"Order/OrderInfo?id=" + id
		content:"../../OrderInfo.html"
		});
	})
	
	$(".operation .btn-white").click(function() {
		var id = $(this).attr("val");
		layer.confirm("是否删除订单?订单将被删除,无法恢复", {
			btn: ["确定", "取消"]
		}, function() {
			//location.href = WEBPATH+'Order/DelOrder?id=' + id 
			location.href="DelOrder.html"
			
		})
	});
	
	$('#checkbox').click(function(){
		if($(this).is(':checked') == true)
		{
			$('input[name="id"]').prop('checked', true);
			$(".delete").prop('disabled', false);
		}
		else
		{
			$('input[name="id"]').prop('checked', false);
			$(".delete").prop('disabled', true);
		}
	});
	
	$('#checkbox3').click(function(){
		if($(this).is(':checked') == true)
		{
			$(".delete").prop('disabled', false);
		}
		else
		{
			$(".delete").prop('disabled', true);
		}
	});
	
	$(".delete").click(function() {
		getcheck();
	});
	
	function getcheck()
	{
		var orderid = [];
		$('.xuan').find('#checkbox3').each(function(){
			if($(this).is(':checked') == true)
			{
				orderid.push($(this).attr('val'));
			}
			//
		})
		orderid = orderid.join(',');
		
		layer.confirm("是否删除此订单?订单将被删除,无法恢复", {
			btn: ["确定", "取消"]
		}, function() {
			//location.href = WEBPATH+'Order/DelOrder?id=' + orderid
			location.href="../../DelOrder.html"
		});
	}
	





