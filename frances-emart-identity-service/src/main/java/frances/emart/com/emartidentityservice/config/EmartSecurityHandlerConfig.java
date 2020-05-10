package frances.emart.com.emartidentityservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.access.AccessDeniedHandler;

import frances.emart.com.emartidentityservice.viewmodel.EmartResponse;

@Configuration
public class EmartSecurityHandlerConfig {

    private ObjectMapper mapper = new ObjectMapper();

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            if (!response.isCommitted()) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                mapper.writeValue(response.getWriter(),
                        EmartResponse.of("you cann't perform the action", "401", HttpStatus.FORBIDDEN));
            }

        };
    }
}
