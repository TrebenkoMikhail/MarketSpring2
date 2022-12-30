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

    $scope.loadProducts();
//
//    $scope.fillTable = function () {
//        $http.get(contextPath + '/app/v1/products')
//            .then(function (response)) {
//                $scope.ProductsList = response.data;
//            });
//    };
//
//    $scope.submitCreateNewProduct = function () {
//        $http.post(contextPath + '/app/v1/products', $scope.newProduct)
//            .then(function (response) {
//            // $scope.BookList.push(response.data);
//            $scope.fillTable();
//            });
//};
////
//// $scope.applyFilter = function () {
////       $http({
////              url: contextPath + '/app/v1/books',
////              method: "GET"
////              params: {book_title: $scope.bookFilter.title}
////              }).then(function (response)) {
////              $scope.BookList = response.data;
////        });
//// }
//
//$scope.deleteProductById = function(productId) {
//    console.log('deleteTest');
//    $http({
//    url: contextPath + '/api/v1/products/' + productId;
//    method: "DELETE"
//    }).then(function (response) {
//        $scope.fillTable();
//    });
//}
//
//$scope.fillTable();