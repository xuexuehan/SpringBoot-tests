## About

1、尽快启动和运行，使用“习惯优于配置”（项目中存在大量的配置，而 Spring Boot 内置一个习惯性的配置，让你无须手动进行配置）的理念让项目快速运行起来；

2、Spring Boot 优点 ：

- 快速构建独立运行的Spring项目；
- 无须依赖外部Servlet容器，应用无需打成WAR包；项目可以打成jar包独自运行；
- 提供一系列 starter pom 来简化 Maven 的依赖加载；
- 大量的自动配置，对主流开发框架的无配置集成；
- 无须配置XML，开箱即用，简化开发，同时也可以修改默认值来满足特定的需求；
- Spring Boot 并不是对 Spring 功能上的增强，而是提供了一种快速使用 Spring 的方式； 
- 极大提高了开发、部署效率 ；

## 入门

1、两种启动方式：

- 直接main；
- 打成jar包，在dos命令下java -jar 项目名称；

2、Spring Boot底层依赖：

```java
  /*
  spring-boot-starter-parent：是当前项目的父依赖
  spring-boot-starter-parent继承spring-boot-dependencies
  spring-boot-dependencies里面定义了很多组件版本号，我们引用对应依赖时，不需要写<version>
  */
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.6.RELEASE</version>
 </parent>
 /*
    spring-boot-starter-web：构建web项目，比如：Tomcat、SpringMVC
    spring-boot-starter：它是SpringBoot的场景启动器，针对不同场景定义了很多不同的场景启动器
    你的项目需要哪些场景启动器，则直接依赖对应的启动器就可以了
  */
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
 //将当前项目打成一个jar包运行
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>  
```

3、Spring Boot底层源码：

```java
/**
 * @SpringBootApplication  用于标识为引导类,说明当前是一个SpringBoot项目
 *      @SpringBootConfiguration
 *          @Configuration 它属于spring中的一个注解，定义配置类，等价于配置文件
 *              @Component 添加到spring容器中，表示是一个组件
 *      @EnableAutoConfiguration
 *          @AutoConfigurationPackage 将引导类所在包及其子包下面所有的组件添加到spring容器中
 *          @Import({AutoConfigurationImportSelector.class})
 *               1、将所有组件以全类名的方式返回，并且添加到spring容器中
 *               2、会给容器中导入非常多的自动配置类（*****AutoiConfiguration），就是导入并配置好很多当                  前项目中所需要的组件
 *               省去我们手动编写配置然后注入到组件中
 *      @ComponentScan 被该注解标识的类，会被spring容器进行管理
 **/
@SpringBootApplication//组合注解
```

## 核心配置

1、spring boot全局配置：

- application.properts
- application.yml

2、YAML：

- YAML不是一个标记语言；
- 以数据为中心，配置数据的时候具有面向对象的特征；比json、xml更适合做配置文件；

3、YAML语法格式：

- YAML基本语法：
  - key: value 表示一对键值对（**冒号后面必须有空格**）；    
  - 使用**空格缩进表示层级关系**；
  - 左侧缩进的空格数目不重要，只要同一层级的元素左侧对齐即可 ；   
  - key 与 value**大小写敏感**；        

- YMAL常用写法：

  - 字面量： 数值，字符串，布尔，日期；

    **字符串 默认不用加上引号**：    

    | “” 使用 双引号会转义特殊字符，特殊字符最终会转成本来想表示含义输出 | name: "mengxuegu \n jiaoyu" 输出： mengxuegu 换行 jiaoyu |
    | :----------------------------------------------------------: | :------------------------------------------------------: |
    | '' 使用 单引号不会转义特殊字符，特殊字符当作一个普通的字符串输出 |  name: 'mengxuegu \n jiaoyu 输出： mengxuegu \n jiaoyu   |

  - 对象 & Map ：

    - key: value value存储对象，每个值换一行写，注意值要左对齐；

      ```java
      emp:
      	lastName: xiaomeng
      	age: 22
      	salary: 10000
      ```

    - 行内写法： 

      ```java
      emp: {lastName: xiaomeng, age: 22, salary: 10000}   
      ```

  - 数组（List、Set）：

    - 用 - 值表示数组中的一个元素：

      ```java
      fortes:
      	- java
      	- python
      	- hadoop  
      ```

    - 行内写法：

      ```java
      fortes: [java, python, hadoop]  
      ```


  ​4、YAML全局配置文件注入值： 

  ```java
  /*
  * ConfigurationProperties: 告诉spring boot将配置文件中的对应属性值，映射到这个组件类中，进行一一绑定
  * prefix = "emp": 配置文件中的前缀名，配置了哪个前缀就会与下面的所有属性进行一一映射
  * @Component: 将当前组件作为springboot中的一个组件来使用，这样才会纳入容器中管理
  * */
  ```

  5、@Value注解获取注入值：

```java
/**
     * 类似于spring框架中使用配置文件中的数据注入方式
     * <bean class="Emp">
     *     <property name="lastName" value="字面量/#{SpEL} spring表达式/${key}从配置文件获取">    </property>
     * </bean>
     * */
 @Value("${emp.last-name}")
```

6、比较@Value和@ConfigurationProperties获取值：

|                      | @ConfigurationProperties |    @Value    |                      示例                      |
| :------------------: | :----------------------: | :----------: | :--------------------------------------------: |
|       实现功能       | 批量注入配置文件的属性值 | 一个一个指定 |                                                |
| 松散绑定（松散语法） |           支持           |    不支持    | last_name == lastName<br>last-name == lastName |
|         SpEL         |          不支持          |     支持     |                    #{10*2}                     |
|     复杂类型封装     |           支持           |    不支持    |                   ${emp.map}                   |
|    JSR303数据校验    |           支持           |    不支持    |                                                |

使用场景：

- 如果只是在某个业务逻辑中需要获取配置文件中的某个属性值，就使用 @Value；
- 如果专门使用javaBean和配置文件进行映射，就使用 @ConfigurationProperties；        

7、加载局部配置文件：

```java
@PropertySource(value = {"classpath:emp.properties"})
```

8、加载XML配置文件：

```java
//用于导入spring的配置文件，并将它加载到容器中
@ImportResource(locations = {"classpath:spring01.xml"})
```

9、自定义配置类：

```java
/**
*@Configuration: 它属于spring中的一个注解,用于标识当前类是一个配置类，来表示对应spring配置文件
**/
@Configuration
public class EmpConfig {
    /**
     * @Bean 标识的方法用于向容器中注入组件
     * 1、返回值就是注入容器中组件对象
     * 2、方法名就是这个组件的 id 值
     * */
    @Bean
    public EmpService empService2() {
        System.out.println("EmpService 组件注入成功");
        return new EmpService();
    }
```

10、多环境项目配置支持：

```java
//properties
#激活profile文件
spring.profiles.active=prod
//yml
server:
  port: 8081 #默认配置的端口号
spring:
  profiles:
    active: dev #激活哪个profile
---
server:
  port: 8082
spring:
  profiles: dev #指定属于哪个环境，dev环境使用
---
server:
  port: 8088
spring:
  profiles: prod #指定属于哪个环境，prod环境使用
```

程序参数、命令行（jar包运行时）：--spring.profiles.active=prod；

虚拟机指定参数：-Dspring.profiles.active=prod；

11、配置文件加载位置：

|    配置文件位置     |                说明                |
| :-----------------: | :--------------------------------: |
|   file: ./config/   | 当前项目的config目录下（最高级别） |
|      file: ./       |   当前项目的根目录下（第三级别）   |
| classpath: /config/ |  类路径的config目录下（第二级别）  |
|    classpath: /     |    类路径的根目录下（最低级别）    |

按照优先级从低到高的顺序，将所有位置的配置文件全部加载，**高优先级的配置内容会覆盖低优先级的配置内容**；

## 日志配置

1、日志框架：

|                          日志抽象层                          |                           日志实现                           |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| jboss-logging（不适合企业项目开发使用）<br>JCL（Jakarta Commons Logging）---2014年后不再维护<br>**SLF4j**（Simple Logging Facade for Java）---与log4j、Logback同一个人开发 | JUL（java.util.logging）---担心被抢市场，推出的<br>Log4j（存在性能问题）<br>**Logback**（Log4j同一个人开发的新框架，做了重大升级）<br>Log4j2（apache开发的很强大，借了log4j的名，但当前很多框架未适配上） |

Spring Boot 采用了 **slf4j+logback** 的组合形式，Spring Boot也提供对JUL、log4j2、Logback提供了默认配置；

2、log五大日志级别：（由低到高）

```java
//1、下面定义的都是日志级别，由低到高：trace < debug < info < warn < error
//2、spring boot默认设定的是 info 级别日志（日志默认级别也称为root级别）
//3、可以通过配置文件进行修改 日志级别，设置某一个级别后，就只打印这个级别及后面更高级别的日志信息，没有指定级别的就用springBoot默认规定的级别，root级别
//跟踪运行信息
logger.trace("这是 trace 日志信息");
//调试信息
logger.debug("这是 debug 日志信息");
//自定义信息
logger.info("这是 info 日志信息");
//警告信息
logger.warn("这是 warn 日志信息");
//错误信息
logger.error("这是 error 日志信息");
```

```java
//调整指定包的日志级别
logging.level.com.xx=debug
//修改springboot的root级别（默认级别）
logging.level.root=debug
```

3、修改日志默认配置：

- 修改日志文件生成路径：

  | logging.file | logging.path |      示例       |                             说明                             |
  | :----------: | :----------: | :-------------: | :----------------------------------------------------------: |
  |    (none)    |    (none)    |                 |                        只在控制台输出                        |
  |  指定文件名  |    (none)    | springboot.log  |         输出到**当前根路径下**的springboot.log文件中         |
  |    (none)    |   指定目录   | /springboot/log | 输出到当前项目所在磁盘根路径下的/springboot/log目录中的spring.log文件中 |
  |  指定文件名  |   指定目录   |                 | 当**两个同时指定时，采用的是logging.file指定**。推荐使用logging.file设置即可，因为它可自定义文件名 |

```java
//输出到当前项目根路径下的springboot.log文件中
logging.file=springboot.log
//输出到当前项目所在磁盘根路径下的/springboot/log目录中的spring.log文件中 E:\springboot\log\spring.log
logging.path=/springboot/log
```

- 修改日志输出的格式：

  ```java
  /**日志输出格式说明：
  * %d 输出日期时间，
  * %thread 输出当前线程名，
  * %-5level 输出日志级别，左对齐5个字符宽度
  # %logger{50} 输出全类名最长50个字符，超过按照句点分割
  # %msg 日志信息
  # %n 换行符  */
  //修改控制台输出的日志格式
  logging.pattern.console=%d{yyyy-MM-dd} == [%thread] %-5level %logger{50} - %msg%n
  //修改文件中输出的日志格式
  logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} >>> [%thread] >>> %-5level >>> %logger{50} - %msg%n
  ```

4、自定义日志配置：

- logback.xml ：是直接就被日志框架加载了；

- logback-spring.xml：配置项不会被日志框架直接加载，而是由 SpringBoot 解析日志配置文件，进而可以使用 SpringBoot 的 Profile 特殊配置 ；   

  - 使用日志profile特殊配置，可根据不同的环境激活不同的日志配置：

    ```java
    <layout class="ch.qos.logback.classic.PatternLayout">
                //根据不同的环境要求进行配置不同的日志信息
                <springProfile name="dev">
                    <!-- configuration to be enabled when the "staging" profile is active -->
                    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} === [%thread] === %-5level === %logger{50} === %msg%n</pattern>
                </springProfile>
                <springProfile name="!dev">
                    <!-- configuration to be enabled when the "staging" profile is active -->
                    <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} >>> [%thread] >>> %-5level >>> %logger{50} >>> %msg%n</pattern>
                </springProfile>
            </layout>
    ```

5、切换日志框架：

```java
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			//采用log4j2的日志框架
			//将spring-boot-starter-logging移除
			<exclusions>
				<exclusion>
					<artifactId>spring-boot-starter-logging</artifactId>
					<groupId>org.springframework.boot</groupId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-log4j2</artifactId>
		</dependency>
```

## web开发

1、springboot web启动器包括嵌入的Servlet容器和SpringMVC；

2、springboot对静态资源（webjars/静态文件）的映射：

```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
if (!this.resourceProperties.isAddMappings()) {
logger.debug("Default resource handling disabled");
} else {
Duration cachePeriod =
this.resourceProperties.getCache().getPeriod();
CacheControl cacheControl =
this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
if (!registry.hasMappingForPattern("/webjars/**")) {
//收到 /webjars/**请求后 ，会去classpath:/META-INF/resources/webjars/ 查找资源文件
this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]
{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/METAINF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
```

访问 localhost:8080/webjars/jquery/3.3.1/jquery.js 会在下面路径中查找：    

```java
//引入jquery webjars
<dependency>
	<groupId>org.webjars</groupId>
	<artifactId>jquery</artifactId>
	<version>3.3.1</version>
</dependency>
```

当接受到 /** 请求访问资源时, 会被映射到下面4个 类路径下的静态资源目录中查找  ：

```java
classpath:/META-INF/resources/
classpath:/resources/
classpath:/static/
classpath:/public/
```

访问 localhost:8080/style.css 会在上面四个静态资源路径中查找文件 ；      

3、欢迎页的映射：

会从 4个静态资源目录 + 根路径 / 中 查找 index.html 页面：

```java
classpath:/META-INF/resources/
classpath:/resources/
classpath:/static/
classpath:/public/
/: 当前项目根路径下
```

- 会在 静态资源目录下 与 根路径查找 (按该顺序) index.html页面； 收到 "/**" 请求映射；
-  访问 localhost:8080/ 会在上面5个目录中查找 index.html页面（因为/也属于/**）；   

4、项目ico图标的映射：

Spring Boot 会在静态资源目录下 与 根路径(按该顺序) 查找 faicon.ico 页面；

如果存在这样的文件，springboot会自动将其设置为应用图标；

5、Thymeleaf模板引擎：

- 嵌入式tomcat默认不支持jsp；

- Spring Boot 官方推荐使用 Thymeleaf 作为模板引擎， 因为 Thymeleaf 提供了完美的 SpringMVC 的支持；

- pom.xml 加入 Thymeleaf 启动器：

  ```java
  //thymeleaf 模板启动器
  <dependency>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
  ```

- 模板文件放在哪里 ?    DEFAULT_PREFIX = "**classpath:/templates/**"；

  将 HTML 页面放到 classpath:/templates/ 目录下， Thymeleaf 就能自动渲染；    

- 在 html 页面加上以下名称空间, 使用 Thymeleaf 时就有语法提示 ：

  ```html
  <html xmlns:th="http://www.thymeleaf.org">  
  ```

 6、Thymeleaf语法：

- 常用属性：

  | 优先级 |                   属性名                   |                             作用                             |
  | :----: | :----------------------------------------: | :----------------------------------------------------------: |
  |   1    |          th:insert<br>th:replace           |    引入片段，与th:fragment声明组合使用；类似于jsp:include    |
  |   2    |                  th:each                   |                    遍历，类似于c:forEach                     |
  |   3    | th:if<br>th:unless<br>th:switch<br>th:case |                     条件判断，类似于c:if                     |
  |   4    |            th:object<br>th:with            |                    声明变量，类似于c:set                     |
  |   5    | th:attr<br>th:attrprepend<br>th:attrappend |        修改任意属性，prepend前面追加，append后面追加         |
  |   6    |    th:value<br>th:href<br>th:src<br>...    |                  **修改任意html原生属性值**                  |
  |   7    |            th:text<br>th:utext             | 修改标签体中的内容<br>th:text转义特殊字符度，即h1标签以文本显示出来<br>th:utext是不转义特殊字符，即h1标签展现出本来效果 |
  |   8    |                th:fragment                 |                           声明片段                           |
  |   9    |                 th:remove                  |                           移除片段                           |

- 标准表达式语法：

  ```java
  一、Simple expressions（表达式语法）
  	1. Variable Expressions(变量表达式): ${...} （参考： 4.2 Variables）
  		1)、获取变量值；使用OGNL表达式；
  		2）、获取对象的属性, 调用方法
  		3）、使用内置的基本对象：
  			#ctx : the context object.(当前上下文对象)
  			#vars: the context variables.(当前上下文里的变量)
  			#locale : the context locale. (当前上下文里的区域信息)
  			下面是Web环境下的隐式对象
  			#request : (only in Web Contexts) the HttpServletRequest object.
  			#response : (only in Web Contexts) the HttpServletResponse object.
  			#session : (only in Web Contexts) the HttpSession object.
  			#servletContext : (only in Web Contexts) the ServletContext object.
  			示例: ${session.foo} (用法参考: 18 Appendix A: Expression Basic Objects)
  		4）、使用内置的工具对象：(用法参考: 19 Appendix B: Expression Utility Objects)
              #execInfo : information about the template being processed.
              #messages : methods for obtaining externalized messages inside variables
              expressions, in the same way as they would be obtained using #{…} syntax.
              #uris : methods for escaping parts of URLs/URIs
              #conversions : methods for executing the configured conversion service
              (if any).
              #dates : methods for java.util.Date objects: formatting, component
            	extraction, etc.
              #calendars : analogous to #dates , but for java.util.Calendar
              objects.
              #numbers : methods for formatting numeric objects.
              #strings : methods for String objects: contains, startsWith,
              prepending/appending, etc.
              #objects : methods for objects in general.
              #bools : methods for boolean evaluation.
              #arrays : methods for arrays.
              #lists : methods for lists.
              #sets : methods for sets.
              #maps : methods for maps.
  		   #aggregates : methods for creating aggregates on arrays or collections.
  		   #ids : methods for dealing with id attributes that might be repeated
  			(for example, as a result of an iteration)
      2. Selection Variable Expressions(选择表达式): *{...}
      （参考：4.3 Expressions on selections）
     		 1）、和${}在功能上是一样， 额外新增：配合 th:object 使用
    			  <div th:object="${session.user}">
      			省得每次写${session.user.firstName}, 直接取出对象，然后写对象名即可
     				<p>Name: <span th:text="*{firstName}">Sebastian</span> </p>
      			<p>Email: <span th:text="*{email}">Saturn</span> </p>
      		  </div>
      3. Message Expressions（获取国际化内容）: #{...} （参考：4.1 Messages）
      4. Link URL Expressions（定义URL）: @{...} （参考：4.4 Link URLs）
      5. Fragment Expressions（片段引用表达式）: ~{...} （参考：4.5 Fragments）
      	<div th:insert="~{commons :: main}">...</div>
  二、Literals（字面量） （参考： 4.6 Literals）
  	1. Text literals: 'one text' , 'Another one!' ,…
  	2. Number literals: 0 , 34 , 3.0 , 12.3 ,…
  	3. Boolean literals: true , false
  	4. Null literal: null
  	5. Literal tokens: one , sometext , main, ... 
  三、Text operations（文本操作） （参考： 4.7 Appending texts）
  	1. String concatenation: +
  	2. Literal substitutions: |The name is ${name}|
  四、Arithmetic operations（数学运算） （参考： 4.9 Arithmetic operations）
  	1. Binary operators: + , - , * , / , %
  	2. Minus sign (unary operator): -
  五、Boolean operations（布尔运算）
  	1. Binary operators: and , or
  	2. Boolean negation (unary operator): ! , not
  六、Comparisons and equality（比较运算） （参考： 4.10 Comparators and Equality）
  	1. Comparators: > , < , >= , <= ( gt , lt , ge , le )
  	2. Equality operators: == , != ( eq , ne )
  七、Conditional operators(条件表达式;三元运算符) （参考： 4.11 Conditional expressions）
  	1. If-then: (if) ? (then)
  	2. If-then-else: (if) ? (then) : (else)
  	3. Default: (value) ?: (defaultvalue)
  八、Special tokens（特殊操作） (参考： 4.13 The No-Operation token)
  	1. No-Operation: -
  ```

- ```html
   <!--
          th:insert 和 th:replace的区别
          th:insert和th:replace都可以引入片段，两者的区别在于
          th:insert： 保留引入时使用的标签
          th:replace：不保留引入时使用的标签, 将声明片段直接覆盖当前引用标签
      -->
      <div th:replace="header :: #header_common_id" class="success"></div>
      <h2 th:insert="header :: #header_common_id"></h2>
   ```
  ```
7、迭代(th:each)：

​```html
 <!--th:each作用在哪个标签上面，对应的就会根据它的值迭代多少次-->
    <!--
        user : 第1个值,代表每次迭代出对象,名字任意取
        iterStat : 第2个值,代表每次迭代器内置对象, 名字任意取, 并有如下属性:
        index : 当前迭代下标 0 开始
        count : 当前迭代下标 1 开始
        size : 获取总记录数
        current : 当前迭代出的对象
        even/odd : 当前迭代是偶数还是奇数 (1开始算,返回布尔值)
        first : 当前是否为第一个元素
        last : 当前是否为最后一个元素
    -->
    <tr th:each="user, iterStat: ${userList}">
        <td th:text="${iterStat.count}">000</td>
        <td th:text="${user.username}">xxx</td>
        <td th:text="${user.gender == 1 ? '男' : '女'}">1</td>
        <td th:text="${iterStat.size}">xxx</td>
        <td th:text="${iterStat.even}? '偶数' : '奇数'">xxx</td>
        <td th:text="${iterStat.first}">xxx</td>
        <td th:text="${iterStat.last}">xxx</td>
    </tr>
</table>
  ```

8、条件判断：

```html
<h3 th:if="not ${#lists.isEmpty(userList)}">显示出来则userList集合不为空</h3>
<h3 th:unless="${#lists.isEmpty(userList)}">显示出来则userList集合不为空</h3>
<div th:switch="${sex}">
    <p th:case="${man}">男</p>
    <p th:case="2">女</p>
    <p th:case="*">未知</p>
</div>
```

9、显示标签体内容：

```html
<!--不转义字符-->
<div th:text="${desc}"></div>
<!--转义字符-->
<div th:utext="${desc}"></div>
```

显示对象：

```html
<div th:object="${session.user}">
    <!--上面已经将对象获取出来，下面直接写属性值即可获取到值-->
    <p>姓名：<span th:text="*{username}"></span></p>
    <p>性别：<span th:text="*{gender} == 1 ? '男' : '女'"></span></p>
</div>
```

10、springboot热部署：

```java
//开发环境建议关闭thymeleaf缓存
spring.thymeleaf.cache=false
//热部署依赖
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-devtools</artifactId>
</dependency>
//Ctrl + F9 编译
```

11、扩展springmvc功能：

```java
/**
 * 1、创建一个WebMvcConfigurer类型的子类
 * 2、类上用@Configuration标识它是一个配置类
 * 3、不能@EnableWebMvc标识
 * 原理：
 *  1、@Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class})
 *  2、public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration
 * */
@Configuration
public class MySpringConfigurer implements WebMvcConfigurer {
    //增加视图控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //发送 /xuexue 请求来到 success.html
        registry.addViewController("/xuexue").setViewName("success");
    }
}
```

12、全面控制springmvc：

在自定义的Web配置类上添加 @Configuration 和 @EnableWebMvc 注解；

总结SpringMVC 配置：

- 在Spring Boot中自已配置组件的时候，先看容器中有没有公司自已配置的（@Bean、@Component）,如果 有就用公司自已配置的; 如果没有，才自动配置；
- 在Spring Boot中会有非常多的xxxConfigurer帮助我们进行扩展配置；    
- 在Spring Boot中会有很多的xxxCustomizer帮助我们进行定制配置；    

## Spring Boot Restful架构

Restful架构：通过HTTP请求方式**区别对资源CRUD操作**，请求URI是/资源名称/资源标识；

|      |       普通CRUD        |       RestfulCRUD        |
| :--: | :-------------------: | :----------------------: |
| 查询 |      getPrvider       |     provider ---GET      |
| 添加 |    addProvider?xxx    |     provider ---POST     |
| 修改 | updateProvider?id=xxx |  provider /{id} ---PUT   |
| 删除 |  deleteProvider?id=1  | provider /{id} ---DELETE |

## springboot错误处理机制

1、几种常见错误（状态码）：

- 浏览器发送一个不存在的请求时，会报404；    
- 500；

2、timestamp：时间戳， status：状态码， error：错误提示， exception：异常对象， message：异常消息， errors：JSR303数据校验出现的错误；

## 嵌入式Sevlet容器自定义配置

1、注册Servlet三大组件 Servlet/Filter/Listener：

- ServletRegistrationBean ：注册自定义Servlet ；
- FilterRegistrationBean：注册自定义Filter；   
- ServletListenerRegistrarionBean：注册自定义Listener；

2、修改嵌入式servlet 容器配置：

```java
//修改服务相关的配置
server.port=8081
//修改关于servlet的相关配置
server.servlet.context-path=/servlet
//修改tomcat相关配置
server.tomcat.max-connections=8000
```

3、自定义定制器修改嵌入式servlet容器配置：

```java
@Bean
public WebServerFactoryCustomizer webServerFactoryCustomizer() {
    return new WebServerFactoryCustomizer() {
        @Override
        public void customize(WebServerFactory factory) {
            ConfigurableServletWebServerFactory factory1 =
                (ConfigurableServletWebServerFactory)factory;
            //修改端口号，如果配置文件中与定制器中的配置冲突，默认采用定制器的配置
            factory1.setPort(8082);
            factory1.setContextPath("/servlet2");
        }
    };
}
```

## 事务管理

```java
//默认创建表类型是MyISAM，是非事务安全的，所以无法实现事物回滚
//指定如下方言: 创建的表类型是Innodb，才可以进行对事物的回滚
database-platform: org.hibernate.dialect.MySQL57Dialect
```

事务管理步骤：    

- 在启动类上 ，使用 @EnableTransactionManagement 开启注解方式事务支持；
- 在 Service层方法上添加 @Transactional 进行事务管理；

## 定时任务

使用的注解：

-  @EnableScheduling 启动类上开启基于注解的定时任务 ；
-  @Scheduled 标识的方法会进行定时处理 ；

需要通过 cron 属性来指定 cron 表达式： 秒 分 时 日 月 星期几；

![](E:\Study-hx\技术总结_2019\img\cron1.PNG)

​  ![](E:\Study-hx\技术总结_2019\img\cron2.PNG)    































































































































































































​       