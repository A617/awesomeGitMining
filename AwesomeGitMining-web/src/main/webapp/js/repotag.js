$(document).ready(function() {

    var lan = $("#onLan").text();
    var year = $("#onYear").text();
    var key = $("#onKey").text();

    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }


    $("#maintab").children().each(function() {
        var tabId = $(this).text().trim();
        $(this).click(function() {
            alert("/repo/repos?pager.offset=0&sort=" + tabId + "");
            $("#current").load("/repo/repos?pager.offset=0&sort=" + tabId + " #current");
        });
    });

    $("[name='lan']").each(function() {
        var text = $(this).text();
        if(text==GetQueryString("lan")){
            $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
            $(this).css("color","#fff");
        }
        $(this).click(function() {
            lan = text;
            $("[name='lan']").css("background-color","#fff");       //其他设置为默认颜色
            $("[name='lan']").css("color","#666");
            $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
            $(this).css("color","#fff");
            $("#current").load("/repo/repos?pager.offset=0&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });

    });
    

    $("[name='key']").each(function() {
        var text = $(this).text();
        if(text==GetQueryString("key")){
            $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
            $(this).css("color","#fff");
        }
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
        if(text==GetQueryString("year")){
            $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
            $(this).css("color","#fff");
        }
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