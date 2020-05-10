package frances.emart.com.emartapigateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "emart-identity-service")
@RequestMapping(path = "api/v1")
@Service
public interface UserDetailsProxyService {

    @RequestMapping(path = "/auth/tokens", method = RequestMethod.GET)
    EmartUserDetails loadUserByToken(@RequestParam("token") String token);
    
}