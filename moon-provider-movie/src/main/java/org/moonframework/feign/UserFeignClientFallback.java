package org.moonframework.feign;

import org.moonframework.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author quzile
 * @version 1.0
 * @since 2018/1/13
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setName("默认用户22");
        return user;
    }

}
