(function () {
  'use strict';

  angular.module('auction', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
      // STEP 2A

      $routeProvider
        .when('/', {
          templateUrl: 'views/home.html',
          controller: 'HomeController',
          controllerAs: 'ctrl'
          // STEP 2B
        })
        .when('/search', {
          templateUrl: 'views/search.html',
          controller: 'SearchController',
          controllerAs: 'ctrl'
          // STEP 2B
        })
        .otherwise({
           redirectTo: '/'
        });
    }]);
    // STEP 2C
    // STEP 2D
    // STEP 2E
}());
