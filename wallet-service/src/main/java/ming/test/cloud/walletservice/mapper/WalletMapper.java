package ming.test.cloud.walletservice.mapper;

import ming.test.cloud.walletservice.model.Status;
import ming.test.cloud.walletservice.model.Wallet;
import ming.test.cloud.walletservice.model.WalletChangeLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface WalletMapper {
    @Select("SELECT id, username , currency , amount FROM wallet WHERE username = #{username}")
    List<Wallet> selectByUser(String username);

    @Select("SELECT id, username , currency , amount FROM wallet WHERE username = #{username} and currency = #{currency}")
    Wallet selectByUserAndCurrency(String username, String currency);

    int insertWalletChangeLog(@Param("log") WalletChangeLog log);

    int freeze(Long id, BigDecimal amount);

    void updateWalletChangeLog(Long logId, Status status);

    @Select("select * from wallet_change_log")
    List<WalletChangeLog> selectAllWalletChangeLog();
}
