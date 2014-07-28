(function (){

    var taxDeduction = 500;

    //exposed function
    doTaxes=function(income, customerName) {

        var yourTax;

        if (customerName != "God Father"){
            yourTax =   income*0.05 - taxDeduction;
        } else{
            yourTax =   mafiaSpecial(income);
        }

        console.log( "   Dear " + customerName + ", your tax is "+ yourTax);
        return yourTax;
    }

    //private function
    function mafiaSpecial(income){
        return income*0.05 - taxDeduction*2;
    }

    var i=18;
})();


window.doTaxes(100000, "John Smith");  // The closure knows its context: taxDeduction=500

doTaxes(100000, "God Father");
mafiaSpecial();  // will give an error - this function is private 
