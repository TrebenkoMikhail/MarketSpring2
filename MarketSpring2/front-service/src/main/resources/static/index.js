angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage)) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
                }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function() {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.isUserLoggedIn = function() {
        if ($localStorage.springMarketUser) {
            return true;
        } else {
        return false;
        }
    };




    if ($localStorage.springMarketUser) {
      try {
         let jwt = $localStorage.springMarketUser.token;
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
    }

    $scope.loadProducts = function () {
        $http.get('http://localhost:5555/core/api/v1/products').then(function (response) {
        $scope.productsList = response.data;
    });
    }

    $scope.createOrder = function () {
            $http.post('http://localhost:5555/core/api/v1/orders').then(function (response) {
            alert('Заказ оформлен');
            $scope.loadCart();
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

    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart').then(function (response) {
        $scope.cart = response.data;
        });
        }

        $scope.addToCart = function (productId) {
        $http.get('http://localhost:5555/cart/api/v1/cart/add' + productId).then(function (response) {
        $scope.loadCart();
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

        $scope.loadProducts();
        $scope.loadCart();
    });

