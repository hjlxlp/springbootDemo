@Component, @Service, @Controller, @Repository是spring注解，注解后可以被spring框架所扫描并注入到spring容器来进行管理 
@Component是通用注解，其他三个注解是这个注解的拓展，并且具有了特定的功能 
@Repository注解在持久层中，具有将数据库操作抛出的原生异常翻译转化为spring的持久层异常的功能。 
@Controller层是spring-mvc的注解，具有将请求进行转发，重定向的功能。 
@Service层是业务逻辑层注解，这个注解只是标注该类处于业务逻辑层。 
用这些注解对应用进行分层之后，就能将请求处理，义务逻辑处理，数据库操作处理分离出来，为代码解耦，也方便了以后项目的维护和开发。

@Controller：修饰class，用来创建处理http请求的对象
@RestController：Spring4之后加入的注解，原来在@Controller中返回json需要@ResponseBody来配合，如果直接用@RestController替代@Controller就不需要再配置@ResponseBody，默认返回json格式。
@RequestMapping：配置url映射

mysql8安装
Authentication plugin 'caching_sha2_password' cannot be loaded
8改变了身份验证插件，解决方式
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root' PASSWORD EXPIRE NEVER; #修改加密规则
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root'; #更新一下用户的密码
ALTER USER 'hjl'@'%' IDENTIFIED WITH mysql_native_password BY '211314';

