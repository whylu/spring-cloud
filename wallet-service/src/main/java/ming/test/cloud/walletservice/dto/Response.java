package ming.test.cloud.walletservice.dto;

import ming.test.cloud.walletservice.model.ErrorCode;
import org.springframework.http.ResponseEntity;

public class Response {
    private Object data;
    private String message;
    private int errorCode;

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

    public int getErrorCode() {
        return errorCode;
    }
}
