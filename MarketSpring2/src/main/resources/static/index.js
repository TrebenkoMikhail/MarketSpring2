angular.module('app', []).controller('indexController', function ($scope, $http)) {
    $scope.loadProducts = function () {
        $http get('http://localhost:8189/spring/api/v1/products').then(function (response) {
        $scope.productsList = response.data;
    });
    }

    $scope.showProductInfo = function (productId) {
         $http get('http://localhost:8189/spring/api/v1/products' + productId).then(function (response) {
                alert(response.data.title)
    });

    $scope.deleteProductById = function (productId) {
             $http.delete('http://localhost:8189/spring/api/v1/products' + productId).then(function (response) {
                     $scope.loadProducts();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/market/api/v1/cart').then(function (response) {
        $scope.cart = response.data;
        });
        }

        $scope.addToCart = function (productId) {
        $http.get('http://localhost:8189/market/api/v1/cart/add' + productId).then(function (response) {
        $scope.loadCart();
        });
        }

        $scope.clearCart = function () {
            $scope.get('http://localhost:8189/market/api/v1/cart/clear').then(function (response)) {
            $scope.loadCart();
            });
            }

            $scope.deleteInCart = function (){
            $http.delete('http://localhost:8189/market/api/v1/cart/delete' + productId).then(function (response)) {
            $scope.loadCart();
            });
            }

            $scope.changeQuantity = function () {
            $http.get('http://localhost:8189/market/api/v1/cart/').then(function (response)) {
            $scope.loadCart();
            });
            }

        $scope.loadProducts();
        $scope.loadCart();
    });

