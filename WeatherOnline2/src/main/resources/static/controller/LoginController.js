/**
 * Created by admarcu on 11/27/2016.
 */
'use strict';

angular.module('myApp')
    .controller('LoginController', function ($uibModal, userService, $scope, $rootScope, $http, $routeParams, $location, $sessionStorage) {

        console.log("LoginController Up!");
        console.log($sessionStorage.username);
        $scope.ok = function () {

            $http({
                method: 'POST',
                url: '/user/login',
                data: {
                    username: $scope.username,
                    password: $scope.password
                }

            }).then(function successCallback(response) {
                console.log(response.data);
                if (response.data === true) {
                    //console.log("true>>>")
                    $rootScope.loggedUser = $scope.username;
                    userService.currentUser = $scope.username;


                    $sessionStorage.username = $scope.username;
                    $scope.$storage = $sessionStorage.username;

                    $location.path('/city')
                } else {
                    // console.log("false>>>")
                    $scope.loginError="Invalid Username or Password!";
                    $location.path('/')
                }
            });
        };

;
        $scope.register = function () {
            var modalInstance = $uibModal.open({
                templateUrl: 'template/register.html',
                controller: 'RegisterController'
            });

            modalInstance.result.then(function (user) {
                console.log("AICI!")
            });

            //$location.path('/register')
        };


    });
