package com.github.model;

import com.github.annotation.Alias;
import com.github.annotation.Component;

/**
 * java对象由三部分组成：对象头，实例数据，对齐填充
 * @author 康盼Java开发工程师
 */
@Component
public class User {
    @Alias("userId")
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public User() {
    }

    public User(Long id, String name, Integer age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
