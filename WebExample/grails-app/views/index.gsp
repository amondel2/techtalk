<!DOCTYPE html>
<html  lang="en">
	<head>
		<meta name="layout" content="main"/>
		<title>WebApp Example</title>
	</head>
	<body ng-app="myApp">
		<div id="page-body" role="main" ng-controller="OrgController">
			<h1>Welcome to {{companyName}}</h1>
			<p>We Currently have {{orgCount}} organization<span ng-if="multipleOrgs">s</span>. Please manage them wisely!</p>
			<div id="tree" angularDir></div>
			
			
		</div>
		<asset:javascript src="app.js"/>
	</body>
</html>
