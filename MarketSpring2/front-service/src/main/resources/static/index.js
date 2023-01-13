angular.module('app', []).controller('indexController', function ($scope, $http)) {


         let jwt = $localStorage.springMarketUser.token; //TODO: доделать
         let payload = JSON.parse(atob(jwt.split('.')[1]))
         let currentTime = parseInt(new Date().getTime() / 1000);
         if (currentTime > payload.exp) {
             console.log("Token is expired")
             delete $localStorage.springMarketUser;
             $http.defaults.headers.common.Authorization = '';
    }
} catch (e) {
}
    $http.defaults.headers.common.Authorization = 'Bearer' + $localStorage.springMarketUser.token;

    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/spring/api/v1/products').then(function (response) {
        $scope.productsList = response.data;
    });
    }

    $scope.createOrder = function () {
            $http.post('http://localhost:8189/spring/api/v1/orders').then(function (response) {
            alert('Заказ оформлен');
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
        $http.get('http://localhost:8190/spring-carts/api/v1/cart').then(function (response) {
        $scope.cart = response.data;
        });
        }

        $scope.addToCart = function (productId) {
        $http.get('http://localhost:8190/spring-carts/api/v1/cart/add' + productId).then(function (response) {
        $scope.loadCart();
        });
        }

        $scope.clearCart = function () {
            $scope.get('http://localhost:8190/spring-carts/api/v1/cart/clear').then(function (response)) {
            $scope.loadCart();
            });
            }

            $scope.deleteInCart = function (){
            $http.delete('http://localhost:8190/spring-carts/api/v1/cart/delete' + productId).then(function (response)) {
            $scope.loadCart();
            });
            }

        $scope.loadProducts();
        $scope.loadCart();
    });

