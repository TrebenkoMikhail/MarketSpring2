angular.module('market').controller('cartController', function ($scope, $http, $localStorage)) {


$scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart').then(function (response) {
        $scope.cart = response.data;
        });
        }



        $scope.clearCart = function () {
            $scope.get('http://localhost:5555/cart/api/v1/cart/clear').then(function (response)) {
            $scope.loadCart();
            });
            }

            $scope.deleteInCart = function (){
            $http.delete('http://localhost:5555/cart/api/v1/cart/delete' + productId).then(function (response)) {
            $scope.loadCart();
            });
            }

            $scope.createOrder = function () {
                        $http.post('http://localhost:5555/core/api/v1/orders').then(function (response) {
                        alert('Заказ оформлен');
                        $scope.loadCart();
                    });
                    }

        $scope.loadProducts();

    });

}