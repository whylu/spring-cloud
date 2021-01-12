package ming.test.cloud.walletservice.utils;

import ming.test.cloud.walletservice.mapper.typehandler.CodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumUtils {
    private static Logger logger = LoggerFactory.getLogger(EnumUtils.class);
    public static  <E extends CodeEnum> E of(Class<E> type, int code) {
        for (E enumConstant : type.getEnumConstants()){
            if(enumConstant.getCode()==code) {
                return enumConstant;
            }
        }
        logger.warn("There is no mapping code {} in enum {}", code, type.getName());
        return null;
    }
}
