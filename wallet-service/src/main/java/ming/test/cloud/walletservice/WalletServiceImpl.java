package ming.test.cloud.walletservice;

import ming.test.cloud.walletservice.dto.FreezeWallet;
import ming.test.cloud.walletservice.dto.WalletChangeResult;
import ming.test.cloud.walletservice.mapper.WalletMapper;
import ming.test.cloud.walletservice.model.Action;
import ming.test.cloud.walletservice.model.Status;
import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.model.WalletChangeLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private static Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

    @Autowired
    private WalletMapper walletMapper;


    @Override
    public List<Wallet> getByUser(String username) {
        return walletMapper.selectByUser(username);
    }

    @Override
    public Wallet getWallet(String username, String currency) {
        return walletMapper.selectByUserAndCurrency(username, currency);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public WalletChangeResult freeze(Wallet wallet, FreezeWallet freezeWallet) {
        WalletChangeLog log = new WalletChangeLog();
        log.setWalletId(wallet.getId());
        log.setAction(Action.FREEZE);
        log.setStatus(Status.FAILED);
        log.setAmount(freezeWallet.getAmount());
        log.setRequestId(freezeWallet.getRequestId());

        WalletChangeResult result = new WalletChangeResult();
        result.setRequestId(freezeWallet.getRequestId());
        result.setWalletId(wallet.getId());
        try {
            Status status = walletMapper.doFreezeAndLog(wallet.getId(), freezeWallet, log);
            result.setStatus(status);
        } catch (Exception e) {
            logger.debug("[MN:freeze] failed");
            walletMapper.insertWalletChangeLog(log);
            result.setStatus(Status.FAILED);
        }
        result.setLogId(log.getId());
        return result;
    }



}
