package ming.test.cloud.walletservice;

import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.dto.FreezeWallet;
import ming.test.cloud.walletservice.model.Status;

import java.util.List;

public interface WalletService {
    List<Wallet> getByUser(String username);

    Wallet getWallet(String username, String currency);

    Status freeze(Wallet wallet, FreezeWallet freezeWallet);
}
