//Menülere tıklandığında açılacak sayfalar
$("#anasayfa").click(function () {
    $("#masterContent").load("pages/anasayfa_content.html");
 
});
$("#boyaTuru").click(function () {
    $("#masterContent").load("pages/turler/boya_turu.html");
});

$("#boyaAltTuru").click(function () {
    $("#masterContent").load("pages/turler/boya_alt_turu.html");
});

$("#kovaTuru").click(function () {
    $("#masterContent").load("pages/turler/kova_turu.html");
});

$("#hammadde").click(function () {
    $("#masterContent").load("pages/hammadde.html");
});

$("#urun").click(function () {
    $("#masterContent").load("pages/urun.html");
});

$("#musteri").click(function () {
    $("#masterContent").load("pages/musteri.html");
});

$("#uretim").click(function () {
    $("#masterContent").load("pages/uretim.html");
});

$("#siparis").click(function () {
    $("#masterContent").load("pages/siparis.html");
});

$("#stok").click(function () {
    $("#masterContent").load("pages/stok.html");
});

