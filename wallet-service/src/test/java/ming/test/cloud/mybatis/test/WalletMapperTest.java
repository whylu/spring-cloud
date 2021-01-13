package ming.test.cloud.mybatis.test;

import ming.test.cloud.walletservice.mapper.AbstTestMapper;
import ming.test.cloud.walletservice.mapper.WalletMapper;
import ming.test.cloud.walletservice.model.Action;
import ming.test.cloud.walletservice.model.Status;
import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.model.WalletChangeLog;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * pure mybatis test
 */
class WalletMapperTest extends AbstTestMapper<WalletMapper> {

    public WalletMapperTest() {
        super(WalletMapper.class, "/db/h2/init-embedded.sql");
    }

    @Test
    void insertWalletChangeLog() {
        WalletChangeLog log = new WalletChangeLog();
        log.setWalletId(123L);
        log.setAction(Action.FREEZE);
        log.setStatus(Status.FAILED);
        log.setAmount(BigDecimal.ONE);
        log.setRequestId("my-request-id-0003123");
        long result = getMapper().insertWalletChangeLog(log);
        assertThat(result).isOne();
        assertThat(log.getId()).isNotZero();
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