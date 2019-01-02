/**
 * Created by 徐楚烜 on 2017-05-28.
 */
$(function () {

    $(".cat-div").hover(function () {

        $(this).css("background-color", "#878787");
        $(".cat-detail").css("display", "inline-block");
    }, function () {
        $(this).css("background-color", "#6e6568");
        $(".cat-detail").css("display", "none");
    });
    $(".cat-detail").hover(function () {
        $(this).css("display", "inline-block");
    }, function () {
        $(this).css("display", "none");
    });

    $(".detail-li").hover(function () {

        $(this).css("background-color", "#878787");
    }, function () {
        $(this).css("background-color", "#6e6568");
    });

    $(".carousel-indicators>li").hover(function () {
        var target = $(this).attr("data-slide-to");
        var tar = parseInt(target);
        $(".carousel-indicators>li").removeClass("active");
        $(this).addClass("active");
        $("#myCarousel").carousel(tar);
    }, function () {

    });
    $(".myTab-discount").hover(function () {
        $(".discount").css("display", "block");
        $(".notice").css("display", "none");
        $(".myTab-notice").css("border-bottom", "none");
        $(this).css("border-bottom", "1px solid #db192a");

    }, function () {

    });
    $(".myTab-notice").hover(function () {
        $(".notice").css("display", "block");
        $(".discount").css("display", "none");
        $(".myTab-discount").css("border-bottom", "none");
        $(this).css("border-bottom", "1px solid #db192a");

    }, function () {

    });
    $("#myCarousel2").hover(function () {
        $(".carousel2-nav").css("display","block");
    },function () {
        $(".carousel2-nav").css("display","none");
    });
});