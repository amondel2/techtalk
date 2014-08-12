//= require controllers
//= require directives

'use strict';
	var as = angular.module('myApp', [ 'myApp.directives', 'myApp.controllers' ]);
	as.value('apiUrl', 'http://localhost:8092/WebExample/');