package cn.cola.smartcanvas.service.impl;

import cn.cola.smartcanvas.constant.CommonConstant;
import cn.cola.smartcanvas.mapper.ChartMapper;
import cn.cola.smartcanvas.model.dto.chart.ChartQueryRequest;
import cn.cola.smartcanvas.model.po.Chart;
import cn.cola.smartcanvas.service.ChartService;
import cn.cola.smartcanvas.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 图表服务实现类
 *
 * @author ColaBlack
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
        implements ChartService {

    /**
     * 获取查询包装类
     *
     * @param chartQueryRequest 图表查询请求
     * @return 查询包装类
     */
    @Override
    public QueryWrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest) {
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        if (chartQueryRequest == null) {
            return queryWrapper;
        }
        Long id = chartQueryRequest.getId();
        String name = chartQueryRequest.getChartName();
        String goal = chartQueryRequest.getGoal();
        String chartType = chartQueryRequest.getChartType();
        Long userId = chartQueryRequest.getCreatorId();
        String sortField = chartQueryRequest.getSortField();
        String sortOrder = chartQueryRequest.getSortOrder();

        queryWrapper.eq(id != null && id > 0, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(name), "chart_name", name);
        queryWrapper.eq(StringUtils.isNotBlank(goal), "goal", goal);
        queryWrapper.eq(StringUtils.isNotBlank(chartType), "chart_type", chartType);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "creater_id", userId);
        queryWrapper.eq("has_deleted", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }
}




