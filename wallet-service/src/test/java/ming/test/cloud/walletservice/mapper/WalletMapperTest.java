package ming.test.cloud.walletservice.mapper;


import ming.test.cloud.walletservice.model.Action;
import ming.test.cloud.walletservice.model.Status;
import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.model.WalletChangeLog;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration()
@MybatisTest
public class WalletMapperTest {

    @SpringBootApplication(scanBasePackages = {"ming.test.cloud.walletservice.mapper"})
    static class InnerConfig {}

    @Autowired
    private WalletMapper walletMapper;

    @Test
    void insertWalletChangeLog() {
        WalletChangeLog log = new WalletChangeLog();
        log.setWalletId(123L);
        log.setAction(Action.FREEZE);
        log.setStatus(Status.FAILED);
        log.setAmount(BigDecimal.ONE);
        log.setRequestId("my-request-id-0003123");
        long result = walletMapper.insertWalletChangeLog(log);
        assertThat(result).isOne();
        assertThat(log.getId()).isNotZero();
    }

    @Test
    void selectByUserAndCurrency() {
        Wallet wallet = walletMapper.selectByUserAndCurrency("ming01", "USD");
        assertThat(wallet).isNotNull();
    }

}
