package ming.test.cloud.walletservice.dto;

import ming.test.cloud.walletservice.model.Status;

public class WalletChangeResult {
    private Long walletId;
    private Long logId;
    private String requestId;
    private Status status;
    private String serverOnlyField;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getServerOnlyField() {
        return serverOnlyField;
    }

    public void setServerOnlyField(String serverOnlyField) {
        this.serverOnlyField = serverOnlyField;
    }
}
