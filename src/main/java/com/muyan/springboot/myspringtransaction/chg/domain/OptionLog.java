package com.muyan.springboot.myspringtransaction.chg.domain;

import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanColumn;
import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanId;
import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanTableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


/**
 * @ClassName OptionLog
 * @Description 操作日志表
 * @Author dym
 * @Date 2019/9/6 10:21
 * @Version 1.0
 **/
@Data
@ToString
@MuyanTableName(tableName = "option_log")
public class OptionLog {

    @MuyanId(idName = "log_id")
    private Integer logId;

    @MuyanColumn(colomnName = "log_content")
    private String logContent;

    @MuyanColumn(colomnName = "log_time")
    private Date logTime;
}
