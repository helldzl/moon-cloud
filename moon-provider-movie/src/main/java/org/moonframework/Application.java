package org.moonframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 *
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class Application {

//    /**
//     * 使用feign, 就不需要显式声明  @LoadBalanced
//     * @return
//     */
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
