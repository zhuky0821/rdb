package com.zhuky.rdb.describe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Configuration
public class TableConfig {

    private Logger logger = LogManager.getLogger(TableConfig.class);

//    public void setTables(String tables) {
//        System.out.println("setTables:" + tables);
//        this.tables = tables;
//    }


    private List<String> init() {

        List<String> tableList = null;
        try {
            Properties properties = new Properties();

            InputStream in = TableConfig.class.getClassLoader().getResourceAsStream("config/rdb.properties");

            properties.load(in);

            logger.info("加载properties参数");



            for (String keyName : properties.stringPropertyNames()) {
                String value = properties.getProperty(keyName);

                if(keyName.equals("rdb.table")){
                    String tables = value;

                    String[] _table = tables.split(",");
                    tableList = new ArrayList<>(Arrays.asList(_table));
                }

                logger.info("properties---------key:{},value:{}", keyName, value);
            }
            logger.info("properties参数加载完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tableList;
    }

    @Bean
    public List<String> tableList(){
        return init();
    }

}
