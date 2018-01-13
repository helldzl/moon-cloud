package org.moonframework.feign;

import org.moonframework.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice-provider-user", fallback = UserFeignClientFallback.class, primary = true)
public interface UserFeignClient {

    /**
     * <p>断路器的状态监控</p>
     *
     * @param id id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

}





