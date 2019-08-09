layui.define(['layer', 'form'], function(exports) {
	var layer = layui.layer,
		form = layui.form;
	/*$("#pwd").blur(function() {
			//创建正则
			var reg = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z]).*$/;
			//获得用户输入的内容
			var con = $(this).val();
			var result = reg.test(con);
			if(!result) {
				layer.tips('请输入最少6位，包括至少1个大写字母，1个小写字母，1个数字', '#pwd', {
					tips: [1, '#f5321c'],
					time: 2000
				});
			}
		}),*/
  function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
      var pair = vars[i].split("=");
      if (pair[0] == variable) {
        return pair[1];
      }
    }
    return (false);
  };
		//定义校验规则
		form.verify({
		pass:function(value, item){
			if(!/^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z]).*$/.test(value)){
				return '密码最少6位，包括至少1个大写字母，1个小写字母，1个数字';
			}
		}
		
	}),
		form.on('submit(formDemo)', function(data) {
      id = getQueryVariable("id");
			var user = {
				"id" : id,
				"name" : data.field.name,
				"tel" : data.field.phone,
				"email" : data.field.email,
        "money" : data.field.money,
				"password" : data.field.password
			}
			$.post("./changeUser", user, function(result){
				layer.msg(result.msg);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index); //关闭  iframe
				window.parent.location.reload();
			})
			return false; //拦截layui自带的提交
		})
	exports('usersinfo', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});