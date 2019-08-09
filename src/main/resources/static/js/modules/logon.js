layui.define(['layer', 'form'], function(exports) {
	var layer = layui.layer,
	form = layui.form;
	$("#a").mouseenter(function(){
		$("#a").css("color","red");
	}),
	$("#a").mouseleave(function(){
		$("#a").css("color","#2bb8aa");
	}),
	/*$("#submit").mouseenter(function(){
	var pwd = $("#pwd").val();
	console.log(pwd);
	var pwd2 = $("#pwd2").val();
	console.log(pwd2);
		if(pwd != pwd2){
			layer.tips('两次输入的密码不一致', '#pwd2', {
				tips: [1, '#f5321c'],
				time: 4000
			});
		}
	}),
	$("#pwd").blur(function(){
		//创建正则
		var reg = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z]).*$/;
			//获得用户输入的内容
			var con = $(this).val();
			var result = reg.test(con);
			if(!result){
				layer.tips('请输入最少6位，包括至少1个大写字母，1个小写字母，1个数字', '#pwd', {
				tips: [1, '#f5321c'],
				time: 2000
			});
			}
	})*/
	//定义表单验证规则
	form.verify({
		pass:function(value, item){
			if(!/^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z]).*$/.test(value)){
				return '密码最少6位，包括至少1个大写字母，1个小写字母，1个数字';
			}
		},
		eq:function(value, item){
			if(value != $("#pwd").val()){
				return '两次输入的密码不一致';
			}
		}
	}),
	//submit 监听
	form.on('submit(login)',function(data){
		console.log(data.field);
		$.post('./userInfo', data.field, function(result){
			if(result.state){
				layer.msg('注册成功');
        location.href="index.html";
			}else {
				layer.msg(result.msg);
			}
			console.log(result);
		})
		return false;//拦截layui自带的提交
	}),
      $("#logo").on("click", function () {
        window.location.href = "index.html";
      }),
	exports('logon', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});