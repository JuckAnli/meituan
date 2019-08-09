layui.define(['layer', 'element', 'form', 'carousel'], function (exports) {
  var layer = layui.layer,
      element = layui.element;
  var carousel = layui.carousel;
  $("#login").on("click", function () {
    window.location.href = "login.html";
  }),
      $(".a").mouseenter(function () {
        $(this).css("color", "#2bb8aa");
      }),
      $(".a").mouseleave(function () {
        $(this).css("color", "#999");
      }),
      $("li").mouseenter(function () {
        $(this).css("background-color", "#2bb8aa");
      }),
      $("li").mouseleave(function () {
        $(this).css("background-color", "#00F7DE");
      }),
      $(".goods").mouseenter(function () {
        $(this).css("background-color", "#F8F8F8");
      }),
      $(".goods").mouseleave(function () {
        $(this).css("background-color", "white");
      }),
      $(".goods").on("click", function () {
        title = $(this).find(".title").text();
        console.log(title);
        url = "goods.html?goodsName=" + title;
        window.location.href = url;
      })
  carousel.render({
    elem: '#test1',
    width: '100%' //设置容器宽度
    ,
    height: '100%',
    arrow: 'always' //始终显示箭头
    //,anim: 'updown' //切换动画方式
  }),
      $("#img").on("click", function () {
        window.location.href = "index.html";
      }),
      //页面加载时判断是否有session用户
      $(window).on('load', function () {
        $.get('./session', function (result) {
          if(result.state){
            $("#name").text(result.data.name);
            $("#name").on("click", function(){
              if(result.data.role == 0){
                location.href = "user.html";
              }else {
                location.href = "admin.html";
              }
            })
          }
        });
      }),
      exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});