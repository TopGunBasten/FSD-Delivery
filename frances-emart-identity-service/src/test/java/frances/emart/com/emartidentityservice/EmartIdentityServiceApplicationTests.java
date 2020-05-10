package frances.emart.com.emartidentityservice;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import frances.emart.com.emartidentityservice.models.BuyerProfile;
import frances.emart.com.emartidentityservice.models.EmartUser;
import frances.emart.com.emartidentityservice.models.SellerProfile;
import frances.emart.com.emartidentityservice.services.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class EmartIdentityServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeAll
	 void setUp() {
		initSeller();
		initBuyer();
	}
	
	private final String password = "ILoveJava";
	private final String sellerName = "peter";
	private final String sellerEmail="peter@163.com";
	private final String sellerBrief = "I am Ali";
	private final String sellerCompanyName ="IBM";
	private final String sellerGSTIN="0002333";
	private final String sellerPostalAddress ="xxx xxxxx 3kkfdks xxxkf xxdfd";
	private final String sellerWebsite = "https://www.ibm.com";
	private final String sellerContractNumber="00132321312";

	private final String buyerName="Janet";
	private final String buyerEmail="Janet@alipay.com";
	private final String buyerMobile="12343345343";
	// private final String buyer


	private void initBuyer() {
	  this.buyer = new EmartUser();
	  this.buyer.setUsername(buyerName);
	  this.buyer.setEmail(buyerEmail);
	  this.buyer.setPassword(password);
	  BuyerProfile buyerBuyerProfile = new BuyerProfile();
	  buyerBuyerProfile.setMobileNumber(buyerMobile);
	  this.buyer.setBuyerProfile(buyerBuyerProfile);
	}

	private void initSeller() {
		this.seller = new EmartUser();
		this.seller.setUsername(sellerName);
		this.seller.setEmail(sellerEmail);
		this.seller.setPassword(password);
		final SellerProfile profile = new SellerProfile();
		profile.setBrief(sellerBrief);
		profile.setCompanyName(sellerCompanyName);
		profile.setContractNumber(sellerContractNumber);
		profile.setGSTIN(sellerGSTIN);
		profile.setPostalAddress(sellerPostalAddress);
		profile.setWebSite(sellerWebsite);
		this.seller.setSellerProfile(profile);
	}

	private EmartUser buyer;
	private EmartUser seller;

	@Autowired
	@Qualifier("EmartUserService")
	private UserService emartUserService;

	@Test
	void RegisterBuyerTest() {
		EmartUser buyer = this.emartUserService.registerBuyer(this.buyer);
		assertEquals(buyer.getUsername(),buyerName);
		assertEquals(buyer.getEmail(), buyerEmail);
		assertNotNull(buyer.getId());
		assertNotNull(buyer.getBuyerProfile().getId());
		assertEquals(buyer.getBuyerProfile().getMobileNumber(),buyerMobile);
	}

	void RegisterSellerTest() {
		EmartUser seller = this.emartUserService.registerSeller(this.seller);
		assertEquals(seller.getUsername(), sellerName);
		assertEquals(seller.getEmail(), sellerEmail);
		assertEquals(seller.getSellerProfile().getBrief(), sellerBrief);
		assertEquals(seller.getSellerProfile().getCompanyName(), sellerCompanyName);
		assertEquals(seller.getSellerProfile().getContractNumber(), sellerContractNumber);
		assertEquals(seller.getSellerProfile().getPostalAddress(), sellerPostalAddress);
		assertEquals(seller.getSellerProfile().getGSTIN(), sellerGSTIN);
		assertEquals(seller.getSellerProfile().getWebSite(), sellerWebsite);
		assertNotNull(seller.getId());
		assertNotNull(seller.getSellerProfile().getId());
	}

	@AfterAll
	void showDown() {
	  this.emartUserService.Remove(this.buyer);
	  this.emartUserService.Remove(this.seller);
	}

}
