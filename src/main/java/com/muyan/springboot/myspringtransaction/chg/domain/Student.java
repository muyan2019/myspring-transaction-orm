package com.muyan.springboot.myspringtransaction.chg.domain;

import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanColumn;
import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanId;
import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanTableName;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Student
 * @Description 实体类,这里采用自定义注解
 * @Author dym
 * @Date 2019/8/30 14:57
 * @Version 1.0
 **/
@Data
@MuyanTableName(tableName="student")
@ToString
public class Student {

    @MuyanId(idName = "id")
    private Integer id;

    @MuyanColumn(colomnName = "name")
    private String name;

    @MuyanColumn(colomnName = "age")
    private Integer age;

    @MuyanColumn(colomnName = "sex")
    private String sex;
}
