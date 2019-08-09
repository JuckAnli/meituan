layui.define(['layer', 'table', 'form'], function(exports) {
	var layer = layui.layer,
		table = layui.table;
	//第一个实例
	table.render({
		elem: '#demo',
		height: 440,
		width: 700,
		url: './allCar' //数据接口
			,
		page: true //开启分页
			,
		cols: [
			[ //表头
				{
					field: 'name',
					title: '用户',
					width: 100,
					fixed: 'left'
				}, {
					field: 'goodsName',
					title: '商品',
					width: 150,
				}, {
					field: 'price',
					title: '单价',
					width: 150,
				}, {
					field: 'nums',
					title: '数量',
					width: 200,
				}, {
					fixed: 'right',
					title: '操作',
					width: 120,
					toolbar: '<div class="layui-table-cell laytable-cell-1-0-11"><a class="layui-btn layui-btn-xs" lay-event="edit">修改</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></div>'
				}
			]
		]

	}),
	table.on('tool(test)', function(obj) {
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
					$.post("./delete", {"id" : obj.data.id}, function (result) {
						layer.msg(result.msg);
          })
					layer.closeAll();
          window.location.reload();
				}
			})
				break;
			case 'edit':
				layer.open({
				title: '用户订单修改',
						type: 2,
						area:['600px','400px'],
						offset: 'auto', //具体配置参考：offset参数项
						content: 'userOrders.html?id=' + obj.data.id,
						btn: '',
						btnAlign: 'c' //按钮居中
							,
						shade: [0.8, '#393D49'] //显示遮罩
							,
						success: function(layero, index) {
							var data = obj.data;//获取当前行的数据
							var body = layer.getChildFrame('body', index);
							body.find("#goods").val(data.goodsName);
    						body.find("#price").val(data.price);
    						body.find("#num").val(data.nums);
    						body.find("#name").val(data.name);
						}
			})
				break;
		};
		
	});

	exports('userOrder', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});