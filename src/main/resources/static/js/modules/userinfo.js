layui.define(['layer', 'form'], function (exports) {
  var layer = layui.layer,
      form = layui.form;
  //submit 监听
  form.on('submit(user)', function (data) {
    console.log(data.field);
    $.post('./change', data.field, function (result) {
      console.log(result);
      layer.msg(result.msg);
      window.location.reload();
    })
    return false;//拦截layui自带的提交
  }),
      form.on('submit(change)', function (data) {
        console.log(data.field);
        $.post('./changepwd', data.field, function (result) {
          console.log(result);
          layer.msg(result.msg);
          window.location.reload();
        })
        return false;//拦截layui自带的提交
      }),
      /*$("#submit").mouseenter(function(){
      var pwd = $("#pwd").val();

      var pwd2 = $("#pwd2").val();

        if(pwd != pwd2){
          layer.tips('两次输入的密码不一致', '#pwd2', {
            tips: [1, '#f5321c'],
            time: 4000
          });
        }
      }),*/
      //定义form表单验证规则
      form.verify({
        pass: function (value, item) {
          if (!/^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z]).*$/.test(value)) {
            return '密码最少6位，包括至少1个大写字母，1个小写字母，1个数字';
          }
        },
        eq: function (value, item) {
          if (value != $("#pwd").val()) {
            return '两次输入的密码不一致';
          }
        }
      }),
      $(window).on('load', function () {
        $.get('./session', function (result) {
          /*console.log(result);*/
          form.val("userinfo", {
            "name": result.data.name // "name": "value"
            , "sex": result.data.sex
            , "tel": result.data.tel
            , "email": result.data.email
            , "date": result.data.birth
          })
          /*$("#name").attr("value", result.data.name);
          $("#tel").attr("value", result.data.tel);
          $("#email").val(result.data.email);
          $("input[name=sex][value='1']").attr("checked", result.data.sex == 1 ? true : false);
          $("input[name=sex][value='2']").attr("checked", result.data.sex == 2 ? true : false);
          if(result.data.birth != null){
            $("#date").val(result.data.birth);
          }
          form.render();*/
        })
      }),
      /*$("#pwd").blur(function(){
        //创建正则
        var reg = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z]).*$/;
          //获得用户输入的内容
          var con = $(this).val();
          var result = reg.test(con);
          if(!result){
            layer.tips('请输入最少6位，包括至少1个大写字母，1个小写字母，1个数字', '#pwd', {
            tips: [1, '#f5321c'],
            time: 2000
          });
          }
      })*/
      exports('userinfo', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});