package cn.cola.smartcanvas.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 存储可视化图表
 *
 * @author ColaBlack
 * @TableName chart
 */
@TableName(value = "chart")
@Data
public class Chart implements Serializable {
    /**
     * 图表id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 图表数据
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
     * 生成的图表,option对象字符串
     */
    private String generatedChart;

    /**
     * 分析结果
     */
    private String analyzedResult;

    /**
     * 图表创建者id
     */
    private Long createrId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除,1表示已删除
     */
    @TableLogic
    private Integer hasDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}