(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                 templateUrl: 'store/store.html',
                 controller: 'storeController'
            })
             .when('/cart', {
                 templateUrl: 'cart/cart.html',
                 controller: 'cartController'
             })
             .when('/orders', {
                 templateUrl: 'orders/orders.html',
                 controller: 'ordersController'
             })
             .when('/registration', {
                 templateUrl: 'registration/registration.html',
                 controller: 'registrationController'
             })

//             .when('/order_pay/orderId', {
//                 templateUrl: 'order_pay/order_pay.html',
//                 controller: 'orderPayController'
//             })
            .otherwise({
                redirectTo: '/'
            });
    }
}

        function run($rootScope, $http, $localStorage) {
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

            if (!$localStorage.springMarketGuestCardId) {
                $http.get('http://localhost:5555/cart/api/v1/cart/generate_uuid')
                    .then(function successCallback(response) {
                        $localStorage.springMarketGuestCardId = response.data.value;
                    });
            }
     }
     })();

angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage)) {


    $rootScope.tryToLogout = function() {
        $scope.clearUser();
        $scope.user = null;
        $scope.path('/')
    };

    $scope.clearUser = function () {
        delete $localStorage.springMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function() {
        if ($localStorage.springMarketUser) {
            return true;
        } else {
        return false;
        }
    };


