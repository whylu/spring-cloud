package ming.test.cloud.walletservice.mapper.typehandler;

import ming.test.cloud.walletservice.utils.EnumUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeEnumTypeHandler<E extends Enum<E> & CodeEnum> implements TypeHandler<CodeEnum> {

    private final Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, CodeEnum codeEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, codeEnum.getCode());
    }

    @Override
    public CodeEnum getResult(ResultSet resultSet, String columnName) throws SQLException {
        if(resultSet.wasNull()) {
            return null;
        }
        int code = resultSet.getInt(columnName);
        E e = EnumUtils.of(type, code);
        return e;
    }

    @Override
    public CodeEnum getResult(ResultSet resultSet, int i) throws SQLException {
        if(resultSet.wasNull()) {
            return null;
        }
        int code = resultSet.getInt(i);
        E e = EnumUtils.of(type, code);
        return e;
    }

    @Override
    public CodeEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        if(callableStatement.wasNull()) {
            return null;
        }
        int code = callableStatement.getInt(i);
        E e = EnumUtils.of(type, code);
        return e;
    }
}
