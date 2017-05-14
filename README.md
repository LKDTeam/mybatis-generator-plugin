# mybatis-generator
使用通用mapper插件自动生成mapper和xml，model。配置文件generatorConfig.xml。

项目构建工具：gradle。

mybatis 通用mapper插件地址：https://github.com/abel533/Mapper

### 目标：

1.编写mybatis-generator的gradle插件。

2.目前生成的文件是在generatorConfig.xml配置的文件系统的绝对路径。改为生成在当前项目的generator目录下。