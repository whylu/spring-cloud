package ming.test.cloud.eureka;

public class HelloObject {
    private String message;
    public HelloObject(String helloMessage, String username) {
        message = helloMessage + ", "+username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
