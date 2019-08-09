layui.define(['layer', 'table', 'form'], function(exports) {
	var layer = layui.layer,
		table = layui.table;
	//第一个实例
	table.render({
		elem: '#demo',
		height: 440,
		width: 660,
		url: './payCar' //数据接口
			,
		page: true //开启分页
			,
		cols: [
			[ //表头
				{
					field: 'goodsName',
					title: '我的商品',
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
					title: '购买时间',
					width: 100
				}, {
					field: 'total',
					title: '实付款',
					width: 100
				}, {
					fixed: 'right',
					title: '操作',
					width: 80,
					toolbar: '<div class="layui-table-cell laytable-cell-1-0-11"><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a></div>'
				}
			]
		]

	}),
	table.on('tool(test)', function(obj) {
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
	});

	exports('order', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});