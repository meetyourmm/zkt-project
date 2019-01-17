## zkt-project
企业应用系统开发脚手架 基于微服务spring cloud项目拆分出来的单soa组件 适合作为公司做单应用项目的架构  也可与微服务无缝对接。 
#  rest接口
#  vue前端（https://github.com/meetyourmm/zkt-admin-ui）
  <p align="center">
  <img width="900" src="http://plgc58szh.bkt.clouddn.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190117091920.png">
  </p>

# 结构说明
```
- zkt-project
  - zkt-admin 后台管理基础模块
    - 用户管理
    - 菜单管理
    - 部门管理
    - 角色管理
    - 数据字典
    - 接口日志
  - zkt-common
    - zkt-common-core  core核心组件
    - zkt-common-web 所有web项目的基础组件 soaclient的封装
  - zkt-generator 代码生成器项目

