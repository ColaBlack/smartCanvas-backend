package cn.cola.smartcanvas.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson客户端配置
 *
 * @author ColaBlack
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    /**
     * redis数据库
     */
    private Integer database;

    /**
     * redis地址
     */
    private String host;

    /**
     * redis端口
     */
    private Integer port;

    /**
     * 超时时间
     */
    private Integer timeout;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setTimeout(timeout);
        return Redisson.create(config);

    }
}
