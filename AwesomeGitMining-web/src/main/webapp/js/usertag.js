$(document).ready(function() {

    var lan = $("#onLan").text();
    var com = $("#onCom").text();

    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }

    $("[name='lan']").each(function() {
        var text = $(this).text();
        if(text==GetQueryString("lan")){
            $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
            $(this).css("color","#fff");
        }
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
        if(text==GetQueryString("com")){
            $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
            $(this).css("color","#fff");
        }
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