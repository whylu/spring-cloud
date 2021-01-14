package ming.test.cloud.walletservice.dto;

import ming.test.cloud.walletservice.model.ErrorCode;
import org.springframework.http.ResponseEntity;

public class Response<D> {
    private D data;
    private String message;
    private int errorCode;

    public static Response error(ErrorCode errorCode) {
        Response errorResponse = new Response();
        errorResponse.errorCode = errorCode.getCode();
        errorResponse.message = errorCode.getMessage();
        return errorResponse;
    }

    public static <D> ResponseEntity<Response<D>> ok(D data) {
        Response response = new Response();
        response.data = data;
        return ResponseEntity.ok().body(response);
    }

    public D getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
