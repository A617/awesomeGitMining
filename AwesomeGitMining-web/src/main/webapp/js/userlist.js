$(document).ready(function() {

    var lan = $("#onLan").text();
    var com = $("#onCom").text();


    //页码计数器
    var pageCount=1;

    var ias = jQuery.ias({
        container:  '#current',
        item:       '#posts',
        pagination: '#pagination',
        next:       '<a href="/user/users" class="next">next</a>',
        delay:      0,
    });


    ias.extension(new IASNoneLeftExtension({text: "You reached the end"}));
    // ias.on('noneLeft', function() {
    //     console.log('We hit the bottom!');
    //     alert(111);
    // })

    // Add a loader image which is displayed during loading
    ias.extension(new IASSpinnerExtension({
        // src: '/img/loading.gif', // optionally
        // html: '<div class="ias-spinner" style="text-align: center;"><img src="{src}"/></div>',
    }));



    ias.on('load', function(event) {
        pageCount++;
        event.url = event.url + "?lan=" + lan+"&com="+com+"&pager.offset="+(pageCount-1)*10;
    })

    $("[name='lan']").each(function() {
        var text = $(this).text();

        $(this).click(function() {
            lan = text;
            pageCount = 1;
            $("#onLan").attr("id","");
            $(this).attr("id","onLan");
            $("#current").load("/user/users?pager.offset=0&lan=" + lan+"&com="+com+" #current");
        });

    });


    $("[name='com']").each(function() {
        var text = $(this).text();

        $(this).click(function() {
            com = text;
            pageCount = 1;
            $("#onCom").attr("id","");
            $(this).attr("id","onCom");
            $("#current").load("/user/users?pager.offset=0&lan=" + lan+"&com="+com+" #current");
        });
    });

});