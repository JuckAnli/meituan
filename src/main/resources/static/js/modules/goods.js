layui.define(['layer', 'element', 'form', 'rate'], function (exports) {
  var layer = layui.layer,
      element = layui.element;
  var rate = layui.rate;
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
  $("#login").on("click", function () {
    window.location.href = "login.html";
  }),
      $(".a").mouseenter(function () {
        $(this).css("color", "#2bb8aa");
      }),
      $(".a").mouseleave(function () {
        $(this).css("color", "#999");
      }),
      rate.render({
        elem: '#test4',
        value: 4.5,
        half: true,
        text: true,
        setText: function (value) {
          this.span.text(value + "分");
        }
      }),
      layui.each($(".my_rate"), function (index, elem) {
        rate.render({
          elem: elem,
          value: 4.5,
          half: true,
          readonly: true,
        })
      }),
      $(window).on('load', function () {
        goodsName = getQueryVariable("goodsName");
        title = decodeURI(goodsName);
        $.get('./goodsInfo', {"goodName": title}, function (result) {
          id = result.data.id;
          $("#name").text(result.data.goodsName);
          $("#address").text(result.data.address);
          $("#tel").text(result.data.tel);
          $("#businessTime").text(result.data.businessTime);
          $("#goodsImg").attr("src", "/img/" + result.data.img);
          $.get('./goodsGroupByGoodsId', {"id":id}, function (result) {
            console.log(result);
            $.each(result.data, function(i, o){
              console.log(o);
              index = "#" + i;
              $(index).find(".img").attr("src", "/img/" + o.img);
              $(index).find(".span1").text(o.title);
              $(index).find(".span2").text("已售" + o.soldNums);
              $(index).find(".span3").text(o.price);
              $(index).find(".span4").text("门店价" + o.oldPrice);
            })
          })
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
      $(".button").on("click", function () {
        goodName = $(this).parents(".info").find(".group-p").find(".span1").text();
        $.get("./session",function (result) {
          if(result.state){
            url = "buy.html?goodName="+goodName;
            window.location.href = url;
          }else {
            layer.msg("您还没有登录，请先登录！");
          }
        })
      }),
      exports('goods', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});