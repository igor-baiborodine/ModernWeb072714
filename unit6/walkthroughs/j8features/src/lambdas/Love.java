package lambdas;

/**
 * Created by yfain11 on 4/3/14.
 */
public class Love {
    public static void main(String[] args) {


    // Anonymous class implementation
     Lovable anonymousLove = new Lovable(){
        public void showLove(){
            System.out.println("I love you the old way");
        }
    };

    anonymousLove.showLove();

    // Since Lovable has only one method declared, we don't need to
    // specify its name and use lambda expression to implement functional interface
    Lovable lambdaLove = () -> System.out.println("I love you the new way");

    lambdaLove.showLove();

    // Call the method propose passing the lambda as an argument
    propose(lambdaLove);

    }

    public static void propose(Lovable lvbl){
        System.out.println("My darling, why did you bring me to Paris?");
        lvbl.showLove();
        System.out.println("OMG! OMG! OMG! Where's the ring?");
    }
}
