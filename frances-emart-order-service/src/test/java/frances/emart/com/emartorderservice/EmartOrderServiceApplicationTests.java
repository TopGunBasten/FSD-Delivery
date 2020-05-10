package frances.emart.com.emartorderservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import frances.emart.com.emartorderservice.model.Order;
import frances.emart.com.emartorderservice.service.OrderService;
import frances.emart.com.emartorderservice.viewmodel.OrderLineRequest;
import frances.emart.com.emartorderservice.viewmodel.OrderRequest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class EmartOrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private OrderService orderService;

	private Order resultOrder;

	private OrderLineRequest line1;
	private OrderLineRequest line2;

	@Test
	void createOrderTest(){
		OrderRequest request = new OrderRequest();
		request.setBuyerId(UUID.randomUUID().toString());
		request.setDiscountCode(UUID.randomUUID().toString());
		request.setDiscountPercentage(5);
		this.line1 = new OrderLineRequest();
		line1.setItemId(UUID.randomUUID().toString());
		line1.setItemName("test line 1");
		line1.setPrice(new BigDecimal(1000));
		line1.setQuantity(2);

		this.line2 = new OrderLineRequest();
		line2.setItemId(UUID.randomUUID().toString());
		line2.setItemName("test line 2");
		line2.setPrice(new BigDecimal(2000));
		line2.setQuantity(2);
		
		List<OrderLineRequest> lines = new ArrayList<>();

		lines.add(line1);
		lines.add(line2);
		 
		request.setLines(lines);

		this.resultOrder = this.orderService.createOrder(request);

		assertNotNull(resultOrder.getId());
		assertEquals(2, resultOrder.getLines().size());

	}

	@AfterAll
	void showDown() {
		this.orderService.removeOrder(this.resultOrder);
	 
	}

}
