
=================================
这是一个开发平台，不是一个系统
=================================

------------------------
部署环境
------------------------
1、安装Maven，并根据你的安装目录修改build.properties文件中的“M2_REPO”和“M2_HOME”
2、在eclipse的“Windows->Preferences->Java->Build Path->Classpath Variable”添加“M2_REPO”变量
2、执行bin目录下的eclipse.bat文件，将下载项目引入的jar包，并按照maven格式生成项目信息
3、根据“docs\设计”中的pdm文件创建数据库，数据库名称为“lmiky”，如果要自定义数据库名称，一并修改“src\main\resources\config\applicationContext-database.xml”中的值
4、执行分别ant(build.xml)命令：attack、maven
5、配置到web服务器中
6、检查“src\main\resources\config\context.properties”文件中的allowInit值是否为true，如果不是，改为true，执行“你的项目地址/init/load.shtml”，在界面上输入管理员信息，点击提交初始化系统，完成之后如果要关闭初始化功能，将allowInit值设为false
