package cn.marve1ous.util;

import org.apache.ibatis.cache.decorators.LoggingCache;

public class LoggingRedisCache extends LoggingCache {

    public LoggingRedisCache(String id) {
        super(new RedisCache(id));
    }

}