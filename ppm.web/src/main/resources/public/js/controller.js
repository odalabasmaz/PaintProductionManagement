var app = angular.module("app", ["ngRoute", "ngTouch", "ui.grid", "ui.grid.pagination"]);

//Menüler - routing
app.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.hashPrefix('');
}]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "html/anasayfa_content.html"
        })
        .when("/anasayfaContent", {
            templateUrl: "html/anasayfa_content.html"
        })
        .when("/boyatur", {
            templateUrl: "html/boya_tur.html",
            controller: "PaintTypesCtrl"
        })
        .when("/hammadde", {
            templateUrl: "html/hammadde.html",
            controller: "RawMaterialCtrl"
        })
        .when("/urun", {
            templateUrl: "html/urun/urun.html",
            controller: "ProductCtrl"
        })
        .when("/urunEkle", {
            templateUrl: "html/urun/urunEkle.html",
            controller: "ProductCRUDCtrl"
        })
        .when("/musteri", {
            templateUrl: "html/musteri.html",
            controller: "CustomerCtrl"
        })
        .when("/uretim", {
            templateUrl: "html/uretim.html"
        })
        .when("/siparis", {
            templateUrl: "html/siparis.html",
            controller: "OrderCtrl"
        })
        .when("/stok", {
            templateUrl: "html/stok.html"
        });
});

//musteri.html
app.controller('CustomerCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {

    //Grid Tanımlanıyor
    $scope.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'id'},
            {name: 'name', displayName: 'Müşteri'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'>" +
                "<button type='button' data-toggle='modal'  data-target='#CustomerModal' data-ng-click='grid.appScope.openCustomerModal(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button>" +
                "<button type='button' data-ng-click='grid.appScope.deleteCustomer(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };
    //Müşteriler Listesi
    $scope.getCustomers = function () {
        $http.get('/rest/customers').then(function (response) {
            $scope.gridOptions.data = response.data;
        });
    };
    $scope.getCustomers();

    //Adına göre Müşteri Filtreleme
    $scope.searchCustomer = function (name) {
        if (name !== undefined) {
            $http.get('/rest/customers?name=' + name).then(function (response) {
                $scope.gridOptions.data = response.data;
            });
        }
    };

    $scope.cleanSearchCustomerName = function () {
        $scope.customerName = "";
        $scope.getCustomers();
    };


    // Müşteri Ekleme/Düzenleme Modal açılması
    var customerId;
    $scope.openCustomerModal = function (row) {

        if (row == null) { // Yeni Kayıt
            $scope.modalCustomerName = "";
            document.getElementById("customerModalHeader").innerHTML = "Müşteri | Yeni Kayıt";
            document.getElementById("saveCustomerButton").innerHTML = "<i class='fa fa-save'></i>     Kaydet";
        } else {// Düzenle
            document.getElementById("customerModalHeader").innerHTML = "Müşteri | Düzenle";
            document.getElementById("saveCustomerButton").innerHTML = "<i class='fa fa-save'></i>     Güncelle";
            customerId = row.entity.id;
            $scope.modalCustomerName = row.entity.name;
        }
    };

    $scope.saveCustomer = function () {
        var buttonType = document.getElementById("saveCustomerButton").innerHTML;
        if (buttonType == '<i class="fa fa-save"></i>     Kaydet') {
            $scope.addCustomer();
        }
        if (buttonType == '<i class="fa fa-save"></i>     Güncelle') {

            $scope.updateCustomer();

        }
     }

    //Müşteri Ekleme
    $scope.addCustomer = function () {
        var customer = {name: $scope.modalCustomerName};
        var res = $http.post('/rest/customers', customer);
        res.then(
            function (response) {
                $scope.getCustomers();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Müşteri Güncelleme
    $scope.updateCustomer = function () {
        var customer = {id: customerId, name: $scope.modalCustomerName};
        var res = $http.put('/rest/customers', customer);
        res.then(
            function (response) {
                $scope.getCustomers();
            }, function (response) {
                alert("exception occurred.");
            });

    };
    //Müşteri Silme
    $scope.deleteCustomer = function (row) {
        var id = row.entity.id;
        var res = $http.delete('/rest/customers?id=' + id);
        res.then(
            function (response) {
                $scope.getCustomers();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    $scope.exportCustomerExcel = function () {

        alasql('SELECT id,name INTO XLSX("Musteri.xlsx",{headers:true}) FROM ?', [$scope.gridOptions.data]);
    }

}]);

//boya_tur.html
app.controller('PaintTypesCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {

    // Boya Türü Grid Tanımlanıyor
    $scope.gridOptionsPaintType = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'id',width: 40},
            {name: 'name', displayName: 'Boya Türü'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'><button type='button' data-toggle='modal'  data-target='#PaintTypeModal'" +
                "data-ng-click='grid.appScope.openPaintTypeModal(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button><button type='button' " +
                "data-ng-click='grid.appScope.deletePaintType(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };

    //Boya Türü Listesi
    $scope.getPaintTypes = function () {
        $http.get('/rest/paintTypes').then(function (response) {
            $scope.gridOptionsPaintType.data = response.data;
            $scope.paintTypes = response.data;
        });
    };

    $scope.getPaintTypes();

    //Adına göre Boya Türü Filtreleme
    $scope.searchPaintType = function (name) {
        if (name !== undefined) {
            $http.get('/rest/paintTypes?name=' + name).then(function (response) {
                $scope.gridOptionsPaintType.data = response.data;
            });
        }
    };
    $scope.cleanSearchPaintTypeName = function () {
        $scope.searchPaintTypeName = "";
        $scope.getPaintTypes();
    };

    // Boya Türü Ekleme/Düzenleme Modal açılması
    var paintTypeId;
    $scope.openPaintTypeModal = function (row) {

        if (row == null) { // Yeni Kayıt

            $scope.modalPaintTypeName = "";
            document.getElementById("paintTypeModalHeader").innerHTML = "Boya Türü | Yeni Kayıt";
            document.getElementById("savePaintTypeButton").innerHTML = "<i class='fa fa-save'></i>     Kaydet";
        } else {// Düzenle

            document.getElementById("paintTypeModalHeader").innerHTML = "Boya Türü | Düzenle";
            document.getElementById("savePaintTypeButton").innerHTML = "<i class='fa fa-save'></i>     Güncelle";
            paintTypeId = row.entity.id;
            $scope.modalPaintTypeName = row.entity.name;
        }
    };

    $scope.savePaintType = function () {

        var buttonType = document.getElementById("savePaintTypeButton").innerHTML;

        if (buttonType == '<i class="fa fa-save"></i>     Kaydet') {
            $scope.addPaintType();
        }

        if (buttonType == '<i class="fa fa-save"></i>     Güncelle') {
            $scope.updatePaintType();
        }


    }

    //Boya Türü Ekleme
    $scope.addPaintType = function () {
        var paintType = {name: $scope.modalPaintTypeName};
        var res = $http.post('/rest/paintTypes', paintType);
        res.then(
            function (response) {
                $scope.getPaintTypes();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Boya Türü Güncelleme
    $scope.updatePaintType = function () {
        var paintType = {id: paintTypeId, name: $scope.modalPaintTypeName};
        var res = $http.put('/rest/paintTypes', paintType);
        res.then(
            function (response) {
                $scope.getPaintTypes();
            }, function (response) {
                alert("exception occurred.");
            });

    };

    //Boya Türü Silme
    $scope.deletePaintType = function (row) {
        var id = row.entity.id;
        var res = $http.delete('/rest/paintTypes?id=' + id);
        res.then(
            function (response) {
                $scope.getPaintTypes();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Boya Türü Excel Export
    $scope.exportExcelPaintType = function () {

        alasql('SELECT id,name INTO XLSX("BoyaTürü.xlsx",{headers:true}) FROM ?', [$scope.gridOptionsPaintType.data]);
    }



    //Boya Alt Türü Grid Tanımlanıyor
    $scope.gridOptionsPaintSubType = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'id',width: 40},
            {name: 'name', displayName: 'Boya Alt Türü'},
            {name: 'paintType.name', displayName: 'Boya Türü'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'><button type='button' data-toggle='modal'  data-target='#PaintSubTypeModal'" +
                "data-ng-click='grid.appScope.openPaintSubTypeModal(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button><button type='button' " +
                "data-ng-click='grid.appScope.deletePaintSubType(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };

    //Boya Alt Türü Listesi
    $scope.getPaintSubTypes = function () {
        $http.get('/rest/paintSubTypes').then(function (response) {
            $scope.gridOptionsPaintSubType.data = response.data;
        });
    };

    $scope.getPaintSubTypes();

    //Boya Alt Türü Filtreleme
    $scope.searchPaintSubType = function (name) {
        if (name !== undefined) {
            $http.get('/rest/paintSubTypes?name=' + name).then(function (response) {
                $scope.gridOptionsPaintSubType.data = response.data;
            });
        }
    };

    $scope.cleanSearchPaintSubTypeName = function () {
        $scope.searchPaintSubTypeName = "";
        $scope.getPaintSubTypes();
    };

    var paintSubTypeId;
	var updatedEntity;
    $scope.openPaintSubTypeModal = function (row) {

        if (row == null) { // Yeni Kayıt

            $scope.modalPaintSubTypeName = "";
            $scope.getPaintTypes();
            document.getElementById("paintSubTypeModalHeader").innerHTML = "Boya Alt Türü | Yeni Kayıt";
            document.getElementById("savePaintSubTypeButton").innerHTML = "<i class='fa fa-save'></i>     Kaydet";
        } else {// Düzenle

            document.getElementById("paintSubTypeModalHeader").innerHTML = "Boya Alt Türü | Düzenle";
            document.getElementById("savePaintSubTypeButton").innerHTML = "<i class='fa fa-save'></i>     Güncelle";

            paintSubTypeId = row.entity.id;
            updatedEntity = row.entity;
            $scope.modalPaintSubTypeName = row.entity.name;
            $scope.modalPaintTypeSelect = row.entity.paintType;
            $scope.modalPaintTypeSelectId = row.entity.paintType.id;

        }

    };

    $scope.savePaintSubType = function () {

        var buttonType = document.getElementById("savePaintSubTypeButton").innerHTML;

        if (buttonType == '<i class="fa fa-save"></i>     Kaydet') {
            $scope.addPaintSubType();
        }

        if (buttonType == '<i class="fa fa-save"></i>     Güncelle') {
            $scope.updatePaintSubType();
        }
    };

    //Boya Alt Türü Ekleme
    $scope.addPaintSubType = function () {
        var res = $http.post('/rest/paintSubTypes?name=' + $scope.modalPaintSubTypeName + '&paintTypeId=' + $scope.modalPaintTypeSelectId);
        res.then(
            function (response) {
                $scope.getPaintSubTypes();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Boya Alt Türü Güncelleme
    $scope.updatePaintSubType = function () {
        updatedEntity.name = $scope.modalPaintSubTypeName;
        updatedEntity.paintType = $scope.modalPaintTypeSelect;
        var res = $http.put('/rest/paintSubTypes', updatedEntity);
        res.then(
            function (response) {
                $scope.getPaintSubTypes();
            }, function (response) {
                alert("exception occurred.");
            });

    };

    //Boya Alt Türü Silme
    $scope.deletePaintSubType = function (row) {
        var id = row.entity.id;
        var res = $http.delete('/rest/paintSubTypes?id=' + id);
        res.then(
            function (response) {
                $scope.getPaintSubTypes();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Boya Alt Türü Excel Export
    $scope.exportExcelPaintSubType = function () {

        alasql('SELECT id,name INTO XLSX("BoyaAltTürü.xlsx",{headers:true}) FROM ?', [$scope.gridOptionsPaintSubType.data]);
    }

}]);

//hammadde.html
app.controller('RawMaterialCtrl', ['$scope', '$http', '$window',  function ($scope, $http, $window) {

    //Grid Tanımlanıyor
    $scope.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'id'},
            {name: 'code', displayName:'Hammadde Kodu'},
            {name: 'name', displayName: 'Hammadde Adı'},
            {name: 'type', displayName: 'Hammadde Türü'}, //bunu arkada göremedim?
            {name: 'desc', displayName: 'Açıklama'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'>" +
                "<button type='button' data-toggle='modal'  data-target='#RawMaterialModal' data-ng-click='grid.appScope.openRawMaterialModal(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button>" +
                "<button type='button' data-ng-click='grid.appScope.deleteRawMaterial(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };
    //Hammadde Listesi
    $scope.getRawMaterials = function () {
        $http.get('/rest/rawmaterials').then(function (response) {
            $scope.gridOptions.data = response.data;
        });
    };
    $scope.getRawMaterials();

    //Adına göre Hammadde Filtreleme
    $scope.searchRawMaterial = function (name) {
        if (name !== undefined) {
            $http.get('/rest/rawmaterials?name=' + name).then(function (response) {
                $scope.gridOptions.data = response.data;
            });
        }
    };

    //Hammadde Search Temizleme -- bakılacak
    $scope.cleanSearchRawMaterialName = function () {
        $scope.customerName = "";
        $scope.getRawMaterials();
    };

    // Hammadde Ekleme/Düzenleme Modal açılması
    var rawMaterialId;
    $scope.openRawMaterialModal = function (row) {

        if (row == null) { // Yeni Kayıt
            $scope.modalRawMaterialName = "";
            document.getElementById("RawMaterialModalHeader").innerHTML = "Hammadde | Yeni Kayıt";
            document.getElementById("saveRawMaterialButton").innerHTML = "<i class='fa fa-save'></i>     Kaydet";
        } else {// Düzenle
            document.getElementById("RawMaterialModalHeader").innerHTML = "Hammadde | Düzenle";
            document.getElementById("saveRawMaterialButton").innerHTML = "<i class='fa fa-save'></i>     Güncelle";
            rawMaterialId = row.entity.id;
            $scope.modalRawMaterialName = row.entity.name;
        }
    };

    $scope.saveRawMaterial = function () {
        var buttonType = document.getElementById("saveRawMaterialButton").innerHTML;
        if (buttonType == '<i class="fa fa-save"></i>     Kaydet') {
            $scope.addRawMaterial();
        }
        if (buttonType == '<i class="fa fa-save"></i>     Güncelle') {

            $scope.updateRawMaterial();

        }
    }

    //Hammadde Ekleme
    $scope.addRawMaterial = function () {
        var rawMaterial = {name: $scope.modalRawMaterialName};
        var res = $http.post('/rest/rawmaterials', rawMaterial);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Hammadde Güncelleme
    $scope.updateRawMaterial = function () {
        var rawMaterial = {id: rawMaterialId, name: $scope.modalRawMaterialName};
        var res = $http.put('/rest/rawmaterials', rawMaterial);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });

    };

    //Hammadde Silme
    $scope.deleteRawMaterial = function (row) {
        var id = row.entity.id;
        var res = $http.delete('/rest/rawmaterials?id=' + id);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Hammadde Excel Export
    $scope.exportRawMaterialExcel = function () {

        alasql('SELECT id,name INTO XLSX("Hammadde.xlsx",{headers:true}) FROM ?', [$scope.gridOptions.data]);
    }

}]);

//urun.html
app.controller('ProductCtrl', ['$scope', '$http', '$window',  function ($scope, $http, $window) {

    //Ürün Grid Tanımlanıyor
    $scope.gridOptions = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'id'},
            {name: 'name', displayName: 'Ürün Adı'},
            {name: 'code', displayName:'Ürün Kodu'},
            {name: 'colorName', displayName:'Renk Adı'},
            {name: 'colorCode', displayName:'Renk Kodu'},
            {name: 'density', displayName: 'Yoğunluğu'},
            {name: 'paintType', displayName: 'Boya Türü'},
            {name: 'paintSubType', displayName: 'Boya Alt Türü'},
            {name: 'desc', displayName: 'Açıklama'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'>" +
                "<button type='button' data-toggle='modal'  data-target='#ProductModal' data-ng-click='grid.appScope.openProductModal(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button>" +
                "<button type='button' data-ng-click='grid.appScope.deleteProduct(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };

    //Ürün Listesi
    $scope.getProducts = function () {
        $http.get('/rest/products').then(function (response) {
            $scope.gridOptions.data = response.data;
        });
    };
    $scope.getProducts();

    //Adına göre Ürün Filtreleme -- bakılacak
    $scope.searchProduct = function (name) {
        if (name !== undefined) {
            $http.get('/rest/products?name=' + name).then(function (response) {
                $scope.gridOptions.data = response.data;
            });
        }
    };

    //Ürün Search Temizleme -- bakılacak
    $scope.cleanSearchProduct = function () {
        $scope.customerName = "";
        $scope.getProducts();
    };

    // Ürün Ekleme/Düzenleme Modal açılması
    var productId;
    $scope.openProductModal = function (row) {

        if (row == null) { // Yeni Kayıt
            $scope.modalProductName = "";
            document.getElementById("ProductModalHeader").innerHTML = "Ürün | Yeni Kayıt";
            document.getElementById("saveProductButton").innerHTML = "<i class='fa fa-save'></i>     Kaydet";
        } else {// Düzenle
            document.getElementById("ProductModalHeader").innerHTML = "Ürün | Düzenle";
            document.getElementById("saveProductButton").innerHTML = "<i class='fa fa-save'></i>     Güncelle";
            productId = row.entity.id;
            $scope.modalProductName = row.entity.name;
        }
    };

    $scope.saveRawMaterial = function () {
        var buttonType = document.getElementById("saveProductButton").innerHTML;
        if (buttonType == '<i class="fa fa-save"></i>     Kaydet') {
            $scope.addProduct();
        }
        if (buttonType == '<i class="fa fa-save"></i>     Güncelle') {

            $scope.updateProduct();

        }
    }

    //Hammadde Ekleme
    $scope.addProduct = function () {
        var product = {name: $scope.modalProductName};
        var res = $http.post('/rest/products', product);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Hammadde Güncelleme
    $scope.updateProduct = function () {
        var customer = {id: customerId, name: $scope.modalRawMaterialName};
        var res = $http.put('/rest/products', customer);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });

    };

    //Hammadde Silme
    $scope.deleteRawMaterial = function (row) {
        var id = row.entity.id;
        var res = $http.delete('/rest/rawmaterials?id=' + id);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Hammadde Excel Export
    $scope.exportRawMaterialExcel = function () {

        alasql('SELECT id,name INTO XLSX("Hammadde.xlsx",{headers:true}) FROM ?', [$scope.gridOptions.data]);
    }

}]);

//urunEkle.html
app.controller('ProductCRUDCtrl', ['$scope', '$http', '$window',  function ($scope, $http, $window) {

    //Ara Ürün Grid Tanımlanıyor
    $scope.gridOptionsIntermProduct = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'name', displayName: 'Ara Ürün'},
            {name: 'miktar', displayName: 'Miktar'},
             {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'>" +
                "<button type='button' data-toggle='modal'  data-target='#ProductModal' data-ng-click='grid.appScope.openProductModal(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button>" +
                "<button type='button' data-ng-click='grid.appScope.deleteProduct(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };

    //Hammadde Grid Tanımlanıyor
    $scope.gridOptionsRawMaterial = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'name', displayName: 'Hammadde'},
            {name: 'miktar', displayName: 'Miktar'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'>" +
                "<button type='button' data-toggle='modal'  data-target='#ProductModal' data-ng-click='grid.appScope.openProductModal(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button>" +
                "<button type='button' data-ng-click='grid.appScope.deleteProduct(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };


    //Ürün Listesi
    $scope.displayIntermProductSection = function (option) {
        if(option == 'mainProduct')
        {
            //intermProductSection
            document.getElementById('RawMaterialSection').style.display = "block";
            document.getElementById('MainProductSection').style.display = "block";

        }
        else if(option == 'intermProduct'){
            document.getElementById('RawMaterialSection').style.display = "block";
            document.getElementById('MainProductSection').style.display = "none";

        }


    };








    //Ürün Listesi
    $scope.getProducts = function () {
        $http.get('/rest/products').then(function (response) {
            $scope.gridOptions.data = response.data;
        });
    };
    $scope.getProducts();

    //Adına göre Ürün Filtreleme -- bakılacak
    $scope.searchProduct = function (name) {
        if (name !== undefined) {
            $http.get('/rest/products?name=' + name).then(function (response) {
                $scope.gridOptions.data = response.data;
            });
        }
    };

    //Ürün Search Temizleme -- bakılacak
    $scope.cleanSearchProduct = function () {
        $scope.customerName = "";
        $scope.getProducts();
    };

    // Ürün Ekleme/Düzenleme Modal açılması
    var productId;
    $scope.openProductModal = function (row) {

        if (row == null) { // Yeni Kayıt
            $scope.modalProductName = "";
            document.getElementById("ProductModalHeader").innerHTML = "Ürün | Yeni Kayıt";
            document.getElementById("saveProductButton").innerHTML = "<i class='fa fa-save'></i>     Kaydet";
        } else {// Düzenle
            document.getElementById("ProductModalHeader").innerHTML = "Ürün | Düzenle";
            document.getElementById("saveProductButton").innerHTML = "<i class='fa fa-save'></i>     Güncelle";
            productId = row.entity.id;
            $scope.modalProductName = row.entity.name;
        }
    };

    $scope.saveRawMaterial = function () {
        var buttonType = document.getElementById("saveProductButton").innerHTML;
        if (buttonType == '<i class="fa fa-save"></i>     Kaydet') {
            $scope.addProduct();
        }
        if (buttonType == '<i class="fa fa-save"></i>     Güncelle') {

            $scope.updateProduct();

        }
    }

    //Hammadde Ekleme
    $scope.addProduct = function () {
        var product = {name: $scope.modalProductName};
        var res = $http.post('/rest/products', product);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Hammadde Güncelleme
    $scope.updateProduct = function () {
        var customer = {id: customerId, name: $scope.modalRawMaterialName};
        var res = $http.put('/rest/products', customer);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });

    };

    //Hammadde Silme
    $scope.deleteRawMaterial = function (row) {
        var id = row.entity.id;
        var res = $http.delete('/rest/rawmaterials?id=' + id);
        res.then(
            function (response) {
                $scope.getRawMaterials();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Hammadde Excel Export
    $scope.exportRawMaterialExcel = function () {

        alasql('SELECT id,name INTO XLSX("Hammadde.xlsx",{headers:true}) FROM ?', [$scope.gridOptions.data]);
    }

}]);


























































/*
 - REST post request with body only, server side
 - ng-resource
 - rest angularjs best practices
 */

/*
 app.controller('UserListCtrl', ['$scope', 'UsersFactory', 'UserFactory', '$location',
 function ($scope, UsersFactory, UserFactory, $location) {

 // callback for ng-click 'editUser':
 $scope.editUser = function (userId) {
 $location.path('/user-detail/' + userId);
 };

 // callback for ng-click 'deleteUser':
 $scope.deleteUser = function (userId) {
 UserFactory.delete({id: userId});
 $scope.users = UsersFactory.query();
 };

 // callback for ng-click 'createUser':
 $scope.createNewUser = function () {
 $location.path('/user-creation');
 };

 $scope.users = UsersFactory.query();
 }]);

 app.controller('UserDetailCtrl', ['$scope', '$routeParams', 'UserFactory', '$location',
 function ($scope, $routeParams, UserFactory, $location) {

 // callback for ng-click 'updateUser':
 $scope.updateUser = function () {
 UserFactory.update($scope.user);
 $location.path('/user-list');
 };

 // callback for ng-click 'cancel':
 $scope.cancel = function () {
 $location.path('/user-list');
 };

 $scope.user = UserFactory.show({id: $routeParams.id});
 }]);

 app.controller('UserCreationCtrl', ['$scope', 'UsersFactory', '$location',
 function ($scope, UsersFactory, $location) {

 // callback for ng-click 'createNewUser':
 $scope.createNewUser = function () {
 UsersFactory.create($scope.user);
 $location.path('/user-list');
 }
 }]);
 */
