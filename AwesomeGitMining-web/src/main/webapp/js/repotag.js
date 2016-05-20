$(document).ready(function() {

    var lan = $("#onLan").text();
    var year = $("#onYear").text();
    var key = $("#onKey").text();
    var sort = $(".active").children()[0].text.trim();


    //页码计数器
    var pageCount=1;

    var ias = jQuery.ias({
        container:  '#current',
        item:       '.panel-body',
        pagination: '#pagination',
        next:       '<a href="/repo/repos" class="next">next</a>',
        // loader:"<img src='/img/loading.gif' /><font size='5'>正在拼命的为您加载</font>",
        // beforePageChange:function(curScrOffset, nextPageUrl){
        //     alert("AA");
        //     pageCount++;
        //     //总页数
        //     var pages = parseInt('<%$total%>', 10);
        //     if(pageCount <= pages) return true;
        //     // jQuery(".artList").css({'padding-bottom':'91px'});
        //     return false;
        // }
    });

    // ias.extension(new IASTriggerExtension({
    //     text: 'Load more items', // optionally
    // }));

    ias.extension(new IASNoneLeftExtension({text: "You reached the end"}));
    ias.on('noneLeft', function() {
        console.log('We hit the bottom!');
    })

    // Add a loader image which is displayed during loading
    ias.extension(new IASSpinnerExtension());
    ias.extension(new IASSpinnerExtension({
        src: '/img/loading.gif', // optionally
        html: '<div class="ias-spinner" style="text-align: center;"><img src="{src}"/></div>',
    }));

    

    ias.on('load', function(event) {
        pageCount++;
        event.url = event.url + "?sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+"&pager.offset="+(pageCount-1)*10;
    })


    $("#maintab").children().each(function() {
        var tabId = $(this).text().trim();

        $(this).click(function() {
            sort=tabId;
            pageCount = 1;
            $(".active").attr("class","");
            $(this).attr("class","active");
            $("#current").load("/repo/repos?pager.offset=0&sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });
    });


    

    $("[name='lan']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            lan = text;
            pageCount = 1;
            $("#onLan").attr("id","");
            $(this).attr("id","onLan");
            $("#current").load("/repo/repos?pager.offset=0&sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });

    });
    

    $("[name='key']").each(function() {
        var text = $(this).text();
        // if(text==GetQueryString("key")){
        //     $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
        //     $(this).css("color","#fff");
        // }
        $(this).click(function() {
            key = text;
            pageCount = 1;
            $("[name='key']").css("background-color","#fff");
            $("[name='key']").css("color","#666");
            $(this).css("background-color","#2baab1");
            $(this).css("color","#fff");
            $("#current").load("/repo/repos?pager.offset=0&sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });
    });

    $("[name='year']").each(function() {
        var text = $(this).text();
        // if(text==GetQueryString("year")){
        //     $(this).css("background-color","#2baab1");              //为被选中的tag设置颜色
        //     $(this).css("color","#fff");
        // }
        $(this).click(function() {
            year = text;
            pageCount = 1;
            $("[name='year']").css("background-color","#fff");
            $("[name='year']").css("color","#666");
            $(this).css("background-color","#2baab1");
            $(this).css("color","#fff");
            $("#current").load("/repo/repos?pager.offset=0&sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });
    });
    

});