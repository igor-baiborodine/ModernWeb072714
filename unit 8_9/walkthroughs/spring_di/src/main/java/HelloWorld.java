import com.farata.course.mwd.auction.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author Viktor Gamov (viktor.gamov@faratasystems.com)
 * @since 9/20/14
 */
public class HelloWorld {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext("com.farata.course.mwd.auction");


        /*ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("context.xml");*/

        final ProductService productService =
            (ProductService) applicationContext.getBean("productService");
        System.out.println(productService.getAllProducts());
    }
}
