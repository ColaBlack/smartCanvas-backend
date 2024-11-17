package cn.cola.smartcanvas.constant;

/**
 * 用户常量
 *
 * @author ColaBlack
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "USER_LOGIN_STATE";

    /**
     * 用户密码加密盐
     */
    String SALT = "ColaBlack";

    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "admin";

    /**
     * 被封号
     */
    String BAN_ROLE = "ban";

    // endregion

    /**
     * 密码正则表达式
     * 密码由字母、数字组成，长度在6-20位之间
     */
    String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

    /**
     * 账号正则表达式
     * 账号由字母、数字组成，长度在4-20位之间
     */
    String ACCOUNT_REGEX = "^[a-zA-Z0-9]{4,20}$";

}
