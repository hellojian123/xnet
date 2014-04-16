$(document).ready(function () {
    $(".four").find("a").mousemove(function () {
        $(this).addClass("curren1");

    });
    $(".four").find("a").mouseout(function () {
        $(this).removeClass("curren1");
        $("#f_a_one").addClass("curren1");
    });
//    $(".button-register").click(function () {
//           
//            $(".rotator").fadeOut(500);
//            $(".Login_two_l").fadeIn(4000);
//            $(".login_one").css("height", "500px");  
//    });

});
