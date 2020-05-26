package frances.emart.com.emartfinancialservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import frances.emart.com.emartfinancialservice.viewmodel.Item;

@FeignClient(name = "emart-inventory-service")
@RequestMapping(path = "api/v1")
@Service
public interface ItemProxyService {

    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public Item getItem(@RequestParam("id") String id);
    
}