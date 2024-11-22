package cn.cola.smartcanvas.model.dto.chart;


import cn.cola.smartcanvas.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询图表请求
 *
 * @author ColaBlack
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChartQueryRequest extends PageRequest implements Serializable {

    /**
     * 图表名称
     */
    private String chartName;

    /**
     * 图表 id
     */
    private Long id;

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 用户 id
     */
    private Long creatorId;

    private static final long serialVersionUID = 1L;
}