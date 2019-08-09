layui.define(['layer', 'element', 'form'], function(exports) {
	var layer = layui.layer,
		element = layui.element;
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
	$("#login").on("click", function() {
			window.location.href = "login.html";
		}),
		$(".a").mouseenter(function() {
			$(this).css("color", "#2bb8aa");
		}),
		$(".a").mouseleave(function() {
			$(this).css("color", "#999");
		}),
	$("#reduce").on("click", function(){
		var num = $("#input").val();
		var price = $("#price").text();
		if(num == 0){
			$("#total").text(parseInt(num)*price);
			layer.msg('数量为0不能再减了！');
		}else{
			num = num - 1;
			$("#input").val(num);
			$("#total").text(parseInt(num)*price);
		}
	}),
	$("#add").on("click", function(){
		var num = $("#input").val();
		num = parseInt(num)+1;
		$("#input").val(num);
		var price = $("#price").text();
		$("#total").text(parseInt(num)*price);
	}),
      $(window).on('load', function () {
        goodsName = getQueryVariable("goodName");
        title = decodeURI(goodsName);
        $.get('./goodsGroupByGoodsName', {"name": title}, function (result) {

          $("#goods-img").attr("src", "/img/" + result.data.img);
          $("#title").text(result.data.title);
          $("#price").text(result.data.price);
          $("#total").text(result.data.price);
        });
        $.get('./session', function (result) {
          if(result.state){
            $("#myName").text(result.data.name);
            $("#myName").on("click", function(){
              if(result.data.role == 0){
                location.href = "user.html";
              }else {
                location.href = "admin.html";
              }
            })
          }
        });
      }),
      $("#img").on("click", function () {
        window.location.href = "index.html";
      }),
			$("#button").on("click", function () {
        var goodsName = $("#title").text();
        var price = $("#price").text();
        var nums = $("#input").val();
        var total = $("#total").text();
        var data = {
          goodsName : goodsName,
          price : price,
          nums : nums,
          total : total
				};
        //nums为0时不让购买
				if(nums == 0){
					layer.msg("您还没添加商品！")
				}else {
          $.post("./car", data, function (result) {
            if(result.state){
              layer.msg("购物车添加成功！");
            }
          })
				}
      })
	exports('buy', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});