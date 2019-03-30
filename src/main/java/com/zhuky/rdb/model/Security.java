package com.zhuky.rdb.model;


import java.util.HashMap;
import java.util.Map;

/**
 * 例子：
 * securityId          60057001         102                202
 * mktId                  1              2                 2
 * securityCode        600570           000001             000002
 * securityName        恒生电子         平安银行           万科
 *
 * 在redis中数据存放格式
 *    K                                     V                           类型
 *    row1                        securityId     60057001               表数据
 *                                mktId          1
 *                                securityCode   600570
 *                                securityName   恒生电子
 *   row2                         securityId     102                    表数据
 *                                mktId          2
 *                                securityCode   000001
 *                                securityName   平安银行
 *   row3                         securityId     202                    表数据
 *                                mktId          2
 *                                securityCode   000002
 *                                securityName   万科
 *   security:60057001            row1                                  主键索引
 *   security:102                 row2                                  主键索引
 *   security:202                 row3                                  主键索引
 *   uSecurity1:1:600570          row1                                  唯一索引
 *   uSecurity1:2:000001          row2                                  唯一索引
 *   uSecurity1:2:000002          row3                                  唯一索引
 *   nSecurity1:1                 row1                                  非唯一索引
 *   nSecurity1:2                 row2                                  非唯一索引
 *                                row3
 *   security                     row1                                  表索引
 *                                row2
 *                                row3
 *
 *   增：首先增加表数据，记录rowId,之后增加修改所有的索引和表索引,将这个rowId记录进去，主要涉及到主键索引，唯一索引，非唯一索引的创建和表索引的维护
 *   删：对各个索引进行维护，之后删除表数据
 *   改：如果涉及到索引字段的修改，需要对该索引进行维护，之后修改表数据
 *
 */

public class Security {

    private int securityId;
    private int mktId;
    private String securityCode;
    private String securityName;

    //索引
    private static Map<String, String[]> index = new HashMap<>();
    static {
        //主键
        index.put("security", new String[]{"0", "securityId"});
        //唯一索引
        index.put("uSecurity1", new String[]{"1", "mktId", "securityCode"});
        //非唯一索引
        index.put("nSecurity1", new String[]{"2", "mktId"});
    }

}
