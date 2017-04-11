// var app = angular.module('app.controllers', []);
var app = angular.module('app', []);

app.controller('CustomerCtrl', function ($scope, $http) {

    /*var d = $.Deferred();
    $http.get('/rest/customers').then(function (response) {
        $scope.customers = response.data;
        d.resolve(response.data);
    });

    return d.promise();
*/

    $("#jsGrid").jsGrid({
        height: "300px",
        width: "400px",
        inserting: false,
        editing: true,
        sorting: true,
        paging: true,
        autoload: true,
        controller: {
            loadData: function() {
                var d = $.Deferred();
                $http.get('/rest/customers').then(function (response) {
                    $scope.customers = response.data;
                    d.resolve(response.data);
                });

                return d.promise();
            }
        },
        fields: [
            { name: "id", type: "text", width: 100 },
            { name: "name", title:"Müşteri", type: "text", width: 200 },
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,

                headerTemplate: function () {
                    return $("<button>").attr("type", "button").text("Add")
                        .on("click", function () {
                             alert("pop çıkacak ekleme işlemi falan");
                        });
                }
            }

        ]
    });


});

app.controller('CreateCustomerCtrl', function ($scope, $http) {
    $scope.createCustomerForm.submit = function (item, event) {
        $http.post('/rest/customers', $scope.Name)
            .success(function () {
                alert('ok');
            })
            .error(function () {
                alert('nok');
            });
    };
});


// MasterPage için denemek için eklendi
/*var app2 = angular.module('masters', ['ui.router']);

app2.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function ($stateProvider, $urlRouterProvider, $locationProvider) {

    // For any unmatched url, redirect to root
    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('musteri', {
            url: '/',
            templateUrl: 'html/musteri.html'
        })
        .state('leftMaster', {
            abstract: true,
            templateUrl: 'LeftMaster.html'
        })
        .state('topLeftMaster', {
            abstract: true,
            templateUrl: 'TopLeftMaster.html'
        })

        .state('login', {
            url: '/',
            templateUrl: 'login.html'
        })
        .state('menuMaster.dashboard', {
            url: '/dashboard',
            templateUrl: 'dashboard.html',
        })
        .state('leftMaster.products', {
            url: '/products',
            templateUrl: 'products.html',
        })
        .state('topLeftMaster.customer', {
            url: '/customer',
            templateUrl: 'customers.html',
        });
}]);*/




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
