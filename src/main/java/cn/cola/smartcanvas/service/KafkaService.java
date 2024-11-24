package cn.cola.smartcanvas.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 卡夫卡服务
 *
 * @author ColaBlack
 */
public interface KafkaService {
    /**
     * 智能分析任务
     *
     * @param record 消息记录
     */
    void genResultTask(ConsumerRecord<String, String> record);
}
