package com.xcx.xestore.manager.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisManager {
    private final static Logger logger = LoggerFactory.getLogger(RedisManager.class);

    @Autowired
    private JedisPool jedisPool;
/*
    @Value("${redis.password}")
    private String password;*/

    private Jedis getResource() {
        Jedis resource = jedisPool.getResource();
        /*if (StringUtils.isBlank(password)) {
            return resource;
        } else {
            resource.auth(password);*/
        return resource;
        /*}*/
    }



    public String get(String key) {

        Jedis resource = getResource();

        String string = resource.get(key);

        resource.close();

        return string;

    }


    public String set(String key, String value) {

        Jedis resource = getResource();

        String string = resource.set(key, value);

        resource.close();

        return string;

    }


    public String hget(String hkey, String key) {

        Jedis resource = getResource();

        String string = resource.hget(hkey, key);

        resource.close();

        return string;

    }


    public long hset(String hkey, String key, String value) {

        Jedis resource = getResource();

        Long hset = resource.hset(hkey, key, value);

        resource.close();

        return hset;

    }


    public long incr(String key) {

        Jedis resource = getResource();

        Long incr = resource.incr(key);

        resource.close();

        return incr;

    }


    public long expire(String key, Integer second) {

        Jedis resource = getResource();

        Long expire = resource.expire(key, second);

        resource.close();

        return expire;

    }


    public long ttl(String key) {

        Jedis resource = getResource();

        Long ttl = resource.ttl(key);

        resource.close();

        return ttl;
    }


    public long del(String key) {

        Jedis resource = getResource();

        Long del = resource.del(key);

        resource.close();

        return del;
    }


    public long hdel(String hkey, String key) {

        Jedis resource = getResource();

        Long hdel = resource.hdel(hkey, key);

        resource.close();

        return hdel;
    }


    /*public Object getObject(String key) {
        Jedis resource = getResource();

        String str = resource.get(key);

        resource.close();

        return SerializeUtils.deSerialize(str);


    }*/


    public void setObject(String key,String object) {
        Jedis resource = getResource();
        resource.set(key, object);
        resource.close();
    }


    public Long sadd(String key, String value) {
        // TODO Auto-generated method stub
        Jedis resource = getResource();
        Long result = resource.sadd(key, value);
        resource.close();
        return result;
    }


    public Boolean sismember(String key, String value) {
        // TODO Auto-generated method stub
        Jedis resource = getResource();
        Boolean result = resource.sismember(key, value);
        resource.close();
        return result;
    }


    public String spop(String key) {
        Jedis resource = getResource();
        String result =  resource.spop(key);
        resource.close();
        return result;
    }


    public Long srem(String key, String value) {
        Jedis resource = getResource();
        Long result = resource.srem(key, value);
        resource.close();
        return result;

    }

    /*public void mget() {

    	Jedis resource = getResource();
    	resource.mget
    }*/

    public Long zadd(String key,double score,String value) {
        Jedis resource = getResource();
        Long result = resource.zadd(key, score, value);
        resource.close();
        return result;
    }


    public Long zrank(String key ,String value) {
        Jedis resource = getResource();
        Long result = resource.zrank(key, value);
        resource.close();
        return result;
    }


    public Long zremrangebyscore(String key,double min,double max) {
        Jedis resource = getResource();
        Long result = resource.zremrangeByScore(key, min, max);
        resource.close();
        return result;
    }


    public void zrem(String key, String value) {
        // TODO Auto-generated method stub

        Jedis resource = getResource();
        resource.zrem(key, value);
        resource.close();
    }
}
