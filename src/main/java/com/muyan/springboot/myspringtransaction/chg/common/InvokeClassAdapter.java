package com.muyan.springboot.myspringtransaction.chg.common;

import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanColumn;
import com.muyan.springboot.myspringtransaction.chg.annotation.MuyanId;
import com.muyan.springboot.myspringtransaction.chg.utils.MuyanStringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName InvokeClassAdapter
 * @Description 提供数据库查询结果和实体类的转化
 * @Author dym
 * @Date 2019/9/5 17:48
 * @Version 1.0
 **/
@Component
public class InvokeClassAdapter {

    /**
     *@Description  将数据库中的一条记录封装成一个实体类
     *@Author dym
     *@Date 2019/9/3 10:14
     *@Param [resultSet, clazz]
     *@return T
     */
    public static <T> T invokeObject(ResultSet resultSet, Class<T> clazz){
        Map<String,Field> map = new HashMap<>(  );
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent( MuyanColumn.class )) {
                    MuyanColumn annotation = field.getAnnotation( MuyanColumn.class );
                    String annotationColomnName = annotation.colomnName() == null?field.getName(): annotation.colomnName();
                    //将注解名称和属性存放到map中
                    map.put( annotationColomnName,  field);
                }else if (field.isAnnotationPresent( MuyanId.class )){
                    MuyanId annotation = field.getAnnotation( MuyanId.class );
                    map.put( field.getName(), field );
                }
            }
            T obj = clazz.getDeclaredConstructor().newInstance();
            ResultSetMetaData metaData = null;

            metaData = resultSet.getMetaData();

            for (int i = 0;i < metaData.getColumnCount(); i++){
                String columnName = metaData.getColumnName( i + 1 );
                if (map.containsKey( columnName )) {
                    Field field = map.get( columnName );
                    Class<?> type = field.getType();
                    //获取方法名称
                    String setMethod = MuyanStringUtils.getSetMethod( field.getName() );
                    Method method = clazz.getMethod( setMethod, type );
                    Object object = resultSet.getObject( i + 1 );
                    method.invoke( obj, type.cast( object ) );
                }
            }
            return obj;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
