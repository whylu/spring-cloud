package ming.test.cloud.eureka.dto;

public class Response<D> {
    private D data;
    private String message;
    private int errorCode;

    public D getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setData(D data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
