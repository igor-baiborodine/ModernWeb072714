(function () {
    'use strict';

    var ProductService = function ($http, $q) {
        // Instance attributes go here:
        this.$http = $http;
        this.$q = $q;
    };

    /** List all dependencies required by the service. */
    ProductService.$inject = ['$http', '$q'];

    // Instance methods go here:
    ProductService.prototype = {

        /** Returns the list of all available products on the server. */
        getProducts: function () {
            return this.$http.get('data/products-featured.json')
              .then(function (resp) { return resp.data; });
        },

        /** Finds products with specified criteria.
          * NOTE: Search criteria are not implemented yet.
          */
        find: function () {
            return this.$http.get('data/products-search.json')
              .then(function (resp) { return resp.data; });
        },


        /** Finds products by its ID. */
        getProductById_: function (productId) {
            return this.getProducts().then(function (products) {
                return _.find(products, function (product) {
                    return product.id === productId;
                });
            });
        },

        getProductById: function (productId) {

            var _this = this;
            var defer = _this.$q.defer();

            return _this.find().then(filterSearchProducts);

            function filterSearchProducts(products) {
                var filtered = products.filter(sameProductId);

                if (filtered.length == 1) {
                    defer.resolve(filtered[0]);
                    return defer.promise;
                } else {
                    return _this.getProducts().then(filterFeaturedProducts);
                }
            }

            function filterFeaturedProducts(products) {
                var filtered = products.filter(sameProductId);

                if (filtered.length == 1) {
                    defer.resolve(filtered[0]);
                } else {
                    defer.reject();
                }
                return defer.promise;
            }

            function sameProductId(product) {
                return product.id === productId;
            }
        }

    };

    // Register the service within AngularJS DI container.
    angular.module('auction').service('ProductService', ProductService);
}());
