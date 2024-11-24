package cn.cola.smartcanvas.service;

import cn.cola.smartcanvas.model.dto.chart.ChartQueryRequest;
import cn.cola.smartcanvas.model.dto.chart.GenChartByAiRequest;
import cn.cola.smartcanvas.model.po.Chart;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图表服务
 *
 * @author ColaBlack
 */
public interface ChartService extends IService<Chart> {
    /**
     * 获取查询包装类
     *
     * @param chartQueryRequest 图表查询请求
     * @return 查询包装类
     */
    QueryWrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest);

    /**
     * 校验智能分析参数
     *
     * @param file    数据文件
     * @param request 智能分析请求
     */
    void validGenChartParams(MultipartFile file, GenChartByAiRequest request);
}
