# SpringCloud_Demo
SpringCloud系统知识持续补充中
- ##### 已接入技术：
Springboot、Eureka、Zuul、Ribbon、Nacos、RabbitMQ、Redis、FeignClient、Hystrix。

- ##### 计划计入技术：
Hadoop、MyBatis、Oracle、LDAP、VUE


- ##### 端口占用表格
<!--
端口分布说明
-->
|项目module名称       |        服务名             | 端口      |        相关服务         |        备注         |       
|--------------------|--------------------------|-----------|--------------------|--------------------|
|  hello             |   hello                  |  8888     |service-eureka-server |功能集成模块  |
|  d-eureka-server   |   service-eureka-server  |  8001     |service-eureka-server |服务注册于发现，云端负载均衡，一个基于 REST 的服务，用于定位服务，以实现云端的负载均衡和中间层服务器的故障转移  |
|  d-eureka-clinet   |   service-eureka-client  |  8002     |service-eureka-server |服务注册 -- 测试  |
|  d-zuul            |   service-zuul           |  8003     |service-eureka-server、hello |API 动态路由，监控，弹性，安全等的边缘服务|
|  d-ribbon-client-1 |   service-ribbon-client  |  8004     |service-eureka-server、service-ribbon-client |负载均衡处理|
|  d-ribbon-client-2 |   service-ribbon-client  |  8005     |service-eureka-server、service-ribbon-client |负载均衡处理|
|  d-ribbon-client-3 |   service-ribbon-client  |  8006     |service-eureka-server、service-ribbon-client |负载均衡处理|
|  d-ribbon-client-4 |   service-ribbon-client  |  8007     |service-eureka-server、service-ribbon-client |负载均衡处理|
|  d-nacos-provider  |   service-nacos-provider |  8008     |nacos               |服务注册 --- 提供者|
|                    |   nacos                  |  8848     |nacos               | [nacos demo](https://github.com/alibaba/nacos/releases)|
|  d-nacos-consumer  |   service-nacos-consumer |  8009     |nacos、service-nacos-provider       |服务注册 --- 消费者|
|                    |   rabbitmq               |  5672     |                                    |消息中间件|
|                    |   redis                  |  6379     |                                    |消息中间件|
|  d-mq        |   service-mq       |  8010     |service-mq、rabbitmq、redis          |消息中间件 依赖 rabbitmq、redis|
