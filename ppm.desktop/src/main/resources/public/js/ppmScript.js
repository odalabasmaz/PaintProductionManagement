//Menülere tıklandığında açılacak sayfalar
/*$("#anasayfa").click(function () {
    $("#masterContent").load("html/anasayfa_content.html");
 
});*/

$("#boyaTuru").click(function () {
    //$("#masterContent").load("html/turler/boya_turu.html");
    window.open("html/turler/boya_turu.html", '_self');

});

$("#boyaAltTuru").click(function () {
    $("#masterContent").load("html/turler/boya_alt_turu.html");
});

$("#kovaTuru").click(function () {
    $("#masterContent").load("html/turler/kova_turu.html");
});

$("#hammadde").click(function () {
    $("#masterContent").load("html/hammadde.html");
});

$("#urun").click(function () {
    $("#masterContent").load("html/urun.html");
});

$("#musteri").click(function () {
    $("#masterContent").load("html/musteri.html");
    window.open("html/musteri.html", '_self');

});

$("#uretim").click(function () {
    $("#masterContent").load("html/uretim.html");
});

$("#siparis").click(function () {
    $("#masterContent").load("html/siparis.html");
});

$("#stok").click(function () {
    $("#masterContent").load("html/stok.html");
});

