package frances.emart.com.emartorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmartOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmartOrderServiceApplication.class, args);
	}

}
