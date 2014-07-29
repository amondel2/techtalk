//= require controllers
//= require directives
//= require filters
//= require services

'use strict';
	var as = angular.module('myApp', [ 'myApp.directives', 'myApp.controllers' ]);
	as.value('apiUrl', 'http://localhost:8091/WebExample/');