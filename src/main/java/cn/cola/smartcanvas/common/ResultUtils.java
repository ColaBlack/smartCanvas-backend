package cn.cola.smartcanvas.common;


/**
 * 返回工具类
 *
 * @author ColaBlack
 */
public class ResultUtils {

    /**
     * 请求成功返回工具
     *
     * @param data 返回给前端的数据
     * @param <T>  返回的数据类型
     * @return 成功返回的结果
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ErrorCode.SUCCESS.getCode(), data, "ok");
    }

    /**
     * 请求失败返回工具
     *
     * @param errorCode 错误码
     * @return 失败返回的结果
     */
    public static BaseResponse<?> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 请求失败返回工具
     *
     * @param code    错误码
     * @param message 错误信息
     * @return 失败返回的结果
     */
    public static BaseResponse<?> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    /**
     * 请求失败返回工具
     *
     * @param errorCode 错误码
     * @param message   错误信息
     * @return 失败返回的结果
     */
    public static BaseResponse<?> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}
