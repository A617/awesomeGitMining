$(document).ready(function() {

    // function GetQueryString(name)
    // {
    //     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    //     var r = window.location.search.substr(1).match(reg);
    //     if(r!=null)return  unescape(r[2]); return null;
    // }


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

    // ias.on('next', function(url) {
    //     alert(url);
    //     // if (url.indexOf("/website/home/2") > -1 && pageNum > 2) {
    //     return false;
    //     // }
    // });

    

    ias.on('load', function(event) {
        pageCount++;
        // console.log(
        //     "~~~~?sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+"&pager.offset="+(pageCount-1)*10
        //         );
        event.url = event.url + "?sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+"&pager.offset="+(pageCount-1)*10;
    })


    // jQuery.ias().extension(new IASPagingExtension());
    // jQuery.ias().on('pageChange', function(pageNum, scrollOffset, url) {
    //     pageCount = pageNum;
    //     console.log(
    //         "Welcome at page " + pageNum + ", " +scrollOffset+" "+
    //         "the original url of this page would be: " + url
    //     );
    // });



    $("#maintab").children().each(function() {
        var tabId = $(this).text().trim();

        $(this).click(function() {
            sort=tabId;
            pageCount = 1;
            // jQuery.ias().reinitialize();
            $("#current").load("/repo/repos?pager.offset=0&sort="+sort+"&lan=" + lan+"&key="+key+"&year="+year+" #current");
        });
    });


    

    $("[name='lan']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            lan = text;
            pageCount = 1;
            // jQuery.ias().reinitialize();
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