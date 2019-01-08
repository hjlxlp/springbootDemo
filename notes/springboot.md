1.@SpringBootApplication,由@Configuration，@ComponentScan，@EnableAutoConfiguration组成
2.ComponentScan，扫描指定的包下的类，并加载符合条件的组件（比如@Component、@Service、@Repository等）或者bean定义，
    最终将这些bean定义加载到IoC容器中，如果不指定basePackage则默认Spring框架实现会从声明@ComponentScan所在类的package进行扫描。
    Spring Boot中的@ComponentScan没有指定basePackage，所以会从<groupId>.<artifactId>下开始扫描。
    所以，一般Application类要放在groupId.artifactId包下。
3.@Configuration，Java Config(Java 配置)，是一个Ioc容器类，相当于传统项目中见到的一个spring 的xml配置文件。
    一个空Java Config类 相当于一个空的xml配置文件，都是用来作为配置bean的容器
4.@EnableAutoConfiguration，Spring 中有很多以Enable开头的注解，其作用就是借助@Import来收集并注册特定场景相关的bean，并加载到IoC容器。
    @EnableAutoConfiguration就是借助@Import来收集所有符合自动配置条件的bean定义，并加载到IoC容器。

                         