(function () {
  'use strict';

  var datepickerDirectiveFactory = function () {
    return {
      scope: false,
      restrict: 'A',
      link: function (scope, element) {
        element.datepicker();
      }
    };
  };

  angular.module('auction').directive('auctionDatepicker', datepickerDirectiveFactory);
}());
