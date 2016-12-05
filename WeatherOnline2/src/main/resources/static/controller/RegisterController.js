/**
 * Created by admarcu on 12/3/2016.
 */
angular.module('myApp')
    .controller('RegisterController', function ($uibModalInstance, userService, $scope, $rootScope, $http, $routeParams, $location, $sessionStorage) {
       console.log("Register up!");
           $scope.cancel = function () {
                  $uibModalInstance.dismiss('cancel');
           };

           $scope.register = function() {
               console.log("Register pressed!");

           $http({
                  method: 'POST',
                  url: '/user/register',
                  data:  {
                         username: $scope.Username,
                         password: $scope.Password
                  }
           }).then(function successCallback(response) {
                  $uibModalInstance.close(response.data);
           }).catch(function(response) {
                  $scope.error = "Username already in use!"
                  console.error('Gists error', response.status, response.data);
           }, function error(response) {
                  console.log("ERORRRR!")
           });


           };

});