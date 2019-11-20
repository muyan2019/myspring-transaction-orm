package com.muyan.springboot.myspringtransaction.controller;

import com.muyan.springboot.myspringtransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @ClassName StudentController
 * @Description 学生控制层
 * @Author dym
 * @Date 2019/8/30 8:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public void addData() throws SQLException {
        userService.addData();
    }
}
