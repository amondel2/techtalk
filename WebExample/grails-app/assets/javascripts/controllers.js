angular.module('myApp.controllers', []).controller('OrgController',['$scope', '$http', 'apiUrl', function($scope, $http, apiUrl) {
	$scope.multipleOrgs = false;
	
	
	$scope.changeOrgCount = function(newCount) {
		$scope.orgCount = newCount;
		$scope.multipleOrgs = newCount != 1;
	};
	
	$http.get(apiUrl + 'company').success(function(data, status, headers, config) {
        $scope.companyName = data.name;
        $scope.changeOrgCount(data.organizations.length);
    });
    
    }]);
//OrgControllers.controller('BookListCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl', function($scope, $rootScope, $http, $location, apiUrl) {
//        var load = function() {
//            console.log('call load()...');
//            $http.get(apiUrl + '/books.json')
//                    .success(function(data, status, headers, config) {
//                        $scope.books = data;
//                        angular.copy($scope.books, $scope.copy);
//                    });
//        }
//
//        load();
//
//        $scope.addBook = function() {
//            console.log('call addBook');
//            $location.path("/new");
//        }
//
//        $scope.editBook = function(index) {
//            console.log('call editBook');
//            $location.path('/edit/' + $scope.books[index].id);
//        }
//
//        $scope.delBook = function(index) {
//            console.log('call delBook');
//            var todel = $scope.books[index];
//            $http
//                    .delete(apiUrl + '/books/' + todel.id + '.json')
//                    .success(function(data, status, headers, config) {
//                        load();
//                    }).error(function(data, status, headers, config) {
//            });
//        }
//
//    }]);
//OrgControllers.controller('NewBookCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl', function($scope, $rootScope, $http, $location, apiUrl) {
//
//        $scope.book = {};
//
//        $scope.saveBook = function() {
//            console.log('call saveBook');
//            $http.post(apiUrl + '/books.json', $scope.book)
//                    .success(function(data, status, headers, config) {
//                        $location.path('/books');
//                    }).error(function(data, status, headers, config) {
//            });
//        }
//    }]);
//
//OrgControllers.controller('EditBookCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl', function($scope, $rootScope, $http, $routeParams, $location, apiUrl) {
//
//        var load = function() {
//            console.log('call load()...');
//            $http.get(apiUrl + '/books/' + $routeParams['id'] + '.json')
//                    .success(function(data, status, headers, config) {
//                        $scope.book = data;
//                        angular.copy($scope.book, $scope.copy);
//                    });
//        }
//
//        load();
//
//        $scope.book = {};
//
//        $scope.updateBook = function() {
//            console.log('call updateBook');
//            $http.put(apiUrl + '/books/' + $scope.book.id + '.json', $scope.book)
//                    .success(function(data, status, headers, config) {
//                        $location.path('/books');
//                    }).error(function(data, status, headers, config) {
//            });
//        }
//    }]);
