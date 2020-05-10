package frances.emart.com.emartdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EmartDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmartDiscoveryServiceApplication.class, args);
	}

}
