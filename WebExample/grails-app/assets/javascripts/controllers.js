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