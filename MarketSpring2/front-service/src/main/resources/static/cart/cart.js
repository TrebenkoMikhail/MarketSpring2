angular.module('market').controller('cartController', function ($scope, $http, $localStorage)) {
        const contextPath = 'http://localhost:5555/cart/';
        const coreContextPath = 'http://localhost:5555/core/';

$scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart' + $localStorage.springMarketGuestCardId).then(function (response) {
        $scope.cart = response.data;
        });
        }



        $scope.clearCart = function () {
            $scope.get(contextPath + '/api/v1/cart' + $localStorage.springMarketGuestCardId + '/clear').then(function (response)) {
            $scope.loadCart();
            });
            }

            $scope.deleteInCart = function () {
            $http.delete(contextPath + '/api/v1/cart' + $localStorage.springMarketGuestCardId + '/delete' + productId).then(function (response)) {
            $scope.loadCart();
            });
            }

            $scope.createOrder = function () {
                        $http.post(coreContextPath + '/api/v1/orders').then(function (response) {
                        alert('Заказ оформлен');
                        $scope.loadCart();
                    });
                    }

        $scope.loadProducts();

    });

}