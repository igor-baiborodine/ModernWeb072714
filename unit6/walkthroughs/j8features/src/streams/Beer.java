package streams;

/**
 * Created by yfain11 on 4/4/14.
 */
public class Beer {
    public String name;
    public String country;
    public float price;

    Beer(String name, String country,float price){
        this.name=name;
        this.country=country;
        this.price=price;
    }

    public String toString(){
        return "Name: " + name + ", price: " + price;
    };
}
