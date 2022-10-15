package cn.beichenhpy.redisdbsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableCaching(order = Ordered.HIGHEST_PRECEDENCE)
@EnableTransactionManagement
@SpringBootApplication
public class RedisDbSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDbSampleApplication.class, args);
    }

}
