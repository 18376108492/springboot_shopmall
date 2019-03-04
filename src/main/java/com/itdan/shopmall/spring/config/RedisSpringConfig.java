package com.itdan.shopmall.spring.config;

import com.itdan.shopmall.utils.jedis.JedisClientPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * redis配置
 */
@Configuration
@PropertySource(value = "classpath:redis.properties")
public class RedisSpringConfig {

    @Value("${redis.maxTotal}")
    private Integer redisMaxTotal;

    @Value("${redis.node1.host}")
    private String redisNode1Host;

    @Value("${redis.node1.port}")
    private Integer redisNode1Port;

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        return jedisPoolConfig;
    }

    @Bean
    public ShardedJedisPool shardedJedisPool() {
        List<JedisShardInfo> jedisShardInfos = new ArrayList<JedisShardInfo>();
        jedisShardInfos.add(new JedisShardInfo(redisNode1Host, redisNode1Port));
        return new ShardedJedisPool(jedisPoolConfig(), jedisShardInfos);
    }

    //配置jedisPool
    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool=new JedisPool(redisNode1Host,redisNode1Port);
        return jedisPool;
    }

    /*
    * 配置JedisClientPool
    */
    @Bean
    public JedisClientPool jedisClientPool(JedisPool jedisPool){
        JedisClientPool jedisClientPool=new JedisClientPool();
        jedisClientPool.setJedisPool(jedisPool);
        return jedisClientPool;
    }



}



