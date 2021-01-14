package ming.test.cloud.eureka.dto;


import java.math.BigDecimal;

public class FreezeWallet {
    private String username;
    private String currency;
    private BigDecimal amount;
    private String requestId;
    private Action action = Action.FREEZE;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Action getAction() {
        return action;
    }
}
