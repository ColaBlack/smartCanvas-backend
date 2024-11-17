package cn.cola.smartcanvas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 项目启动主类
 *
 * @author ColaBlack
 */
@SpringBootApplication
@MapperScan("cn.cola.smartcanvas.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SmartCanvasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCanvasApplication.class, args);
    }

}
