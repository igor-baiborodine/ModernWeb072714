describe('hello angular', function () {
    var element;
    var $scope;
    //beforeEach(angular.mock.inject(function ($compile, $rootScope) {
    beforeEach(inject(function ($compile, $rootScope) {
        $scope = $rootScope;
        element = angular.element('<div wa-panel>{{ 2 + 2 }}</div>');
        element = $compile(element)($rootScope);
    }));
    it("should be 4", function () {
        $scope.$digest();
        expect(element.html()).toBe("4");
    });
});
