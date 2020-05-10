package frances.emart.com.emartcart.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import frances.emart.com.emartcart.model.EmartCart;

@EnableAutoConfiguration
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, EmartCart> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, EmartCart> redisTemplate = new RedisTemplate<String, EmartCart>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}