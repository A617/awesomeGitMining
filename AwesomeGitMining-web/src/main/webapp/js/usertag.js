$(document).ready(function() {

    var lan = $("#onLan").text();
    var com = $("#onCom").text();


    $("[name='lan']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            lan = text;
            $("[name='lan']").css("background-color","#fff");
            $("[name='lan']").css("color","#666");
            $(this).css("background-color","#2baab1");
            $(this).css("color","#fff");
            $("#current").load("/user/users?pager.offset=0&lan=" + lan+"&com="+com+" #current");
        });

    });


    $("[name='com']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            com = text;
            $("[name='com']").css("background-color","#fff");
            $("[name='com']").css("color","#666");
            $(this).css("background-color","#2baab1");
            $(this).css("color","#fff");
            $("#current").load("/user/users?pager.offset=0&lan=" + lan+"&com="+com+" #current");
        });
    });

});