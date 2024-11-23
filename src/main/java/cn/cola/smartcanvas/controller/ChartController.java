package cn.cola.smartcanvas.controller;

import cn.cola.smartcanvas.annotation.AuthCheck;
import cn.cola.smartcanvas.common.BaseResponse;
import cn.cola.smartcanvas.common.DeleteRequest;
import cn.cola.smartcanvas.common.ErrorCode;
import cn.cola.smartcanvas.common.ResultUtils;
import cn.cola.smartcanvas.common.exception.BusinessException;
import cn.cola.smartcanvas.common.exception.ThrowUtils;
import cn.cola.smartcanvas.constant.UserConstant;
import cn.cola.smartcanvas.model.dto.chart.*;
import cn.cola.smartcanvas.model.po.Chart;
import cn.cola.smartcanvas.model.po.User;
import cn.cola.smartcanvas.service.ChartService;
import cn.cola.smartcanvas.service.UserService;
import cn.cola.smartcanvas.utils.ExcelUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图表接口
 *
 * @author ColaBlack
 */
@RestController
@RequestMapping("/chart")
@Slf4j
public class ChartController {

    @Resource
    private ChartService chartService;

    @Resource
    private UserService userService;


    /**
     * 智能分析
     *
     * @param multipartFile       上传的文件
     * @param genChartByAiRequest 智能分析请求
     * @param request             请求
     * @return 智能分析结果
     */
    @PostMapping("/gen")
    public BaseResponse<String> genChartByAi(@RequestPart("file") MultipartFile multipartFile,
                                             GenChartByAiRequest genChartByAiRequest, HttpServletRequest request) {
        String name = genChartByAiRequest.getChartName();
        String goal = genChartByAiRequest.getGoal();
        String chartType = genChartByAiRequest.getChartType();

        ThrowUtils.throwIf(StringUtils.isBlank(goal), ErrorCode.PARAMS_ERROR, "分析目标为空");
        ThrowUtils.throwIf(StringUtils.isNotBlank(name) && name.length() > 50, ErrorCode.PARAMS_ERROR, "图表名称过长");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("你是一个数据分析师，你需要分析以下数据，告诉我你的结果：\n");
        stringBuilder.append("目标：").append(goal).append("\n");
        stringBuilder.append("类型：").append(chartType).append("\n");
        stringBuilder.append("名称：").append(name).append("\n");
        String result = ExcelUtils.excelToCsv(multipartFile);
        stringBuilder.append("数据：").append(result).append("\n");
        return ResultUtils.success(stringBuilder.toString());
//        // 读取到用户上传的 excel 文件，进行一个处理
//        User loginUser = userService.getLoginUser(request);
//        // 文件目录：根据业务、用户来划分
//        String uuid = RandomStringUtils.randomAlphanumeric(8);
//        String filename = uuid + "-" + multipartFile.getOriginalFilename();
//        File file = null;
//        try {
//
//            // 返回可访问地址
//            return ResultUtils.success("");
//        } catch (Exception e) {
////            log.error("file upload error, filepath = " + filepath, e);
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
//        } finally {
//            if (file != null) {
//                // 删除临时文件
//                boolean delete = file.delete();
//                if (!delete) {
////                    log.error("file delete error, filepath = {}", filepath);
//                }
//            }
//        }
    }


    // region 增删改查

    /**
     * 创建
     *
     * @param chartAddRequest 创建图表请求
     * @param request         请求
     * @return 创建的图表 id
     */
    @PostMapping("/add")
    public BaseResponse<Long> addChart(@RequestBody ChartAddRequest chartAddRequest, HttpServletRequest request) {
        if (chartAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartAddRequest, chart);
        User loginUser = userService.getLoginUser(request);
        chart.setCreaterId(loginUser.getId());
        boolean result = chartService.save(chart);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newChartId = chart.getId();
        return ResultUtils.success(newChartId);
    }

    /**
     * 删除
     *
     * @param deleteRequest 删除图表请求
     * @param request       请求
     * @return 删除成功
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteChart(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldChart.getCreaterId().equals(user.getId()) && userService.notAdmin(user)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = chartService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param chartUpdateRequest 更新图表请求
     * @return 更新成功
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateChart(@RequestBody ChartUpdateRequest chartUpdateRequest) {
        if (chartUpdateRequest == null || chartUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartUpdateRequest, chart);
        long id = chartUpdateRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = chartService.updateById(chart);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id 图表id
     * @return 图表信息
     */
    @GetMapping("/get/id")
    public BaseResponse<Chart> getChartById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = chartService.getById(id);
        if (chart == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(chart);
    }

    /**
     * 分页获取图表
     *
     * @param chartQueryRequest 图表查询请求
     * @return 分页的图表列表
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Chart>> listChartByPage(@RequestBody ChartQueryRequest chartQueryRequest) {
        long current = chartQueryRequest.getCurrent();
        long size = chartQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Chart> chartPage = chartService.page(new Page<>(current, size),
                chartService.getQueryWrapper(chartQueryRequest));
        return ResultUtils.success(chartPage);
    }

    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param chartQueryRequest 图表查询请求
     * @param request           请求
     * @return 分页的图表列表
     */
    @PostMapping("/my/list/page")
    public BaseResponse<Page<Chart>> listMyChartByPage(@RequestBody ChartQueryRequest chartQueryRequest,
                                                       HttpServletRequest request) {
        if (chartQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        chartQueryRequest.setCreatorId(loginUser.getId());
        long current = chartQueryRequest.getCurrent();
        long size = chartQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Chart> chartPage = chartService.page(new Page<>(current, size),
                chartService.getQueryWrapper(chartQueryRequest));
        return ResultUtils.success(chartPage);
    }

    /**
     * 编辑（用户）
     *
     * @param chartEditRequest 编辑图表请求
     * @param request          请求
     * @return 编辑成功
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editChart(@RequestBody ChartEditRequest chartEditRequest, HttpServletRequest request) {
        if (chartEditRequest == null || chartEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Chart chart = new Chart();
        BeanUtils.copyProperties(chartEditRequest, chart);
        User loginUser = userService.getLoginUser(request);
        long id = chartEditRequest.getId();
        // 判断是否存在
        Chart oldChart = chartService.getById(id);
        ThrowUtils.throwIf(oldChart == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldChart.getCreaterId().equals(loginUser.getId()) && userService.notAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = chartService.updateById(chart);
        return ResultUtils.success(result);
    }
    // endregion
}
