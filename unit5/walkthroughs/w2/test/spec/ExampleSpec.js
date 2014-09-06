describe("My function under test should", function () {
    it("return on", function () {
        // place specification code here
        //
    });
    describe("another suite", function () {
        it("spec1", function () {

        });
    });
    it("my another spec", function () {
        var truth = true;
        expect(truth).toBeTruthy();
    });
    it("2+2 = 4", function () {
        expect(2 + 2).toEqual(4);
    });

    it("be part of super agents", function () {
        expect("James Bond").toBeSecretAgent();
        expect("Jason Bourne").toBeSecretAgent();
        // expect("Austin Powers").toBeSecretAgent();
        expect("Austin Powers").not.toBeSecretAgent();
    });
});
