/**
 * Created by admarcu on 11/28/2016.
 */
'use strict';

angular.module('myApp')
    .controller('CityController', function (userService,$scope, $http, $rootScope, $routeParams, $location,$sessionStorage) {
        console.log("City Up!");
        userService.currentUser=$sessionStorage.username;
        $rootScope.loggedUser=$sessionStorage.username;
        console.log(userService.currentUser);
        console.log($rootScope.loggedUser);
        console.log(userService.currentUser.username);
        console.log("asdsadsadsadsa!");
        $scope.cities = [];
        delete $scope.cityadd;

        $scope.$storage=$sessionStorage;
        console.log($sessionStorage);
        console.log($scope.$storage);
        console.log($scope.$storage.username);

        $scope.logout = function () {
            console.log("LOGOUT PRESSED");
            $sessionStorage.username=undefined;
            $location.path('/');
        }

        //$scope.xxx="admin";
        $http({
            method: 'GET',
            url: '/user/' + $sessionStorage.username
        }).then(function successCallback(response) {
            //userService.currentUser = response.data;
            $scope.loggedUser= response.data; ////
            $scope.loggedUser.cities.sort();

            console.log(response.data);
        });

        $scope.add = function () {
            console.log("Add pressed");
            $scope.array = [];
            $http({
                method: 'POST',
                url: '/city/' + $sessionStorage.username,
                data:  {
                    cityName: $scope.cityName,
                    users: $scope.array
                }
            }).then(function successCallback(response) {
                $scope.cityadd = response.data; ////
                console.log($scope.cityadd);
                console.log("--------");
                console.log($scope.loggedUser.cities);
                console.log("--------");
                var change=0;
                if( angular.isUndefined($scope.loggedUser.cities[0])){
                    console.log("IN UNDEFINED")
                    $scope.loggedUser.cities.push($scope.cityadd);
                }else{
                    for(var i =0; i< $scope.loggedUser.cities.length; i++) {
                        console.log("<>");
                        console.log($scope.loggedUser.cities[i]);
                        console.log($scope.loggedUser.cities[i].cityId +":"+$scope.cityadd.cityId);
                        if($scope.loggedUser.cities[i].cityId === $scope.cityadd.cityId) {
                            console.log("ORAS DEJA EXISTEN IN UI");
                            change=0;
                            break;
                        }
                        else{
                            console.log("Adauga in for");
                            change=1;

                        }
                    }
                    if(change===1){
                        $scope.loggedUser.cities.push($scope.cityadd);
                    }
                }


                //console.log($scope.ulala.cities);
            },function (error) {
                console.log(error);
                $scope.error = "Invalid city name!"
            });

        }

        $scope.view = function(id) {
            $rootScope.pressedId=id;
            $location.path("/weather");
           //
        };



        $scope.delete = function (id) {
            $http({
                method: 'DELETE',
                url: '/city/' + id + '/' + $sessionStorage.username
            }).then(function successCallback(response) {
                var indexToDelete = null;
                for(var i =0; i< $scope.loggedUser.cities.length; i++) {
                    if($scope.loggedUser.cities[i].cityId == id) {
                        indexToDelete = i;
                        console.log(indexToDelete);
                    }
                }
                $scope.loggedUser.cities.splice(indexToDelete,1);
                console.log("City deleted" +id );
           });

        }

    });
