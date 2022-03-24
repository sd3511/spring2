angular.module('app', []).controller('indexController', function ($scope, $http) {
    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/winter/api/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8189/winter/api/v1/cart').then(function (response) {
            $scope.cartList = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        console.log(productId);
        $http.get('http://localhost:8189/winter/api/v1/cart/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }
    $scope.deleteFromCart = function (productId) {
        $http.get('http://localhost:8189/winter/api/v1/cart/delete/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }


    $scope.deleteProductById = function (productId) {
        $http.delete('http://localhost:8189/winter/api/v1/products/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.loadProducts();

    // const contextPath = 'http://localhost:8189/market';
    //
    // $scope.fillTable = function () {
    //     $http.get(contextPath + '/api/v1/products')
    //         .then(function (response) {
    //             $scope.ProductsList = response.data;
    //         });
    // };
    //
    // $scope.submitCreateNewProduct = function () {
    //     $http.post(contextPath + '/api/v1/products', $scope.newProduct)
    //         .then(function (response) {
    //             // $scope.BooksList.push(response.data);
    //             $scope.fillTable();
    //         });
    // };
    //
    // $scope.applyFilter = function () {
    //     $http({
    //         url: contextPath + '/api/v1/books',
    //         method: "GET",
    //         params: {book_title: $scope.bookFilter.title}
    //     }).then(function (response) {
    //         $scope.BooksList = response.data;
    //     });
    // }

    // $scope.deleteProductById = function(productId) {
    //     console.log('deleteTest');
    //     $http({
    //         url: contextPath + '/api/v1/products/' + productId,
    //         method: "DELETE"
    //     }).then(function (response) {
    //         $scope.fillTable();
    //     });
    // }
    //
    // $scope.fillTable();
});