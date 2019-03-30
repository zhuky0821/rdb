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

    @Bean("tableColumns")
    public Map<String, String[]> tableColumns() {
        column.put("1111", new String[]{});
        return column;
    }

    @Bean("tableIndex")
    public Map<String, Map<String, String[]>> tableIndex(){
        initIndex();
        return index;
    }


    private void initIndex(){

        for(String table : tableList){

            try {
                Class tableClass = Class.forName(table);
                System.out.println("33333=" + tableClass.getName()+ "   " + tableClass.getSimpleName());
                //获取类成员变量
                Field indexField = tableClass.getDeclaredField("index");
                indexField.setAccessible(true);
                Object o = indexField.get(tableClass);
                System.out.println("数据类型：" + o.getClass().getTypeName() + "; 值：" + o);
                Map<String, String[]> indexDeclared = (Map<String, String[]>)o ;
                System.out.println("indexDeclared=" + indexDeclared);

                Iterator<Map.Entry<String, String[]>> iterator = indexDeclared.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, String[]> _name = iterator.next();
                    System.out.println("111111=" + _name.getKey());
                    String[] _names = _name.getValue();
                    for(String s : _names){
                        System.out.println("222222=" + s);
                    }
                }

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


    private void initColumn(){

        for(String table : tableList){

            try{
                Class tableClass = Class.forName(table);

                //获取类成员变量
                Field[] tableFields = tableClass.getDeclaredFields();
                String[] tableFieldNames = new String[tableFields.length];

                for(int t=0; t<tableFields.length; t++){
                    tableFieldNames[t] = tableFields[t].getName();

                }

            }catch (ClassNotFoundException e){
                log.error("无法加载类：" + table);
            }
        }

    }

}
