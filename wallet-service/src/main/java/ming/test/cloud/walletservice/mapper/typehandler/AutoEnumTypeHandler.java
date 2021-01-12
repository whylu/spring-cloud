package ming.test.cloud.walletservice.mapper.typehandler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoEnumTypeHandler<E extends Enum<E>> implements TypeHandler<E> {

    private TypeHandler<E> typeHandler;

    public AutoEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        if(CodeEnum.class.isAssignableFrom(type)) {
            typeHandler = new CodeEnumTypeHandler(type);
        } else {
            typeHandler = new EnumTypeHandler(type);
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        typeHandler.setParameter(preparedStatement, i, e, jdbcType);
    }

    @Override
    public E getResult(ResultSet resultSet, String s) throws SQLException {
        return typeHandler.getResult(resultSet, s);
    }

    @Override
    public E getResult(ResultSet resultSet, int i) throws SQLException {
        return typeHandler.getResult(resultSet, i);
    }

    @Override
    public E getResult(CallableStatement callableStatement, int i) throws SQLException {
        return typeHandler.getResult(callableStatement, i);
    }
}
