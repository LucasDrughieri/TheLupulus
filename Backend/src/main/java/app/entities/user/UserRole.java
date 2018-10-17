package app.entities.user;

public enum UserRole {

    ADMINISTRATOR(0), NORMAL_USER(1);

    private Integer code;

    UserRole(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
