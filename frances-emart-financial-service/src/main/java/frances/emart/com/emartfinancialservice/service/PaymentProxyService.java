package frances.emart.com.emartfinancialservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import frances.emart.com.emartfinancialservice.viewmodel.MockPayment;
import frances.emart.com.emartfinancialservice.viewmodel.PaymentRequest;

@FeignClient(name = "emart-mock-payment-service")
@RequestMapping(path = "api/v1")
@Service
public interface PaymentProxyService {
    @RequestMapping(path = "/payments", method = RequestMethod.POST)
    public MockPayment submitTransaction(@RequestBody PaymentRequest request);
}