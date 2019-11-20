package com.muyan.springboot.myspringtransaction.chg.service;

import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanTransction;
import com.muyan.springboot.myspringtransaction.chg.domain.OptionLog;
import com.muyan.springboot.myspringtransaction.chg.domain.Student;
import com.muyan.springboot.myspringtransaction.chg.mapper.LogMapper;
import com.muyan.springboot.myspringtransaction.chg.mapper.StudentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ChangerUserService
 * @Description TODO
 * @Author dym
 * @Date 2019/8/30 10:16
 * @Version 1.0
 **/
@Service
public class ChangerStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LogMapper logMapper;


    /**
     *@Description  保存实体
     *@Author dym
     *@Date 2019/9/3 17:53
     *@Param [student]
     *@return boolean
     */
    @MuyanTransction
    public boolean saveDataByOrm(Student student){
        boolean result = studentMapper.add( student );
        OptionLog optionLog = new OptionLog();
        optionLog.setLogContent("新增学生数据："+student.toString());
        optionLog.setLogTime( new Date());
        logMapper.add( optionLog );
        //int i = 1/0;  如果这里打开  则事务全部回滚
        return result;
    }

    /**
     *@Description  保存编辑实体
     *@Author dym
     *@Date 2019/9/3 17:54
     *@Param [student]
     *@return boolean
     */
    @MuyanTransction
    public boolean update(Student student){
        boolean result = studentMapper.update( student );
        OptionLog optionLog = new OptionLog();
        optionLog.setLogContent("变更学生数据："+student.toString());
        optionLog.setLogTime( new Date());
        logMapper.add( optionLog );
        return result;
    }

    /**
     *@Description  根据id删除实体
     *@Author dym
     *@Date 2019/9/3 17:55
     *@Param [id]
     *@return boolean
     */
    @MuyanTransction
    public boolean delete(int id){
        boolean result = studentMapper.delete( id );
        OptionLog optionLog = new OptionLog();
        optionLog.setLogContent("删除ID为："+id+"的学生数据：");
        optionLog.setLogTime( new Date());
        logMapper.add( optionLog );
        return result;
    }

    /**
     *@Description  根据ID查询student实体
     *@Author dym
     *@Date 2019/9/3 17:56
     *@Param [id]
     *@return com.muyan.springboot.myspringtransaction.chg.domain.Student
     */
    public Student query(int id){
        Student student = (Student) studentMapper.query( id );
        return student;
    }
    /**
     *@Description  查询所有的学生列表
     *@Author dym
     *@Date 2019/9/3 17:58
     *@Param []
     *@return java.util.List<com.muyan.springboot.myspringtransaction.chg.domain.Student>
     */
    public List<Student> queryAll(){
        List<Student> list = studentMapper.queryAll();
        return list;
    }
}
