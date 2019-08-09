layui.define(['layer', 'form'], function (exports) {
  var layer = layui.layer,
      form = layui.form;
  $("#a").mouseenter(function () {
    $("#a").css("color", "red");
  }),
      $("#a").mouseleave(function () {
        $("#a").css("color", "#2bb8aa");
      }),
      //submit 监听
      form.on('submit(login)', function (data) {
        console.log(data.field);
        $.post('./login', data.field, function (result) {
          console.log(result);
          if (result.state) {
            layer.msg('登录成功');
            //location.href = "index.html";
            //判断角色跳转不同页面
            /*if(result.data.role == 0){
              location.href = "user.html";
            }
            if(result.data.role == 1){
              location.href = "admin.html";
            }*/
            /*history.back(-1);*/
            self.location = document.referrer;
          } else {
            layer.msg(result.msg);
          }
          console.log(result);
        })
        return false;//拦截layui自带的提交
      }),
      $("#img").on("click", function () {
        window.location.href = "index.html";
      }),
  exports('login', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});