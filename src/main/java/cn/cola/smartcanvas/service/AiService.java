package cn.cola.smartcanvas.service;

import cn.cola.smartcanvas.model.vo.GenResultVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;

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
     */
    GenResultVO genResult(String goal, String chartType, String data);

}
