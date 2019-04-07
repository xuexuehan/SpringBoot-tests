package com.xx.springboot;

import com.xx.springboot.entity.User;
import com.xx.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot13CacheApplicationTests {

	//操作的是复杂类型
	@Autowired
	RedisTemplate redisTemplate;

	//针对的都是操作字符串
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	//自定义的json序列化器
	@Autowired
	RedisTemplate jsonRedisTemplate;

	@Autowired
	UserService userService;

	/**
	 * 五大数据类型
	 * stringRedisTemplate.opsForValue();//操作字符串
	 stringRedisTemplate.opsForList();//操作list
	 stringRedisTemplate.opsForSet();//操作set
	 stringRedisTemplate.opsForZSet();//操作zset
	 stringRedisTemplate.opsForHash();//操作Hash

	 * */

	@Test
	public void contextLoads() {
		//stringRedisTemplate.opsForValue().set("name", "韩雪");
		String name = stringRedisTemplate.opsForValue().get("name");
		System.out.println(name);

        //stringRedisTemplate.opsForList().leftPush("myList", "a");
        //stringRedisTemplate.opsForList().leftPushAll("myList", "b", "c", "d");
        List<String> myList = stringRedisTemplate.opsForList().range("myList", 0, -1);
        System.out.println(myList);

    }

    @Test
    public void testRedis() {
        User user = userService.getUserById(1);
        //保存的数据对象必须序列化
        //redisTemplate.opsForValue().set("user", user);

        jsonRedisTemplate.opsForValue().set("user2", user);
        User user1 = (User) redisTemplate.opsForValue().get("user");

        System.out.println(user1);
    }

}
