package cn.cola.smartcanvas.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 图表状态枚举
 *
 * @author ColaBlack
 */
@Getter
@AllArgsConstructor
public enum ChartStatusEnums {
    /**
     * 成功
     */
    SUCCESS("success", "成功"),

    /**
     * 失败
     */
    FAILED("failed", "失败"),

    /**
     * 处理中
     */
    PROCESSING("processing", "处理中");

    private final String value;

    private final String desc;
}
