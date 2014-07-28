// Athother way of using closures
function Person(name){

    this.name = name;

}

Person.prototype.doTaxes= function(){

    var taxDeduction = 500;

    //private function
    function mafiaSpecial(income){
        return income*0.05 - taxDeduction*2;
    }

    //exposed function
    return function(income) {

        var yourTax;

        if (this.name != "God Father"){
            yourTax =   income*0.05 - taxDeduction;
        } else{
            yourTax =   mafiaSpecial(income);
        }

        console.log( "   My dear " + this.name + ", your tax is "+ yourTax);
        return yourTax;
    }

}();

var p1=new Person("John Smith");
p1.doTaxes(100000);

var p2= new Person("God Father");
p2.doTaxes(100000);

mafiaSpecial();  // will give an error - this function is private 
