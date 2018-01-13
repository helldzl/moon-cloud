package org.moonframework.feign;

import feign.hystrix.FallbackFactory;
import org.moonframework.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/1/13
 */
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                // TODO something
                System.out.println("fallback; reason was: " + throwable.getMessage());
                return null;
            }
        };
    }
}
