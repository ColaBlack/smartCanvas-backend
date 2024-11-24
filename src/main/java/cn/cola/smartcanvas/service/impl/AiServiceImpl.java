package cn.cola.smartcanvas.service.impl;

import cn.cola.smartcanvas.model.enums.ChartStatusEnums;
import cn.cola.smartcanvas.model.vo.GenResultVO;
import cn.cola.smartcanvas.service.AiService;
import cn.cola.smartcanvas.utils.AiUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * AI服务实现类
 *
 * @author ColaBlack
 */
@Service
public class AiServiceImpl implements AiService {

    private static final String SYSTEM_PROMPT = "你是一个数据分析师和前端数据可视化专家，接下来我会按照以下格式给你提供数据分析任务：\n" +
            "【数据分析目标】\n" +
            "【图表可视化类型】\n" +
            "【,分隔的csv格式数据】\n" +
            "请按照以下步骤和格式进行数据分析，不要输出任何多余的注释开头和结尾!\n" +
            "第一步，给出尽可能详细的数据分析结果，用$分隔开，不要输出任何多余的开头和结尾!例如:\n" +
            "$如数据呈现趋势、数据分布、数据关系、有什么异常值和启示等$\n" +
            "第二步，进行数据可视化，给出 Echarts V5 的 option 配置对象的json格式代码，合理地将数据进行可视化,用&分隔开，不要生成任何多余的注释开头和结尾!例如:\n" +
            "&{\"title\": {\"text\": \"数据分析标题\",\"subtext\": \"\"},\"tooltip\": {\"trigger\": \"axis\",\"axisPointer\": {\"type\": \"shadow\"}},\"legend\": {\"data\": [\"图例\"]},\"xAxis\": {\"data\": [\"1号\", \"2号\", \"3号\"]},\"yAxis\": {},\"series\": [{\"name\": \"系列名\",\"type\": \"bar\",\"data\": [1, 2, 3]}]}&";

    @Resource
    private AiUtils aiUtils;

    /**
     * ai生成分析结论服务
     *
     * @param goal      数据分析目标
     * @param chartType 图表可视化类型
     * @param data      csv格式数据
     * @return 分析结论
     */
    @Override
    public GenResultVO genResult(String goal, String chartType, String data) {
        String userPrompt = "【" + goal + "】\n" + "【" + chartType + "】\n" + "【" + data + "】";
        String prompt = SYSTEM_PROMPT + "\n" + userPrompt;
        String result = aiUtils.aiCaller(prompt);
        // 提取$之间的内容
        String[] results = result.split("\\$");
        if (results.length != 3) {
            // ai生成有误，重新调用
            return genResult(goal, chartType, data);
        }
        String resultStr = results[1].trim();

        // 提取&之间的内容
        String[] options = result.split("&");
        if (options.length != 2) {
            // ai生成有误，重新调用
            return genResult(goal, chartType, data);
        }
        String optionStr = options[1].trim();
        return new GenResultVO(null, resultStr, optionStr, ChartStatusEnums.SUCCESS.getValue(), ChartStatusEnums.SUCCESS.getDesc());
    }
}
