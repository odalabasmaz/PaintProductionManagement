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
        .when("/boyaTuru", {
            templateUrl: "html/turler/boya_turu.html",
            controller: "PaintTypeCtrl"
        })
        .when("/boyaAltTuru", {
            templateUrl: "html/turler/boya_alt_turu.html"
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
            {name: 'name'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'><button type='button' data-toggle='modal'  data-target='#editModal'" +
                "data-ng-click='grid.appScope.editCustomer(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button><button type='button' " +
                "data-ng-click='grid.appScope.deleteCustomer(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
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

        alasql('SELECT id,name INTO XLSX("Musteri.xlsx",{headers:true}) FROM ?',[$scope.gridOptions.data]);
     }

}]);


//boya_turu.html
app.controller('PaintTypeCtrl', ['$scope', '$http', '$window', '$filter', function ($scope, $http, $window, $filter) {

    //Grid Tanımlanıyor
    $scope.gridOptionsPaintType = {
        paginationPageSizes: [5, 10, 20],
        paginationPageSize: 10,
        columnDefs: [
            {name: 'id'},
            {name: 'name'},
            {
                name: 'Aksiyon',
                cellTemplate: "<div class='ui-grid-cell-contents' style='text-align: center;'><button type='button' data-toggle='modal'  data-target='#editPaintTypeModal'" +
                "data-ng-click='grid.appScope.editPaintType(row)' class='btn btn-success btn-xs'><i class='fa fa-edit'></i></button><button type='button' " +
                "data-ng-click='grid.appScope.deletePaintType(row)' class='btn btn-danger btn-xs'><i class='fa fa-trash'></i></button></div>",
                enableCellEdit: false,
                width: 80
            }
        ]
    };

    //Boya Türü Listesi
    $scope.getPaintTypes = function () {
        $http.get('/rest/paint_types').then(function (response) {
            $scope.gridOptionsPaintType.data = response.data;
        });
    };

    $scope.getPaintTypes();

    //Adına göre Müşteri Filtreleme
    $scope.searchPaintType = function (name) {
        if (name !== undefined) {
            $http.get('/rest/paint_types?name=' + name).then(function (response) {
                $scope.gridOptionsPaintType.data = response.data;
            });
        }
    };

    $scope.cleanPaintTypeName = function () {
        $scope.addPaintTypeName = "";
    };

    $scope.cleanSearchPaintTypeName = function () {
        $scope.searchPaintTypeName = "";
        $scope.getPaintTypes();
    };

    //Boya Türü Ekleme
    $scope.addPaintType = function () {
        var paintType = {name: $scope.addPaintTypeName};
        var res = $http.post('/rest/paint_types', paintType);
        res.then(
            function (response) {
                $scope.getPaintTypes();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    var paintTypeId;

    $scope.editPaintType = function (row) {
        paintTypeId = row.entity.id;
        $scope.editPaintTypeName = row.entity.name;
    };

    //Boya Türü Güncelleme
    $scope.updatePaintType = function () {
        var paintType = {id: paintTypeId, name: $scope.editPaintTypeName};
        var res = $http.put('/rest/paint_types', paintType);
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
        var res = $http.delete('/rest/paint_types?id=' + id);
        res.then(
            function (response) {
                $scope.getPaintTypes();
            }, function (response) {
                alert("exception occurred.");
            });
    };

    $scope.exportExcelPaintType = function () {

        alasql('SELECT id,name INTO XLSX("BoyaTürü.xlsx",{headers:true}) FROM ?',[$scope.gridOptionsPaintType.data]);
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
