package com.wangxin.langshu.business.edu.vo.user.old.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.wangxin.langshu.business.util.tools.JSONUtil;

@Component
public class CacheRedis {

	private static final int MINUTE = 5;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate redisTemplate) {
	    RedisSerializer stringSerializer = new StringRedisSerializer();
	    redisTemplate.setKeySerializer(stringSerializer);
	    redisTemplate.setValueSerializer(stringSerializer);
	    redisTemplate.setHashKeySerializer(stringSerializer);
	    redisTemplate.setHashValueSerializer(stringSerializer);
	    this.redisTemplate = redisTemplate;
	}
	

	/**
	 * 默认缓存5分钟
	 */
	public <T> T set5Minutes(Object key, T t) {
		if (t != null) {
			redisTemplate.opsForValue().set(key, JSONUtil.toJSONString(t), MINUTE, TimeUnit.MINUTES);
		}
		return t;
	}
	
	
	public <T> T set(Object key, T t) {
		if (t != null) {
			redisTemplate.opsForValue().set(key, JSONUtil.toJSONString(t));
		}
		return t;
	}

	/**
	 * 单位分钟
	 */
	public <T> T set(Object key, T t, Integer time) {
		if (t != null) {
			redisTemplate.opsForValue().set(key, JSONUtil.toJSONString(t), time, TimeUnit.MINUTES);
		}
		return t;
	}

	public <T> T get(Object key, Class<T> clazz) {
		if (redisTemplate.hasKey(key)) {
			return JSONUtil.parseObject(redisTemplate.opsForValue().get(key).toString(), clazz);
		}
		return null;
	}

	public void delete(Object key) {
		if (redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
	}
	
	
	

	public <T> List<T> list(Object key, Class<T> clazz) {
		if (redisTemplate.hasKey(key)) {
			return JSONUtil.parseArray(redisTemplate.opsForValue().get(key).toString(), clazz);
		}
		return null;
	}

	public boolean hasKey(Object key) {
		if (redisTemplate.hasKey(key)) {
			return true;
		}
		return false;
	}
	
	
	public Map<String,Object> batchQueryByKeys(List<String> keys,Boolean useParallel){
        if(null == keys || keys.size() == 0 ){
            return null;
        }
 
        if(null == useParallel){
            useParallel = true;
        }
 
        List<Object> results = redisTemplate.executePipelined(
                new RedisCallback<Object>() {
                    public Object doInRedis(RedisConnection connection) throws DataAccessException {
                        StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                        for(String key:keys) {
                            stringRedisConn.get(key);
                        }
                        return null;
                    }
                });
        if(null == results || results.size() == 0 ){return null;}
 
        Map<String,Object> resultMap  =  null;
 
        if(useParallel){
            Map<String,Object> resultMapOne  = Collections.synchronizedMap(new HashMap<String,Object>());
            keys.parallelStream().forEach(t -> {
                resultMapOne.put(t,results.get(keys.indexOf(t)));
            });
            resultMap = resultMapOne;
        }else{
            Map<String,Object> resultMapTwo  = new HashMap<>();
            for(String t:keys){
                resultMapTwo.put(t,results.get(keys.indexOf(t)));
            }
            resultMap = resultMapTwo;
        }
 
        return  resultMap;
 
    }

}
