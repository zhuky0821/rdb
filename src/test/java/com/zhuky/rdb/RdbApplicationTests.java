package com.zhuky.rdb;

import com.zhuky.rdb.dao.MRowDao;
import com.zhuky.rdb.describe.TableConfig;
import com.zhuky.rdb.describe.TableDescribe;
import com.zhuky.rdb.model.MRow;
import com.zhuky.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan(basePackages = {"com.*"})
public class RdbApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MRowDao mRowDao;

    @Autowired
    TableDescribe tableDescribe;

    @Autowired
    TableConfig tableConfig;

//    @Test
//    public void set() {
//        redisTemplate.opsForValue().set("test:set1", "testValue1");
//        redisTemplate.opsForSet().add("test:set2", "asdf");
//        redisTemplate.opsForHash().put("hash1", "name1", "lms1");
//        redisTemplate.opsForHash().put("hash1", "name2", "lms2");
//        redisTemplate.opsForHash().put("hash1", "name3", "lms3");
//        System.out.println("set值:" + redisTemplate.opsForValue().get("test:set1"));
//        System.out.println("hash值" + redisTemplate.opsForHash().get("hash1", "name1"));
//    }

    @Test
    public void redisUtilTest(){
        System.out.println(redisUtil.get("test:set1"));
    }


    @Test
    public void redisUtilTest2(){

        Map<String, Object> user = new HashMap<>();
        user.put("name", "zhu");
        user.put("age", 10);

        redisUtil.hmset("A01", user);


    }

    @Test
    public void redisUtilTest3(){

        redisUtil.hset("user", "name", "zhu");


    }

    @Test
    public void test3(){

        MRow mRow = new MRow();
        mRow.setRowId("A01");
        Map<String, Object> user = new HashMap<>();
        user.put("name", "zhu");
        user.put("age", 10);
        mRow.setColumnsData(user);

        System.out.println(mRow);

        mRowDao.insert(mRow);


    }

    @Test
    public void test4(){

        MRow mRow = new MRow();
        mRow.setRowId("A01");

        mRowDao.delete(mRow);


    }

    @Autowired
    Map<String, String[]> tableColumns;

    @Test
    public void test5(){

        Iterator<Map.Entry<String, String[]>> iterator = tableColumns.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String[]> table = iterator.next();
            String[] columns = table.getValue();
            for(int i=0; i<columns.length; i++){
                System.out.println(columns[i]);
            }
        }

    }




}
