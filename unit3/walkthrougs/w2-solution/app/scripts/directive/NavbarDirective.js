// STEP 3A
(function () {
  // STEP 3B
  'use strict';

  // STEP 3C
  var navbarDirectiveFactory = function () {
    // STEP 3D
    return {
      scope: false,
      restrict: 'E',
      templateUrl: 'views/partial/NavbarDirective.html'
    };
  };

  // STEP 3E
  angular.module('auction').directive('auctionNavbar', navbarDirectiveFactory);
}());
