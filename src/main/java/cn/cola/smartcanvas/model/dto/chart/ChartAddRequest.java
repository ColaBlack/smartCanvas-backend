package cn.cola.smartcanvas.model.dto.chart;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建图表请求
 *
 * @author ColaBlack
 */
@Data
public class ChartAddRequest implements Serializable {

    /**
     * 图表名称
     */
    private String chartName;

    /**
     * 分析目标
     */
    private String goal;

    /**
     * 图表数据
     */
    private String chartData;

    /**
     * 图表类型
     */
    private String chartType;

    private static final long serialVersionUID = 1L;
}