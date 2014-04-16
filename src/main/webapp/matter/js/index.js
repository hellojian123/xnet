$(document).ready(function () {
    $(".four").find("a").mousemove(function () {
        $(this).addClass("curren1");
    });
    $(".four").find("a").mouseout(function () {
        $(this).removeClass("curren1");
        //        $("#f_a_one").addClass("curren1");
    });
    $(".tab_li").find("a").css("color", "White");
    $(".w_span").click(function () {
        // $(this).css("display", "none");
        $(this).parent().css("display", "none");
    });
    $(".w_span").mousemove(function () {
        this.src = 'images/logo/close2.gif';
    });
    $(".w_span").mouseout(function () {
        this.src = 'images/logo/close1.gif';
    });
    $(".rank_list").find("ul").find("li").click(function () {
        var href_1 = $(this).find("a").attr("href");  //找到a标签的href元素
        self.location = href_1;
    });
    $(".mod_city_con").mousemove(function () {
        $(this).find("div.mod_city_list").css("display", "block");
    });    //搜索
    $(".mod_city_con").mouseout(function () {
        $(this).find("div.mod_city_list").css("display", "none");
    });
    $(".mod_city_list").find("ul").find("li").mouseover(function () {
        $(".mod_city_list").find("ul").find("li").find("a").css("color", "#333");
        $(this).find("a").css("color", "White");
    });
    $(".mod_city_list").find("ul").find("li").mouseout(function () {
        $(".mod_city_list").find("ul").find("li").find("a").css("color", "#333");
    });
    $(".mod_city_list").find("ul").find("li").click(function () {
        var txt = $(this).find("a").html();
        $(this).parent().parent().parent().parent().find("a").find("span").text(txt);
    });
});
