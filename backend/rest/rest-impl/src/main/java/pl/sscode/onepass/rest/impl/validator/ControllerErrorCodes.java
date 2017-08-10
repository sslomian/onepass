package pl.sscode.onepass.rest.impl.validator;

/**
 * Created by sscode on 2017-08-06.
 */
public enum ControllerErrorCodes {
    USER_NOT_FOUND("100", "user.login.userNotFound"),
    WRONG_PASSWORD("101", "user.login.wrongPassword")



    ;

    private String code;
    private String msg;

    ControllerErrorCodes(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
