package cn.cola.smartcanvas.model.vo;

import cn.cola.smartcanvas.model.po.Chart;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 图表视图对象
 *
 * @author ColaBlack
 */
@Data
public class ChartVO {

    /**
     * 图表id
     */
    private Long id;

    /**
     * 图表名称
     */
    private String chartName;

    /**
     * 分析目的
     */
    private String goal;

    /**
     * 图表数据,option对象字符串
     */
    private String chartData;

    /**
     * 图表类型
     */
    private String chartType;

    /**
     * 分析状态
     */
    private String status;

    /**
     * 运行信息
     */
    private String execmsg;

    /**
     * 生成的图表
     */
    private String generatedChart;

    /**
     * 分析结果
     */
    private String analyzedResult;

    /**
     * 创建时间
     */
    private Date createTime;

    public ChartVO(Chart chart) {
        BeanUtils.copyProperties(chart, this);
    }
}
