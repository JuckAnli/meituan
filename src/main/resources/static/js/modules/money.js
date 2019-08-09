layui.define(['layer', 'form'], function(exports) {
	var layer = layui.layer,
		form = layui.form;
	$("#out").on("click", function() {
			layer.open({
				type: 1,
				offset: 'auto', //具体配置参考：offset参数项
				content: '<div style="padding: 20px 80px;">确定充值</div>',
				btn: '确定',
				btnAlign: 'c' //按钮居中
					,
				shade: [0.8, '#393D49'] //不显示遮罩
					,
				yes: function() {
					var money = $("#data").text()
					$.post('./money', {"money": money}, function (result) {
						console.log(result);
						layer.msg(result.msg);
						window.location.reload();
          })
					layer.closeAll();
				}
			})
		}),
		$(".check").on("click", function() {
			$(".check").css("border", "2px solid #777777");
			$(this).css("border", "2px solid #FFB800");
			$("#data").text($(this).text());
		}),
		$("#input").on("blur", function() {
			if($(this).val().length==0 || $(this).val()==""){
				$("#data").text(0);
			}else{
				$("#data").text($(this).val());
			}
			}),
		$("#input").on("focus", function() {
			$(".check").css("border", "2px solid #777777");
		}),
      $(window).on('load', function () {
        $.get('./money', function (result) {
          console.log(result);
         $("#blance").text(result.data.money);
        })
      }),
	exports('money', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});