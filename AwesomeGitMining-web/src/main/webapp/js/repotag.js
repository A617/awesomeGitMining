$(document).ready(function() {

    var lan = $("#onLan").text();
    var year = $("#onYear").text();
    var key = $("#onKey").text();


    $("[name='lan']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            lan = text;
            $("[name='lan']").css("background-color","#fff");
            $("[name='lan']").css("color","#666");
            $(this).css("background-color","#2baab1");
            $(this).css("color","#fff");
            $("#current").load("/repo/repos?pager.offset=0&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });

    });
    

    $("[name='key']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            key = text;
            $("[name='key']").css("background-color","#fff");
            $("[name='key']").css("color","#666");
            $(this).css("background-color","#2baab1");
            $(this).css("color","#fff");
            $("#current").load("/repo/repos?pager.offset=0&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });
    });

    $("[name='year']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            year = text;
            $("[name='year']").css("background-color","#fff");
            $("[name='year']").css("color","#666");
            $(this).css("background-color","#2baab1");
            $(this).css("color","#fff");
            $("#current").load("/repo/repos?pager.offset=0&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });
    });
    

});