package frances.emart.com.emartmockpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartmockpayment.model.MockPayment;
import frances.emart.com.emartmockpayment.service.MockPaymentService;
import frances.emart.com.emartmockpayment.viewmodel.PaymentRequest;

@RestController
public class MockPaymentController {

    @Autowired
    private MockPaymentService mockPaymentService;

    @RequestMapping(path = "/payments", method = RequestMethod.POST)
    public MockPayment createPayment(@RequestBody PaymentRequest payment) {
        return this.mockPaymentService.acceptPayment(payment);
    }

    @RequestMapping(path = "/payments", method = RequestMethod.GET)
    public MockPayment getPayment(@RequestParam("clientId") String clientId, @RequestParam("orderId") String orderId) {
        return this.mockPaymentService.getMockPayment(clientId, orderId);
    }
    
}