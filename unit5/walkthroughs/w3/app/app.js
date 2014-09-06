var app = angular.module('walkthrough', ['ngRoute']);

app.value('mode', 'app');
app.value('version', 'v0.0.4');

app.directive('waPanel', function () {
    return function (scope, element) {
        element.addClass('wa-panel');
    }
});

app.controller("AppCtrl", function () {
    this.hamlet = "To be or not to be";
});

app.filter('reverse', function () {
    return function (message) {
        return message.split("").reverse().join("");
    }
});

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/main.html',
            controller: 'MainCtrl'
        })
        .when('/search', {
            templateUrl: 'views/search.html',
            controller: 'SearchCtrl'
        });
}]);
