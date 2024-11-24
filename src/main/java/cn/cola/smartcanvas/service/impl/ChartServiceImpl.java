package cn.cola.smartcanvas.service.impl;

import cn.cola.smartcanvas.common.ErrorCode;
import cn.cola.smartcanvas.common.exception.ThrowUtils;
import cn.cola.smartcanvas.constant.CommonConstant;
import cn.cola.smartcanvas.mapper.ChartMapper;
import cn.cola.smartcanvas.model.dto.chart.ChartQueryRequest;
import cn.cola.smartcanvas.model.dto.chart.GenChartByAiRequest;
import cn.cola.smartcanvas.model.po.Chart;
import cn.cola.smartcanvas.service.ChartService;
import cn.cola.smartcanvas.utils.SqlUtils;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        queryWrapper.orderByDesc("update_time");
        return queryWrapper;
    }

    /**
     * 校验智能分析参数
     *
     * @param file    数据文件
     * @param request 智能分析请求
     */
    @Override
    public void validGenChartParams(MultipartFile file, GenChartByAiRequest request) {
        String name = request.getChartName();
        String goal = request.getGoal();
        ThrowUtils.throwIf(StringUtils.isBlank(goal), ErrorCode.PARAMS_ERROR, "分析目标为空");
        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 50, ErrorCode.PARAMS_ERROR, "图表名称过长");

        ThrowUtils.throwIf(file == null || file.isEmpty(), ErrorCode.PARAMS_ERROR, "分析数据不能为空");
        ThrowUtils.throwIf(file.getSize() > 1024 * 1024, ErrorCode.PARAMS_ERROR, "上传文件不能超过1M");
        ThrowUtils.throwIf(!"xlsx".equals(FileUtil.getSuffix(file.getOriginalFilename())), ErrorCode.PARAMS_ERROR, "只支持xlsx格式");
    }
}




