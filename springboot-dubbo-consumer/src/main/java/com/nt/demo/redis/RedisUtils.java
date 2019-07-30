package com.nt.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Create by TaoTaoNing
 * 2019/7/25
 **/
public class RedisUtils {

    private static final Jedis JEDIS = new Jedis("localhost");
    private static final JedisPool JEDISPOOL;

    static {
        // 获取连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(50);
        //设置最小空闲数
        config.setMinIdle(8);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //Idle时进行连接扫描
        config.setTestWhileIdle(true);
        //表示idle object evitor两次扫描之间要sleep的毫秒数
        config.setTimeBetweenEvictionRunsMillis(30000);
        //表示idle object evitor每次扫描的最多的对象数
        config.setNumTestsPerEvictionRun(10);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        config.setMinEvictableIdleTimeMillis(60000);

        // 获得连接池: JedisPool jedisPool = new JedisPool(poolConfig,host,port);
        JEDISPOOL = new JedisPool(config, "localhost", 6379);
    }


    public static Jedis getRedis() {
// 获得核心对象：jedis
        Jedis jedis = null;
        try {
            // 通过连接池来获得连接
            jedis = JEDISPOOL.getResource();
            // 设置数据
            jedis.set("name", "张三");
            // 获取数据
            String value = jedis.get("name");
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            // 释放资源
//            if (jedis != null) {
//                jedis.close();
//            }
//            // 释放连接池
//            if (JEDISPOOL != null) {
//                JEDISPOOL.close();
//            }
        }
        return jedis;
    }

    public static Jedis getInstance() {
        // System.out.println("redis 正在运行" + JEDIS.ping());
        return JEDIS;
    }
}
