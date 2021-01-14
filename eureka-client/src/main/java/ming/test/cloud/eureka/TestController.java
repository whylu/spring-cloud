package ming.test.cloud.eureka;

import ming.test.cloud.eureka.dto.FreezeWallet;
import ming.test.cloud.eureka.dto.OrderInsertRequest;
import ming.test.cloud.eureka.dto.Response;
import ming.test.cloud.eureka.dto.Status;
import ming.test.cloud.eureka.dto.WalletChangeResult;
import ming.test.cloud.eureka.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

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


    @Autowired
    private WalletService walletService;

    @PostMapping(value = "/insertOrder")
    public String insertOrder(@RequestBody OrderInsertRequest request) {
        String uuid = UUID.randomUUID().toString();

        Optional<WalletChangeResult> walletChangeResult = walletService.freezeAsset(uuid, request);
        if(walletChangeResult.isPresent()) {
            return uuid;
        }
        return "-1";
    }

}
