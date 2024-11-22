package cn.cola.smartcanvas.model.dto.chart;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新图表请求
 *
 * @author ColaBlack
 */
@Data
public class ChartUpdateRequest implements Serializable {

    /**
     * 图表名称
     */
    private String chartName;

    /**
     * 图表id
     */
    private Long id;

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

    /**
     * 生成的图表数据
     */
    private String generatedChart;

    /**
     * 生成的分析结论
     */
    private String analyzedResult;

    private static final long serialVersionUID = 1L;
}