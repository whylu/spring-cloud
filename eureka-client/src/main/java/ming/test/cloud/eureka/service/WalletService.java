package ming.test.cloud.eureka.service;

import ming.test.cloud.eureka.TestController;
import ming.test.cloud.eureka.dto.FreezeWallet;
import ming.test.cloud.eureka.dto.OrderInsertRequest;
import ming.test.cloud.eureka.dto.Response;
import ming.test.cloud.eureka.dto.Status;
import ming.test.cloud.eureka.dto.WalletChangeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Service
public class WalletService {
    private static Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private RestTemplate restTemplate;

    public Optional<WalletChangeResult> freezeAsset(String uuid, OrderInsertRequest request) {
        FreezeWallet freezeRequest = createFreezeRequest(request);
        freezeRequest.setRequestId(uuid);

        RequestEntity<FreezeWallet> requestEntity = RequestEntity.post(URI.create("http://wallet-service/wallet/freeze"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(freezeRequest);
        try {
            ParameterizedTypeReference<Response<WalletChangeResult>> typeRef = new ParameterizedTypeReference<Response<WalletChangeResult>>() {};

            ResponseEntity<Response<WalletChangeResult>> responseEntity = restTemplate.exchange(requestEntity, typeRef);
            Response<WalletChangeResult> response = responseEntity.getBody();
            if(response.getErrorCode()==0 && response.getData() instanceof WalletChangeResult) {
                if(response.getData().getStatus()== Status.COMPLETED) {
                    return Optional.of(response.getData());
                } else {
                    return Optional.empty();
                }
            }
        } catch (Exception e) {
            logger.warn("failed to freeze asset", e);
            // handle failure
        }
        return Optional.empty();
    }

    private FreezeWallet createFreezeRequest(OrderInsertRequest request) {
        FreezeWallet freezeWallet = new FreezeWallet();
        freezeWallet.setCurrency(request.getQuote());
        freezeWallet.setAmount(request.getPrice().multiply(request.getSize()));
        freezeWallet.setUsername(request.getUsername());
        return freezeWallet;
    }
}
