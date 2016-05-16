$(document).ready(function() {

    var lan = $("#onLan").text();
    var year = $("#onYear").text();
    var key = $("#onKey").text();

    alert(lan+" "+year+" "+key);

    $("[name='lan']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            lan = text;
            window.location.href = "/repo/repos?pager.offset=0&lan=" + lan+"&key="+key+"&year="+year;
        });
    });

    $("[name='key']").each(function() {

        var text = $(this).text();
        $(this).click(function() {
            key = text;
            window.location.href = "/repo/repos?pager.offset=0&lan=" + lan+"&key="+key+"&year="+year;
        });
    });

    $("[name='year']").each(function() {
        var text = $(this).text();
        $(this).click(function() {
            year = text;
            window.location.href = "/repo/repos?pager.offset=0&lan=" + lan+"&key="+key+"&year="+year;
        });
    });
});