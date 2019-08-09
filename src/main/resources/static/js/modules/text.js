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
		form.on('submit(formDemo)',function(data){
			var id = getQueryVariable("id");
			var goods = {
				"id" : id,
				"title" : data.field.title,
				"price" : data.field.price,
				"oldPrice" : data.field.oldPrice,
				"soldNums" : data.field.soldNums
			}
			$.post("./updateGoodsGroup", goods, function (result) {
				layer.msg(result.msg);
        window.parent.location.reload();
      })
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index); //再执行关闭  
		return false;//拦截layui自带的提交
	})
	exports('text', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});