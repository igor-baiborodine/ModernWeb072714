function Person(lname, fname){
    this.lastName=lname;
    this.firstName=fname;

    this.marryMe = function(person){
        console.log("Will you marry me " + person.firstName);
    }

}

var p1= new Person("Smith", "Joe");
var p2= new Person("Roberts", "Julia") ;

p1.marryMe(p2);
