package ming.test.cloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${hello.message}")
    private String helloMessage;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        List<ServiceInstance> instances = this.discoveryClient.getInstances(applicationName);
        ServiceInstance serviceInstance = instances.get(0);
        return this.discoveryClient.getInstances(applicationName);
    }

    @GetMapping(value = "/hello/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloObject hello(@PathVariable String username) {
        HelloObject obj = new HelloObject(helloMessage, username);
        return obj;
    }
}
