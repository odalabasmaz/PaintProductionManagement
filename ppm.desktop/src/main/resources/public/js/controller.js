var app = angular.module('app', []);

app.controller('CustomerCtrl', ['$scope', '$http', '$window', function ($scope, $http, $window) {
	$http.get('/rest/customers').then(function (response) {
		$scope.customers = response.data;
	});

	$scope.addCustomer = function () {
		var customer = {name: $scope.Name};
		var res = $http.post('/rest/customers', customer);
		res.then(
			function (response) {
				alert('response: ' + response.data.result);
				$window.location.href = '/customers/index.html';
			}, function (response) {
				alert("exception occurred.");
			});
	};

	$scope.updateCustomer = function () {

	};

	$scope.deleteCustomer = function (id) {
		var res = $http.delete('/rest/customers?id=' + id);
		res.then(
			function (response) {
				alert('response: ' + response.data.result);
				$window.location.href = '/customers/index.html';
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

// MasterPage denemek için eklendi
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
