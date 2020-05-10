package frances.emart.com.emartmockpayment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import frances.emart.com.emartmockpayment.model.MockPayment;
import frances.emart.com.emartmockpayment.service.MockPaymentService;
import frances.emart.com.emartmockpayment.viewmodel.PaymentRequest;

@SpringBootTest
class EmartMockPaymenApplicationTests {

	@Test
	void contextLoads() {
	}

	private PaymentRequest payment;

	@Autowired
	private MockPaymentService mockPaymentService;

	@Test
	void createPaymentTests()
	{
		this.payment = new PaymentRequest();
		this.payment.setClientId("emart");
		this.payment.setOrderId(UUID.randomUUID().toString());
		this.payment.setRemarks("testing");
		this.payment.setTotal(new BigDecimal(10000));
		MockPayment payment = this.mockPaymentService.acceptPayment(this.payment);

		assertNotNull(payment.getId());
	}

}
