package cn.cola.smartcanvas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.cola.smartcanvas.model.po.Chart;
import cn.cola.smartcanvas.service.ChartService;
import cn.cola.smartcanvas.mapper.ChartMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【chart(存储可视化图表)】的数据库操作Service实现
* @createDate 2024-11-17 14:47:52
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

}




