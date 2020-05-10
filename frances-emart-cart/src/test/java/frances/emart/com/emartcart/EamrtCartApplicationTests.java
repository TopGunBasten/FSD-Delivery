package frances.emart.com.emartcart;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import frances.emart.com.emartcart.model.CartItem;
import frances.emart.com.emartcart.model.EmartCart;
import frances.emart.com.emartcart.service.CartService;

@SpringBootTest
class EamrtCartApplicationTests {

	@Autowired
	private CartService cartService;

	@Test
	void contextLoads() {
	}

	private String userId = UUID.randomUUID().toString();

	private  EmartCart cart;
	private CartItem item;

	@Test
	void getCartTests() {
		this.cart = this.cartService.getCart(userId);
		assertNotNull(this.cart);
	}

	@Test
	void addItemToCartTest() {
		this.item = new CartItem();
		this.item.setItemId( UUID.randomUUID().toString());
		this.item.setItemName("test");
		this.item.setPrice(new BigDecimal(50000));
		this.item.setQuantity(2);
		this.cartService.addItemToCart(this.userId, this.item);
		assertNotNull(this.cartService.getCart(this.userId).getItems().get(this.item.getId()));
	}





}
