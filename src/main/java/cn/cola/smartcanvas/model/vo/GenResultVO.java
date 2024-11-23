package cn.cola.smartcanvas.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AI生成结果VO
 *
 * @author ColaBlack
 */
@Data
@AllArgsConstructor
public class GenResultVO {

    /**
     * 分析结果
     */
    private String result;

    /**
     * option对象
     */
    private String option;
}
