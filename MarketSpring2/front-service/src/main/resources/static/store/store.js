angular.module('market').controller('storeController', function ($scope, $http, $localStorage)) {
        const contextPath = 'http://localhost:5555/core';
        const cartContextPath = 'http://localhost:5555/cart';

        $scope.loadProducts = function () {
               $http.get(contextPath + '/api/v1/products').then(function (response) {
               $scope.productsList = response.data;
           });
           }

           $scope.showProductInfo = function (productId) {
                $http get(contextPath + '/api/v1/products' + productId).then(function (response) {
                       alert(response.data.title)
           });

           $scope.deleteProductById = function (productId) {
                    $http.delete(contextPath + '/api/v1/products' + productId).then(function (response) {
                            $scope.loadProducts();
               });
           }

        $scope.addToCart = function (productId) {
        $http.get(cartContextPath + '/api/v1/cart/' + $localStorage.springMarketGuestCardId + 'add' + productId).then(function (response) {
         });
        }

        $scope.loadProducts();

    });

}