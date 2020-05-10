package frances.emart.com.emartfinancialservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import frances.emart.com.emartfinancialservice.model.Discount;
import frances.emart.com.emartfinancialservice.service.DiscountService;
import frances.emart.com.emartfinancialservice.viewmodel.DiscountRequest;

@SpringBootTest
class EmartFinancialServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private DiscountService discountService;

	private DiscountRequest request;

	@Test
	void createDisocuntTest(){
		this.request = new DiscountRequest();
		this.request.setBuyerId(UUID.randomUUID().toString());
		this.request.setDescription("testing discounting");
		this.request.setPercent(5);
		this.request.setStartDate(LocalDateTime.now());
		this.request.setEndDate(LocalDateTime.now().plusYears(1));
		Discount discount = this.discountService.createDiscount(request);

		assertNotNull(discount.getCode());
			
	}

}
