## Erlang支持
- Erlang下载

  [Erlang官网](http://www.erlang.org/downloads)

- 配置Erlang环境变量：

```xml
1. 添加系统环境变量ERLANG_HOME，值为安装目录
2. 修改系统环境变量Path,在PATH变量中添加“%ERLANG_HOME%\bin”
```

- 查看版本
```shell script
C:\Users\bo>erl
Eshell V10.5  (abort with ^G)
1>

```

## RabbitMQ支持
  RabbitMQ 是基于Erlang开发的，安装RabbitMQ之前确保正确安装erl.
  
 - RabbitMQ下载
 
   [RabbitMQ官网](https://www.rabbitmq.com/)
   
   [GitHub下载](https://github.com/rabbitmq/rabbitmq-server/releases/tag/v3.8.3-beta.2)


- 启动：

```xml
     cd D:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.3-beta.2\sbin
 
     启动web管理界面
     1.rabbitmq-plugins.bat enable rabbitmq_management

     启动服务
     2.rabbitmq-service.bat stop
     3.rabbitmq-service.bat start
     
     打开浏览器登录：http://127.0.0.1:15672 
```

默认用户guest/guest
   


