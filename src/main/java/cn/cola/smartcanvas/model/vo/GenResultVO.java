package cn.cola.smartcanvas.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AI生成结果VO
 *
 * @author ColaBlack
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenResultVO {

    /**
     * 图表id
     */
    private Long id;

    /**
     * 分析结果
     */
    private String result;

    /**
     * option对象
     */
    private String option;

    /**
     * 分析状态
     */
    private String status;

    /**
     * 运行信息
     */
    private String execmsg;
}
