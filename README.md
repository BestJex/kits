<div align=center>
![](https://www.strongyang.com/static/kits3.png)
</div>

# kits
### 介绍
   一款安全可靠的云存储管理平台，使用SpringSecurity Oauth2、SpringBoot2.0搭建
   - [在线文档](https://www.strongyang.com/kits-v1/doc/)
   - [在线演示](https://www.strongyang.com/kits-v1/)
   - [前端地址](https://github.com/stryang/kits-ui)
### 界面截图
![](https://www.strongyang.com/static/kits_login.png)
![](https://www.strongyang.com/static/kits_index.png)
### 依赖环境
|  软件/插件  | 版本  |
|  ----  | ----  |
| JDK    | 1.8以上 |
| maven  | 3.0以上 |
| lombok |  |
### 编译
   进入项目pom.xml文件所在目录，使用maven编译`mvn install`，许提前搭建maven环境
### 配置
```yml
server:
  port: 9999

spring:
  application:
    name: kits
  fileLocation: D:/ftpdata
  userName: admin
  password: 123456
```
- `server.port`：服务运行端口
- `spring.fileLocation`：服务器存储根目录配置
- `spring.userName`：用户名配置
- `spring.password`：密码配置
### 启动
运行springboot启动类
```
...
2019-11-20 14:45:07.336  INFO 81636 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page template: index
2019-11-20 14:45:07.503  INFO 81636 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2019-11-20 14:45:07.540  INFO 81636 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9999 (http) with context path ''
2019-11-20 14:45:07.544  INFO 81636 --- [           main] c.p.h.kits.PtytHaiguanKitsApplication    : Started PtytHaiguanKitsApplication in 3.843 seconds (JVM running for 4.587)
kits is started!
```