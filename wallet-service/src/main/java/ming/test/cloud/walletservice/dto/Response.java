package ming.test.cloud.walletservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ming.test.cloud.walletservice.model.ErrorCode;
import org.springframework.http.ResponseEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Object data;
    private String message;
    private Integer errorCode;

    public static Response error(ErrorCode errorCode) {
        Response errorResponse = new Response();
        errorResponse.errorCode = errorCode.getCode();
        errorResponse.message = errorCode.getMessage();
        return errorResponse;
    }

    public static ResponseEntity<Response> ok(Object data) {
        Response response = new Response();
        response.data = data;
        return ResponseEntity.ok().body(response);
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
