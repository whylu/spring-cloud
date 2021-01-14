package ming.test.springcloud;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {

    @Value("${test.prop.a: defaultValue_a}")
    private String a;
    @Value("${test.prop.b: defaultValue_b}")
    private String b;

    @Value("${bootiful.x: defaultValue_x}")
    private String x;
    @Value("${bootiful.y: defaultValue_y}")
    private String y;
    @Value("${bootiful.z: defaultValue_z}")  // this is not in config
    private String z;

    @RequestMapping("/hello")
    String getMessage() {
        return "a="+a + ", b="+b + ", x="+x + ", y="+y + ", z="+z;
    }

}
