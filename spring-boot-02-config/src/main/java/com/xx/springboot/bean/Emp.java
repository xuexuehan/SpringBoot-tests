package com.xx.springboot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;
/*
* ConfigurationProperties: 告诉spring boot将配置文件中的对应属性值，映射到这个组件类中，进行一一绑定
* prefix = "emp": 配置文件中的前缀名，配置了哪个前缀就会与下面的所有属性进行一一映射
* @Component: 将当前组件作为springboot中的一个组件来使用，这样才会纳入容器中管理
* @PropertySource: 加载局部配置文件
*
* */
@PropertySource(value = {"classpath:emp.properties"})
@Validated
@Component
@ConfigurationProperties(prefix = "emp")
public class Emp {
    /**
     *类似于spring框架中使用配置文件中的数据注入方式
     * <bean class="Emp">
     *     <property name="lastName" value="字面量/#{SpEL} spring表达式/${key}从配置文件获取"></property>
     * </bean>
     *
     * */
    //@Value("${emp.last-name}")

    //@Email
    private String lastName;
    //@Value("#{10*2}")
    private Integer age;
    //@Value("8000")
    private Double salary;
    private Boolean boss;
    private Date birthdy;
    private Map map;
    private List list;

    private Forte forte;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirthdy() {
        return birthdy;
    }

    public void setBirthdy(Date birthdy) {
        this.birthdy = birthdy;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Forte getForte() {
        return forte;
    }

    public void setForte(Forte forte) {
        this.forte = forte;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", boss=" + boss +
                ", birthdy=" + birthdy +
                ", map=" + map +
                ", list=" + list +
                ", forte=" + forte +
                '}';
    }
}
