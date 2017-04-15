var app = angular.module("app", ["ngRoute"]);

//Menüler - routing
app.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.hashPrefix('');
}]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/anasayfaContent", {
            templateUrl: "html/anasayfa_content.html"
        })
        .when("/boyaTuru", {
            templateUrl: "html/turler/boya_turu.html"
        })
        .when("/boyaAltTuru", {
            templateUrl: "html/turler/boya_alt_turu.html"
        })
        .when("/kovaTuru", {
            templateUrl: "html/turler/kova_turu.html"
        })
        .when("/hammadde", {
            templateUrl: "html/hammadde.html"
        })
        .when("/urun", {
            templateUrl: "html/urun.html"
        })
        .when("/musteri", {
            templateUrl: "html/musteri.html",
            controller : "CustomerCtrl"
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


//Musteri.html
app.controller('CustomerCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
	$http.get('/rest/customers').then(function (response) {
		$scope.customers = response.data;
	});

	$scope.addCustomer = function () {
		var customer = {name: $scope.Name};
		var res = $http.post('/rest/customers', customer);
		res.then(
			function (response) {
				//alert('response: ' + response.data.result);
                $window.location.reload();
			}, function (response) {
				alert("exception occurred.");
			});
	};

	$scope.updateCustomer = function () {
        var customer = {name: $scope.Name};

	};

	$scope.deleteCustomer = function (id) {
		var res = $http.delete('/rest/customers?id=' + id);
		res.then(
			function (response) {
				//alert('response: ' + response.data.result);
                $window.location.reload();
            }, function (response) {
				alert("exception occurred.");
			});
	};

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
