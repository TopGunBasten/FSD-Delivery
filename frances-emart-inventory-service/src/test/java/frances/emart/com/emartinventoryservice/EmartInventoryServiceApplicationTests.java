package frances.emart.com.emartinventoryservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import frances.emart.com.emartinventoryservice.model.Catagory;
import frances.emart.com.emartinventoryservice.model.Item;
import frances.emart.com.emartinventoryservice.model.SubCatagory;
import frances.emart.com.emartinventoryservice.service.CatagoryService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class EmartInventoryServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CatagoryService  catagoryService;

	private Item itemOne;

	private Item itemTwo;
	
	private Catagory electronicProduct;
	private SubCatagory pcCatagory;
	private SubCatagory mobileCatagory;
	
	@Test
	void createCatagoryTest(){
		electronicProduct = new Catagory();
		electronicProduct.setName("Electronic Product");
		electronicProduct.setBrief("like mobile, podding, loptop");
		Catagory newCatagory = this.catagoryService.createCatagory(electronicProduct);
		assertNotNull(newCatagory.getId());
		assertEquals(newCatagory.getBrief(), electronicProduct.getBrief());
		assertEquals(newCatagory.getName(), electronicProduct.getName());
	}


	@AfterAll
	void showDown() {
		this.catagoryService.removeCatagory(this.electronicProduct);
	 
	}



}
