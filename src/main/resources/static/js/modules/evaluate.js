layui.define(['layer', 'table', 'form'], function(exports) {
	var layer = layui.layer,
		table = layui.table;
	//第一个实例
	table.render({
			elem: '#demo',
			height: 440,
			width: 700,
			url: './goodsGroup' //数据接口
				,
			page: true //开启分页
				,
			cols: [
				[ //表头
					{
						field: 'title',
						title: '套餐名',
						width: 302,
						fixed: 'left'
					}, {
						field: 'price',
						title: '套餐价',
						width: 80,
					}, {
						field: 'oldPrice',
						title: '店面价',
						width: 80,
					}, {
						field: 'soldNums',
						title: '销售数量',
						width: 100
					},{
						fixed: 'right',
						title: '操作',
						width: 120,
						toolbar: '<div class="layui-table-cell laytable-cell-1-0-11"><a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a></div>'
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
							layer.closeAll();
						}
					})
					break;
				case 'edit':
					layer.open({
						title: '套餐修改',
						type: 2,
						area:['600px','400px'],
						offset: 'auto', //具体配置参考：offset参数项
						content: 'text.html?id=' + obj.data.id,
						btn: '',
						btnAlign: 'c' //按钮居中
							,
						shade: [0.8, '#393D49'] //显示遮罩
							,
            success: function(layero, index) {
              var data = obj.data;//获取当前行的数据
              var body = layer.getChildFrame('body', index);
              body.find("#title").val(data.title);
              body.find("#price").val(data.price);
              body.find("#oldPrice").val(data.oldPrice);
              body.find("#soldNums").val(data.soldNums);
						}
					})
					break;
			};

		});

	exports('evaluate', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});