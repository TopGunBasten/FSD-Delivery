package frances.emart.com.emartmockpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartmockpayment.model.MockPayment;
import frances.emart.com.emartmockpayment.repository.MockPaymentRepository;
import frances.emart.com.emartmockpayment.viewmodel.PaymentRequest;

@Service
public class MockPaymentService {

    @Autowired
    private MockPaymentRepository mockPaymentRepository;

    public MockPayment acceptPayment(PaymentRequest request){
       return this.mockPaymentRepository.save(new MockPayment(request));
    }

    public MockPayment getMockPayment(String clientId, String orderId){
        return this.getMockPayment(clientId, orderId);
    }
    
}