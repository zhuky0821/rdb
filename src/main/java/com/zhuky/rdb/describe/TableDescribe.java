package com.zhuky.rdb.describe;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 使用反射加载包下的所有表对象，将属性插入到map中
 */

@Component
public class TableDescribe {

    private Logger log = LogManager.getLogger(TableDescribe.class);

    @Autowired
    private List<String> tableList;

    //索引
    private Map<String, Map<String, String[]>> index = new HashMap<>();

    //列
    private Map<String, String[]> column = new HashMap<>();


    private TableIndexAndCol tableIndexAndCol = new TableIndexAndCol(index, column);



    @Bean("tableIndexAndCol")
    public TableIndexAndCol tableIndexAndCol(){
        init();
        return tableIndexAndCol;
    }


    private void init(){

        for(String table : tableList){

            try {
                Class tableClass = Class.forName(table);

                //列名
                //获取类成员变量
                Field[] tableFields = tableClass.getDeclaredFields();
                String[] tableFieldNames = new String[tableFields.length - 1];

                for(int t=0; t<tableFields.length - 1; t++){
                    tableFieldNames[t] = tableFields[t].getName();
                }
                column.put(tableClass.getSimpleName(), tableFieldNames);

                //索引
                //获取类成员变量
                Field indexField = tableClass.getDeclaredField("index");
                indexField.setAccessible(true);
                Object o = indexField.get(tableClass);
                Map<String, String[]> indexDeclared = (Map<String, String[]>)o ;

                Iterator<Map.Entry<String, String[]>> iterator = indexDeclared.entrySet().iterator();

                index.put(tableClass.getSimpleName(), indexDeclared);
            }
            catch (ClassNotFoundException e){
                log.error("无法加载类：" + table);
            } catch (NoSuchFieldException e) {
                log.error("类{0}没有index列", table);
            } catch (IllegalAccessException e) {

                e.printStackTrace();
                log.error("类型无法转换");
            }
        }
    }

}

