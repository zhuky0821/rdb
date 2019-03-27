package com.zhuky.rdb.describe;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
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

    @Bean
    public Map<String, String[]> tableColumns() {
        return init();
    }


    private Map<String, String[]> init(){
        Map<String, String[]> tableColumns = new HashMap<>();
        for(String table : tableList){

            try {
                Class tableClass = Class.forName(table);
                //获取类成员变量
                Field[] tableFields = tableClass.getDeclaredFields();
                String[] tableFieldNames = new String[tableFields.length];
                for(int j=0; j<tableFields.length; j++){
                    tableFieldNames[j] = tableFields[j].getName();
                }

                tableColumns.put(tableClass.getName(), tableFieldNames);

            }
            catch (ClassNotFoundException e){
                log.error("无法加载类：" + table);
            }
        }
        return tableColumns;
    }


}
