package com.sky.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.entity.DishFlavor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

@SpringBootTest
public class SpringDataRedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void test() {
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations listOperations = redisTemplate.opsForList();
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testString() throws JsonProcessingException {
        redisTemplate.opsForValue().set("city", "shanghai");
        DishFlavor dishFlavor = new DishFlavor(1L, 1L, "name", "xing");
        // 序列化
        String serialize = objectMapper.writeValueAsString(dishFlavor);
        redisTemplate.opsForValue().set("dishFlavor", serialize);
        String json = redisTemplate.opsForValue().get("dishFlavor");
        // 手动反序列化
        DishFlavor dishFlavor1 = objectMapper.readValue(json, DishFlavor.class);
        System.out.println(dishFlavor1);
    }
}
