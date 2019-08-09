layui.define(['layer', 'form'], function(exports) {
	var layer = layui.layer,
		form = layui.form;
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
	form.on('submit(formDemo)', function(data) {
    id = getQueryVariable("id");
		var car = {
			"id" : id,
			"goodsName" : data.field.goodsName,
			"price" : data.field.price,
			"nums" : data.field.nums
		};
		console.log(data.field);
		$.post("./changeCar", car, function(result){
			layer.msg(result.msg);
      var index = parent.layer.getFrameIndex(window.name);
      parent.layer.close(index); //关闭  iframe
      window.parent.location.reload();
		});
		return false; //拦截layui自带的提交
	})
	exports('userOrders', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});