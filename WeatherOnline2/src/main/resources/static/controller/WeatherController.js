/**
 * Created by admarcu on 11/27/2016.
 */
'use strict';

angular.module('myApp')
    .controller('WeatherController', function ($scope, $rootScope, $http, $routeParams, $location, $sessionStorage) {

        $scope.weather=[];

        $rootScope.loggedUser=$sessionStorage.username;

        console.log("Weather Up!");
        console.log($rootScope.pressedId);

        console.log( $scope.loggedUser);
        $scope.logout = function () {
            console.log("LOGOUT PRESSED");
            $sessionStorage.username=undefined;
            $location.path('/');
        }

        $http({
            method: 'POST',
            url: '/city/getWeather',
            data: $rootScope.pressedId
        }).then(function successCallback(response) {
            $scope.weather= response.data; ////
            console.log(response.data);
        });


        $scope.back = function() {
            $location.path("/city");
        };




    });
