layui.define(['layer', 'table', 'form'], function(exports) {
	var layer = layui.layer,
		table = layui.table;
	//第一个实例
	table.render({
		elem: '#demo',
		height: 440,
		width: 700,
		url: './car' //数据接口
			,
		page: true //开启分页
			,
		cols: [
			[ //表头
				{
					field: 'goodsName',
					title: '待付款商品',
					width: 200,
					fixed: 'left'
				}, {
					field: 'price',
					title: '单价',
					width: 80
				}, {
					field: 'nums',
					title: '数量',
					width: 80,
				}, {
					field: 'time',
					title: '加入时间',
					width: 100
				}, {
					field: 'total',
					title: '须付金额',
					width: 100
				}, {
					fixed: 'right',
					title: '操作',
					width: 120,
					toolbar: '<div class="layui-table-cell laytable-cell-1-0-11"><a class="layui-btn layui-btn-xs" lay-event="pay">付款</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></div>'
				}
			]
		]

	}),
	table.on('tool(test)', function(obj) {
		console.log(obj);
		switch(obj.event) {
			case 'del':
				layer.open({
				type: 1,
				offset: 'auto', //具体配置参考：offset参数项
				content: '<div style="padding: 20px 80px;">确定删除</div>',
				btn: '确定',
				btnAlign: 'c' //按钮居中
					,
				shade: [0.8, '#393D49'] //不显示遮罩
					,
				yes: function() {
					$.post("./delete", {"id" : obj.data.id}, function(result){
						layer.msg(result.data.msg);
					})
					layer.closeAll();
					window.location.reload();
				}
			})
				break;
			case 'pay':
				//判断用户金额是否充足
        $.get('./money', function (result) {
          if(result.state){
          	if(parseInt(result.data.money) >= parseInt(obj.data.total)){
              layer.open({
                type: 1,
                offset: 'auto', //具体配置参考：offset参数项
                content: '<div style="padding: 20px 80px;">确定支付</div>',
                btn: '确定',
                btnAlign: 'c' //按钮居中
                ,
                shade: [0.8, '#393D49'] //不显示遮罩
                ,
                yes: function() {
                	var car = {
                		"id" : obj.data.id,
										"total" : obj.data.total,
										"goodsName" : obj.data.goodsName,
										"nums" : obj.data.nums
									};
                  $.post("./pay", car, function (result) {
										if(result.state){
                      layer.closeAll();
											layer.msg("支付成功！");
											window.location.reload();
										}else {
                      layer.closeAll();
											layer.msg(result.msg);
											window.location.reload();
										}
                  })
                }
              })
						}else {
          		layer.msg("余额不足，请充值！");
						}
					}else {
          	layer.msg(result.data.msg);
					}
        });
				break;
		};
		
	});

	exports('pay', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});