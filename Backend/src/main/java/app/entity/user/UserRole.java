package app.entity.user;

public enum UserRole {

    ADMINISTRATOR(1), NORMAL_USER(2);

    private Integer code;

    UserRole(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
