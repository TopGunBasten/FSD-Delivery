package frances.emart.com.emartidentityservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfig {
    private String key = "emart";
    private Long ttl = 3600000L;
}