package ming.test.cloud.walletservice;

import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.dto.FreezeWallet;
import ming.test.cloud.walletservice.mapper.WalletMapper;
import ming.test.cloud.walletservice.model.Action;
import ming.test.cloud.walletservice.model.Status;
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


    @Transactional
    @Override
    public Status freeze(Wallet wallet, FreezeWallet freezeWallet) {
        logger.debug("freeze...");
        WalletChangeLog log = new WalletChangeLog();
        log.setWalletId(wallet.getId());
        log.setAction(Action.FREEZE);
        log.setStatus(Status.PROCESSING);
        log.setAmount(freezeWallet.getAmount());
        log.setRequestId(freezeWallet.getRequestId());
        walletMapper.insertWalletChangeLog(log);
        if(log.getId()==null) {
            return Status.FAILED;
        }

        try {
            return doFreeze(wallet.getId(), freezeWallet, log.getId());
        } catch (Exception e) {
            walletMapper.updateWalletChangeLog(log.getId(), Status.FAILED);
            return Status.FAILED;
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
    public Status doFreeze(Long walletId, FreezeWallet freezeWallet, Long logId) {
        int result = walletMapper.freeze(walletId, freezeWallet.getAmount());
        Status status = (result==1)? Status.COMPLETED: Status.FAILED;
        if(status==Status.COMPLETED) {
            throw new RuntimeException("asdadsa");
        }
        walletMapper.updateWalletChangeLog(logId, status);
        return status;
    }

}
