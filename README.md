# SmartCanvas-backend

#### 介绍

SmartCanvas-backend 是 智绘蓝图 的后端项目，主要负责处理用户上传的数据、和AI模型的交互，并将结果返回给前端。
智绘蓝图是一个使用ai进行可视化分析的网站。你可以通过它快速的制作自己的可视化分析图表，并将其分享给其他人。

支持以同步的方式提交，也可以使用异步的方式提交分析信息。异步的方式可以提高用户体验。

#### 技术栈

项目特色：

- 使用了智谱glm-4-flash大模型进行智能数据分析
- 由于大模型的限制，无法进行大数据量的处理，因此采用了redisson操作redis利用令牌桶算法进行分布式限流
- 由于大模型生成的速率远小于前端的请求速率，因此使用了kafka作为消息队列，提高消息处理效率
- 如果不需要使用kafka，该项目已配置了线程池，可以以较低成本提高消息处理效率

开发效率：

- 基于我自己制作的Spring-Model项目二次开发，进一步提高开发效率
- 使用了spring/spring-boot框架进行后端开发，提高开发效率
- 使用了knife4j作为接口文档工具
- 使用了mysql进行数据存储
- 使用了mybatis/mybatis-plus作为ORM框架，提高开发效率

#### 安装教程

1. 克隆本仓库
2. 安装需要的中间件，如redis、mysql、kafka等
3. 配置中间件地址、端口等信息
4. 安装项目依赖，如mybatis等
5. 启动项目

#### 使用说明

1.本项目使用了多环境配置文件，如dev、test、prod等，根据需要切换配置文件即可。
2.为了保证我的数据安全，我没有上传dev配置文件，请自行创建。
3.你的application-dev.yml可能长这样：

```yaml
smartCanvas:
  mysql:
    host: localhost
    port: 3306
    database: smart_canvas
    username: root
    password: 123456
  redis:
    host: localhost
    port: 6379
    database: 2
  server:
    port: 1221
  ai:
    # todo 请自行申请智谱API-KEY并换成你自己的
    api-key: 你的智谱API-KEY
    model-name: glm-4-flash
    request-id-template: "smartCanvas-request-%s"
  kafka:
    servers: localhost:9092
    group-id: smartCanvas
```

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat 分支
3. 提交代码
4. 新建 Pull Request

#### 友情连接

- [前端项目gitee地址](https://gitee.com/colablack/smart-canvas-frontend)
- [前端项目github地址](https://github.com/ColaBlack/SmartCanvas-frontend)
- [我的个人文档](https://colablack.github.io/)
- 知乎搜索半名浮生 / CSDN搜索生半名浮 / B站搜索名浮半生 就能找到我哦