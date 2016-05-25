// $("ul#demo_menu1").sidebar();
$("div#demo_menu2").sidebar({
    position:"right",
    callback:{
        sidebar : {
            open : function(){
                $.ajax("/repo/contrast", {
                    type: 'GET',
                    success: function (data, textStatus) {
                        $(".compare-bar").empty();
                        $.each(data,function (i,value) {
                            var url = "/repo/" + value + "/";
                            var e = $("<a href=" + url + "></a>").text(value+"\n");
                            $(".compare-bar").append(e);
                        });
                    }
                });
            },
            close : function(){

            }
        },
        item : {
            enter : function(){
                $(this).find("a").animate({color:"red"}, 250);
            },
            leave : function(){
                $(this).find("a").animate({color:"white"}, 250);
            }
        }
    }
});


// $("ul#demo_menu3").sidebar({
//     position:"top",
//     open:"click"
// });
// $("ul#demo_menu4").sidebar({
//     position:"bottom"
// });
// $("ul#demo_menu5").sidebar({
//     root: $("#include")
// });

