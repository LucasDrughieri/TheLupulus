package app.model.user;

public enum UserRole {

    ADMINISTRATOR(1), NORMAL_USER(2);

    private Integer code;

    UserRole(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static UserRole getRole(Integer code){
        for (UserRole userRole: UserRole.values()){
            if (userRole.getCode().equals(code)){
                return userRole;
            }
        }
        return null;
    }
}
