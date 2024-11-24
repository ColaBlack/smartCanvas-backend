package cn.cola.smartcanvas.service.impl;

import cn.cola.smartcanvas.model.enums.ChartStatusEnums;
import cn.cola.smartcanvas.model.po.Chart;
import cn.cola.smartcanvas.model.vo.GenResultVO;
import cn.cola.smartcanvas.service.AiService;
import cn.cola.smartcanvas.service.ChartService;
import cn.cola.smartcanvas.service.KafkaService;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 卡夫卡服务实现类
 *
 * @author ColaBlack
 */
@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    @Resource
    private AiService aiService;

    @Resource
    private ChartService chartService;

    /**
     * 智能分析任务
     *
     * @param record 消息记录
     */
    @Override
    @KafkaListener(topics = {"smartCanvas_genChartByAI"})
    public void genResultTask(ConsumerRecord<String, String> record) {
        if (record == null || record.value() == null) {
            log.error("kafka 中消息记录为空");
            return;
        }
        GenResultVO resultVO;
        Chart chart = JSONUtil.toBean(record.value(), Chart.class);
        String chartData = chart.getChartData();
        String goal = chart.getGoal();
        String chartType = chart.getChartType();
        if (chartType == null) {
            chartType = "任意统计图";
        }
        try {
            resultVO = aiService.genResult(goal, chartType, chartData);
            resultVO.setId(chart.getId());
        } catch (Exception e) {
            log.error("智能分析异常", e);
            chart.setStatus(ChartStatusEnums.FAILED.getValue());
            chart.setExecmsg(ChartStatusEnums.FAILED.getDesc());
            chartService.updateById(chart);
            return;
        }

        chart.setGeneratedChart(resultVO.getOption());
        chart.setAnalyzedResult(resultVO.getResult());

        chart.setStatus(ChartStatusEnums.SUCCESS.getValue());
        chart.setExecmsg(ChartStatusEnums.SUCCESS.getDesc());
        chartService.updateById(chart);
    }
}
