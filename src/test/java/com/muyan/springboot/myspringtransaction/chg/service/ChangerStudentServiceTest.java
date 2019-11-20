package com.muyan.springboot.myspringtransaction.chg.service;

import com.muyan.springboot.myspringtransaction.chg.domain.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangerStudentServiceTest {

    @Autowired
    private ChangerStudentService changerStudentService;

    @Test
    public void saveData() {
    }

    @Test
    public void saveDataByOrm() {
        Student student = new Student();
        student.setName("muyan456");
        student.setAge(40);
        student.setSex("男");
        boolean b = changerStudentService.saveDataByOrm( student );
        Assert.assertFalse( b );
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setId(35);
        student.setName("muyan1234");
        student.setAge(20);
        student.setSex("男");

        boolean result = changerStudentService.update( student );
        Assert.assertFalse( result );

    }

    @Test
    public void delete() {
        boolean result = changerStudentService.delete( 37 );
        Assert.assertFalse( result );
    }

    @Test
    public void query() {
        Student student = changerStudentService.query( 35 );
        System.out.println( "查询结果为:"+student );
        Assert.assertNotNull( student );
    }

    @Test
    public void queryAll() {
        List<Student> students = changerStudentService.queryAll();
        if (null != students && students.size() > 0){
            for (Student student : students) {
                System.out.println( student );
            }
        }
    }
}