(function () {
    'use strict';

    var auctionModule = angular.module('auction', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {

        var title = function (page) {
          return 'Auction | ' + page;
        };

        $routeProvider
            .when('/', {
                templateUrl: 'views/home.html',
                controller: 'HomeController',
                controllerAs: 'ctrl',
                title: title('Home')
            })
            .when('/search', {
                templateUrl: 'views/search.html',
                controller: 'SearchController',
                controllerAs: 'ctrl',
                title: title('Search')
            })
            .otherwise({
               redirectTo: '/'
        });
    }]);

    auctionModule.run(['$rootScope', function ($rootScope) {
            $rootScope.$on('$routeChangeStart', function (event, next, current) {
                $rootScope.pageTitle = next.$$route.title;
            });
        }]);
    /*
    .run(['$rootScope', function ($rootScope) {
        $rootScope.$on('$routeChangeStart', function (event, next) {
            $rootScope.pageTitle = next.$$route.title;
        });
    }]);
    */

}());
