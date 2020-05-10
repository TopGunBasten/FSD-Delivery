package frances.emart.com.emartfinancialservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmartFinancialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmartFinancialServiceApplication.class, args);
	}

}
