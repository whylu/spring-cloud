package ming.test.cloud.walletservice;

import ming.test.cloud.walletservice.dto.FreezeWallet;
import ming.test.cloud.walletservice.dto.WalletChangeResult;
import ming.test.cloud.walletservice.model.Status;
import ming.test.cloud.walletservice.model.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> getByUser(String username);

    Wallet getWallet(String username, String currency);

    WalletChangeResult freeze(Wallet wallet, FreezeWallet freezeWallet);
}
