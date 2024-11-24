package cn.cola.smartcanvas.utils;

import cn.cola.smartcanvas.common.ErrorCode;
import cn.cola.smartcanvas.common.exception.BusinessException;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Excel工具类
 *
 * @author ColaBlack
 */
@Slf4j
public class ExcelUtils {
    /**
     * excel转csv字符串
     *
     * @param file 文件
     * @return csv字符串
     */
    public static String excelToCsv(MultipartFile file) {
        // 读取数据
        List<Map<Integer, String>> list;

        try {
            list = EasyExcel.read(file.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "读取Excel文件失败");
        }

        // 如果数据为空
        if (CollUtil.isEmpty(list)) {
            return "";
        }
        // 转换为 csv格式文本
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringUtils.join(list
                .get(0)
                .values()
                .stream()
                // 过滤掉空值
                .filter(ObjectUtils::isNotEmpty)
                .collect(Collectors.toList()), ",")).append("\n");

        // 读取数据
        for (int i = 1; i < list.size(); i++) {
            LinkedHashMap<Integer, String> dataMap = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> dataList = dataMap
                    .values()
                    .stream()
                    .filter(ObjectUtils::isNotEmpty)
                    .collect(Collectors.toList());
            stringBuilder.append(StringUtils.join(dataList, ",")).append("\n");
        }
        return stringBuilder.toString();
    }
}
