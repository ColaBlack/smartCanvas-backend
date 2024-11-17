package cn.cola.smartcanvas.common.exception;


import cn.cola.smartcanvas.common.ErrorCode;

/**
 * 异常工具类
 *
 * @author ColaBlack
 */
public class ThrowUtils {

    /**
     * 条件成立则抛运行时异常
     *
     * @param condition        条件
     * @param runtimeException 运行时异常
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛指定错误码的业务异常
     *
     * @param condition 条件
     * @param errorCode 错误码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛业务异常（有描述信息）
     *
     * @param condition 条件
     * @param errorCode 错误码
     * @param message   异常信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
