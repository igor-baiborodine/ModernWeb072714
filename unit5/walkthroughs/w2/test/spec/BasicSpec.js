describe('Sanity check spec', function () {
    beforeEach(function () {
        console.log("Running before each spec...");
    });
    afterEach(function () {
        console.log("Running after each spec...");
    });
    it('should be green', function () {
        var color = "green";
        alert(color);
        expect(color).toBe("green");
    });
    it('should be red', function () {
        var color = "green";
        expect(color).not.toBe("red");
    });

    // this spec will be ignored
    xit('should be red', function () {
        var color = "green";
        expect(color).not.toBe("red");
    });
    // this suite also will be ignored
    xdescribe("nested suite", function () {
        it('another spec', function () {

        });
    });
});

