package cn.cola.smartcanvas.utils;

import cn.cola.smartcanvas.common.ErrorCode;
import cn.cola.smartcanvas.common.exception.BusinessException;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * redisson工具类
 *
 * @author ColaBlack
 */
@Service
public class RedissonUtils {

    @Resource
    private RedissonClient redissonClient;

    /**
     * redisson令牌桶算法限流
     *
     * @param key  限流key
     * @param rate 每秒限制次数
     */
    public void limitRate(String key, long rate) {
        // 创建限流器
        RRateLimiter limiter = redissonClient.getRateLimiter(key);
        // 设置限流器的速率
        limiter.trySetRate(RateType.OVERALL, rate, Duration.ofSeconds(1));
        // 尝试获取1个令牌，如果获取成功，则表示可以执行，否则表示限流
        if (!limiter.tryAcquire(1)) {
            throw new BusinessException(ErrorCode.TOO_MANY_REQUESTS_ERROR, "请求过于频繁，请稍后再试");
        }
    }
}
