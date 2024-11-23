package cn.cola.smartcanvas.config;

import com.zhipu.oapi.ClientV4;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AI 配置信息读取
 *
 * @author ColaBlack
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ai")
public class AiConfig {

    /**
     * AI 模型名称
     */
    private String modelName;

    /**
     * AI API 密钥
     * 请自行申请 AI 平台 API 密钥并填入
     */
    private String apiKey;

    /**
     * 业务ID模版
     */
    private String requestIdTemplate;

    @Bean
    public ClientV4 clientV4() {
        return new ClientV4.Builder(apiKey).build();
    }
}






