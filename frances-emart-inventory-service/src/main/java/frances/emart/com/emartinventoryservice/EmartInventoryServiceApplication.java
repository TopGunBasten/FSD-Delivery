package frances.emart.com.emartinventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class EmartInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmartInventoryServiceApplication.class, args);
	}

}
