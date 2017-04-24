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
            templateUrl: "html/hammadde.html"
        })
        .when("/urun", {
            templateUrl: "html/urun.html"
        })
        .when("/musteri", {
            templateUrl: "html/musteri.html",
            controller: "CustomerCtrl"
        })
        .when("/uretim", {
            templateUrl: "html/uretim.html"
        })
        .when("/siparis", {
            templateUrl: "html/siparis.html"
        })
        .when("/stok", {
            templateUrl: "html/stok.html"
        });
});

//musteri.html
app.controller('CustomerCtrl', ['$scope', '$http', '$window', '$filter', function ($scope, $http, $window, $filter) {

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
                "<button type='button' data-toggle='modal'  data-target='#editModal' data-ng-click='grid.appScope.editCustomer(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button>" +
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

    $scope.cleanCustomerName = function () {
        $scope.Name = "";
    };

    $scope.cleanSearchCustomerName = function () {
        $scope.customerName = "";
        $scope.getCustomers();
    };

    //Müşteri Ekleme
    $scope.addCustomer = function () {
        var customer = {name: $scope.Name};
        var res = $http.post('/rest/customers', customer);
        res.then(
            function (response) {
                $scope.getCustomers();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    var customerId;

    $scope.editCustomer = function (row) {
        customerId = row.entity.id;
        $scope.EditName = row.entity.name;
    };

    //Müşteri Güncelleme
    $scope.updateCustomer = function () {
        var customer = {id: customerId, name: $scope.EditName};
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

    $scope.exportExcel = function () {

        alasql('SELECT id,name INTO XLSX("Musteri.xlsx",{headers:true}) FROM ?', [$scope.gridOptions.data]);
    }

}]);


//boya_tur.html
app.controller('PaintTypesCtrl', ['$scope', '$http', '$window', '$filter', function ($scope, $http, $window, $filter) {

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
            document.getElementById("savePaintTypeButton").innerHTML = "Kaydet";
        } else {// Düzenle

            document.getElementById("paintTypeModalHeader").innerHTML = "Boya Türü | Düzenle";
            document.getElementById("savePaintTypeButton").innerHTML = "Güncelle";
            paintTypeId = row.entity.id;
            $scope.modalPaintTypeName = row.entity.name;
        }
    };

    $scope.savePaintType = function () {

        var buttonType = document.getElementById("savePaintTypeButton").innerHTML;

        if (buttonType == 'Kaydet') {
            $scope.addPaintType();
        }

        if (buttonType == 'Güncelle') {
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
                "data-ng-click='grid.appScope.deleteSubPaintType(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
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
    $scope.openPaintSubTypeModal = function (row) {

        if (row == null) { // Yeni Kayıt

            $scope.addPaintSubTypeName = "";
            $scope.getPaintTypes();
            document.getElementById("paintSubTypeModalHeader").innerHTML = "Boya Alt Türü | Yeni Kayıt";
            document.getElementById("savePaintSubTypeButton").innerHTML = "Kaydet";
        } else {// Düzenle

            document.getElementById("paintSubTypeModalHeader").innerHTML = "Boya Alt Türü | Düzenle";
            document.getElementById("savePaintSubTypeButton").innerHTML = "Güncelle";

            paintSubTypeId = row.entity.id;
            $scope.editPaintSubTypeName = row.entity.name;
            $scope.modalPaintTypeSelect = row.entity.paintType.id;
        }

    };

    $scope.savePaintSubType = function () {

        var buttonType = document.getElementById("savePaintSubTypeButton").innerHTML;

        if (buttonType == 'Kaydet') {
            $scope.addPaintSubType();
        }

        if (buttonType == 'Güncelle') {
            $scope.updatePaintSubType();
        }
    }


    //Boya Türü Ekleme
    $scope.addPaintSubType = function () {
        var paintSubType = {name: $scope.addPaintSubTypeName, paintTypeId: $scope.paintTypeSelectAdd};
        var res = $http.post('/rest/paintSubTypes?name=' + $scope.addPaintSubTypeName + '&paintTypeId=' + $scope.paintTypeSelectAdd);
        res.then(
            function (response) {
                $scope.getPaintSubTypes();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    //Boya Türü Güncelleme
    $scope.updatePaintSubType = function () {
        var paintSubType = {id: paintTypeId, name: $scope.editPaintTypeName};
        var res = $http.put('/rest/paintSubTypes', paintSubType);
        res.then(
            function (response) {
                $scope.getPaintSubTypes();
            }, function (response) {
                alert("exception occurred.");
            });

    };

    //Boya Türü Silme
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

    $scope.exportExcelPaintSubType = function () {

        alasql('SELECT id,name INTO XLSX("BoyaTürü.xlsx",{headers:true}) FROM ?', [$scope.gridOptionsPaintSubType.data]);
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
