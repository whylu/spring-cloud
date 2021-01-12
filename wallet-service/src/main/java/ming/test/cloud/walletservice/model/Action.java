package ming.test.cloud.walletservice.model;

import ming.test.cloud.walletservice.mapper.typehandler.CodeEnum;

public enum Action implements CodeEnum {
    FREEZE(1);

    private final int code;

    Action(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
