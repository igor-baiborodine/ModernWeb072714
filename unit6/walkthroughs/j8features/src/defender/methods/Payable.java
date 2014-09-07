package defender.methods;

/**
 * Created by yfain11 on 3/31/14.
 */
public interface Payable {

   default void increasePay(){
       System.out.println("Ain't no need your increases");
   }
}
