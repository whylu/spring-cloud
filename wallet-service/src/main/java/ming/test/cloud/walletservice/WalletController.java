package ming.test.cloud.walletservice;

import ming.test.cloud.walletservice.dto.Response;
import ming.test.cloud.walletservice.dto.FreezeWallet;
import ming.test.cloud.walletservice.dto.WalletChangeResult;
import ming.test.cloud.walletservice.model.ErrorCode;
import ming.test.cloud.walletservice.model.Status;
import ming.test.cloud.walletservice.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> freeze(@RequestBody FreezeWallet freezeWallet) {
        Wallet wallet = walletService.getWallet(freezeWallet.getUsername(), freezeWallet.getCurrency());
        if(wallet==null) {
            return ResponseEntity.badRequest().body(Response.error(ErrorCode.WALLET_NOT_FOUND));
        }
        WalletChangeResult result = walletService.freeze(wallet, freezeWallet);
        return Response.ok(result);
    }
}
