package ming.test.cloud.walletservice.mapper.typehandler;

import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class CodeEnumTypeHandlerTest {

    @Test
    void setParameter() throws SQLException {
        PreparedStatement preparedStatement = spy(PreparedStatement.class);
        CodeEnumTypeHandler codeEnumTypeHandler = new CodeEnumTypeHandler(MyCodeEnum.class);
        codeEnumTypeHandler.setParameter(preparedStatement, 1, MyCodeEnum.BBB, null);
        verify(preparedStatement).setInt(1, MyCodeEnum.BBB.getCode());
    }

    @Test
    void getResult() throws SQLException {
        CodeEnumTypeHandler codeEnumTypeHandler = new CodeEnumTypeHandler(MyCodeEnum.class);

        ResultSet resultSet = mock(ResultSet.class);
        doReturn(MyCodeEnum.BBB.getCode()).when(resultSet).getInt(10);
        CodeEnum result = codeEnumTypeHandler.getResult(resultSet, 10);
        assertThat(result).isEqualTo(MyCodeEnum.BBB);

        reset(resultSet);
        doReturn(true).when(resultSet).wasNull();
        CodeEnum result1 = codeEnumTypeHandler.getResult(resultSet, 10);
        assertThat(result1).isNull();

    }

    enum MyCodeEnum implements CodeEnum {
        AAA(11), BBB(22);

        private final int code;

        MyCodeEnum(int code) {
            this.code = code;
        }

        @Override
        public int getCode() {
            return code;
        }

    }
}