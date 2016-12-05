/**
 * Created by admarcu on 11/26/2016.
 */
'use strict';
var app = angular.module("myApp", ['ngRoute','ngStorage','ui.bootstrap']);
console.log("merge");
app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: '/template/login.html',
        controller: 'LoginController',
        resolve: {check : function (userService, $location,$sessionStorage) {

            if($sessionStorage.username === undefined) {
                $location.path("/");
            } else {
                $location.path("/city");
                console.log("Good");
            }
        }}
    }).when('/city', {
        templateUrl: 'template/cities.html',
        controller: 'CityController',
        resolve: {check : function (userService, $location,$sessionStorage) {
            if($sessionStorage.username === undefined) {
                $location.path("/");
            } else {
                $location.path("/city");
                console.log("Good");
            }
        }}

    }).when('/weather', {
        templateUrl: 'template/weather.html',
        controller: 'WeatherController',
        resolve: {check : function (userService, $location) {
            if(!userService.currentUser) {
                $location.path("/");
            } else {
                console.log("Good");
            }
        }}
    })
});

app.controller('myApp', function($scope,$sessionStorage) {


});

