package cn.cola.smartcanvas.service;

import cn.cola.smartcanvas.common.exception.BusinessException;
import cn.cola.smartcanvas.model.vo.GenResultVO;

/**
 * AI服务接口
 *
 * @author ColaBlack
 */
public interface AiService {

    /**
     * ai生成分析结论服务
     *
     * @param goal      数据分析目标
     * @param chartType 图表可视化类型
     * @param data      csv格式数据
     * @return 分析结论
     * @throws BusinessException 业务异常
     */
    GenResultVO genResult(String goal, String chartType, String data) throws BusinessException;
}
