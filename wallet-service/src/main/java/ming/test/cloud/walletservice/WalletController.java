package ming.test.cloud.walletservice;

import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.dto.FreezeWallet;
import ming.test.cloud.walletservice.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/wallet")
@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Wallet> getWallet(@PathVariable String username) {
        return walletService.getByUser(username);
    }

    @PostMapping(value = "/freeze", produces = MediaType.APPLICATION_JSON_VALUE)
    public Status freeze(@RequestBody FreezeWallet freezeWallet) {
        Wallet wallet = walletService.getWallet(freezeWallet.getUsername(), freezeWallet.getCurrency());
        if(wallet==null) {
            return Status.FAILED;
//            return error ;
        }
        Status status = walletService.freeze(wallet, freezeWallet);

        return status;
    }
}
