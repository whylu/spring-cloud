package ming.test.cloud.walletservice;

import ming.test.cloud.walletservice.mapper.AbstTestMapper;
import ming.test.cloud.walletservice.model.Status;
import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.mapper.WalletMapper;
import ming.test.cloud.walletservice.model.Action;
import ming.test.cloud.walletservice.model.WalletChangeLog;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WalletMapperTest extends AbstTestMapper<WalletMapper> {

    public WalletMapperTest() {
        super(WalletMapper.class, "/db-init/init.sql");
    }

    @Test
    void insertWalletChangeLog() {
        WalletChangeLog log = new WalletChangeLog();
        log.setWalletId(123L);
        log.setAction(Action.FREEZE);
        log.setStatus(Status.PROCESSING);
        log.setAmount(BigDecimal.ONE);
        log.setRequestId("my-request-id-0003123");
        long result = getMapper().insertWalletChangeLog(log);
        System.out.println(result);
        System.out.println(log.getId());
    }

    @Test
    void selectByUser() {
    }

    @Test
    void selectByUserAndCurrency() {
        Wallet wallet = getMapper().selectByUserAndCurrency("ming01", "USD");
        assertThat(wallet).isNotNull();
    }

    @Test
    void selectAllWalletChangeLog() {
        List<WalletChangeLog> walletChangeLogs = getMapper().selectAllWalletChangeLog();
        System.out.println(walletChangeLogs);
        assertThat(walletChangeLogs).isNotEmpty();
        WalletChangeLog log = walletChangeLogs.get(0);
        assertThat(log.getAction()).isEqualTo(Action.FREEZE);
        assertThat(log.getStatus()).isEqualTo(Status.COMPLETED);
    }
}