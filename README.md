## 一个基于 JavaEE 开发的极简论坛

[![License](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)


### 技术栈

`Java EE`  `Mysql`  `Spring` `SpringMVC`  `Mybatis`  `JavaScript` `PJAX`
`Maven` 


### 已有功能


- 单页面
- 登录
- 注册
- 回复
- 修改头像
- 发布文章
- 回复文章
- @圈人
- 滋磁 markdown
- 滋磁图片拖拽、粘贴上传
- 滋磁网易云音乐插入
...


### 如何获得

1. `$ git clone https://github.com/x-pengg/BBS.git`

2. 导入到 IntelliJ IDEA/~~Eclipse~~

3. 开始食用


### 如何部署

1. 进入项目根目录，输入 `mvn clean package -P remote`

2. 获得 war 包，部署到 `Tomcat`/`Jetty` 符合 `Servlet` 规范的容器中运行/启动

3. 打开浏览器，访问 `http://ip:8080`

