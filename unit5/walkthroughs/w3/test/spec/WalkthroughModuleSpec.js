describe('walkthrough module', function () {
    beforeEach(module('walkthrough'));

    describe('testing the injections', function () {
        it('version should be v0.0.4', inject(function (version) {
            expect(version).toBe('v0.0.4');
        }));

        it('mode should be app', inject(function (mode) {
            expect(mode).toBe('app');
        }));
    });

    describe('testing a directive', function () {
        var element;
        beforeEach(inject(function ($compile, $rootScope) {
            element = angular.element('<div wa-panel></div>');
            element = $compile(element)($rootScope);
        }));
        it('should have a class panel', function () {
            expect(element.hasClass('wa-panel')).toBe(true);
        });
    });

    describe('testing a controller', function () {
        var appController;

        beforeEach(inject(function ($controller) {
            appController = $controller('AppCtrl');
        }));

        it('should have greeting Hi there', function () {
            expect(appController.hamlet).toBe('To be or not to be');
        });
    });

    describe('testing a filter', function () {
        it('reverse filter should reverse a string', inject(function (reverseFilter, $filter) {
            expect(reverseFilter('ABCD')).toEqual('DCBA');
            expect(reverseFilter('Vik')).toEqual('kiV');

            // another form
            expect($filter('reverse')(('ABCD'))).toEqual('DCBA');
            expect($filter('reverse')(('Vik'))).toEqual('kiV');
        }));
    });

    describe('testing a service', function () {
        it('location should be empty', inject(function ($location) {
            expect($location.path()).toBe('');
        }));
        it('should have a working /home route', inject(function ($location, $rootScope) {
            $location.path('/home');
            $rootScope.$digest();

            expect($location.path()).toBe('/home');
        }));
    });

    describe('testing a route', function () {
        describe('main route', function () {
            it('should use main.html view ', inject(function ($route) {
                expect($route.routes['/'].templateUrl).toEqual('views/main.html');
            }));
            it('should handled by MainCtrl', inject(function ($route) {
                expect($route.routes['/'].controller).toEqual('MainCtrl');
            }));
        });
        describe('search route', function () {
            it('should use search.html view', inject(function ($route) {
                expect($route.routes['/search'].templateUrl).toEqual('views/search.html');
            }));
            it('should handled by SearchCtrl', inject(function ($route) {
                expect($route.routes['/search'].controller).toEqual('SearchCtrl');
            }));
        });
    });
});
