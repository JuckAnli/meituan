layui.define(['layer', 'table', 'form'], function(exports) {
	var layer = layui.layer,
		table = layui.table;
	//第一个实例
	table.render({
		elem: '#demo',
		height: 440,
		width: 700,
		url: './userInfo' //数据接口
			,
		page: true //开启分页
			,
		cols: [
			[ //表头
				{
					field: 'name',
					title: '昵称',
					width: 100,
					fixed: 'left'
				}, {
					field: 'email',
					title: '邮箱',
					width: 150,
				}, {
					field: 'tel',
					title: '电话',
					width: 150,
				}, {
					field: 'password',
					title: '密码',
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
					$.post("./deleteUser", {"id": obj.data.id}, function(result){
						layer.msg(result.msg);
					})
					layer.closeAll();
				}
			})
				break;
			case 'edit':
				layer.open({
				title: '修改用户信息',
						type: 2,
						area:['600px','400px'],
						offset: 'auto', //具体配置参考：offset参数项
						content: 'usersinfo.html?id=' + obj.data.id,
						btn: '',
						btnAlign: 'c' //按钮居中
							,
						shade: [0.8, '#393D49'] //显示遮罩
							,
						success: function(layero, index) {
							var data = obj.data;//获取当前行的数据
							var body = layer.getChildFrame('body', index);
						/*	var iframeWin = window[layero.find('iframe')[0]['name']];*/
								body.find("#name").val(data.name);
    						body.find("#tel").val(data.tel);
    						body.find("#email").val(data.email);
    						body.find("#pwd").val(data.password);
							  body.find("#money").val(data.money);
							
							
							/*var data =$(layero).find("iframe")[1].contentWindow.document.getElementById("msg");
							console.log(data);*/
							
						}
			})
				break;
		};
		
	});

	exports('users', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});