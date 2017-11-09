package com.example.data.combine.service.impl;

import com.example.data.combine.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * Created by khan on 11/10/17.
 */
@Service
public class CacheServiceImpl implements CacheService{
  public static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);

  @Autowired
  private RedisConnectionFactory factory;

  @Override
  public void flushDB() {
    LOGGER.info("flush redis db");
    RedisConnection redisConnection =null;
    try{
      redisConnection=factory.getConnection();
      redisConnection.flushDb();
    }catch (Exception e){
      LOGGER.error("failed to flush db  with error: {}",e.getMessage(),e);
    }finally {
      if(redisConnection!=null){
        redisConnection.close();
      }
    }
  }
}
