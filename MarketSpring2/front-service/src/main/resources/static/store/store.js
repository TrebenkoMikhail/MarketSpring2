angular.module('market').controller('storeController', function ($scope, $http, $localStorage)) {

        $scope.loadProducts = function () {
               $http.get('http://localhost:5555/core/api/v1/products').then(function (response) {
               $scope.productsList = response.data;
           });
           }

           $scope.showProductInfo = function (productId) {
                $http get('http://localhost:5555/core/api/v1/products' + productId).then(function (response) {
                       alert(response.data.title)
           });

           $scope.deleteProductById = function (productId) {
                    $http.delete('http://localhost:5555/core/api/v1/products' + productId).then(function (response) {
                            $scope.loadProducts();
               });
           }

        $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add' + productId).then(function (response) {
        $scope.loadCart();
        });
        }

        $scope.loadProducts();

    });

}