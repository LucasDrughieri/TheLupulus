package app.entities.order;

public enum OrderState {

    FINALIZED(0), PENDING(1), IN_PROGRESS(2), CANCELLED(3);

    private Integer code;

    OrderState(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
