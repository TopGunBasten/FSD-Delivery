package frances.emart.com.emartapigateway.service;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmartUserDetails {

    private String id;

    private String username;

    private String email;

    private List<String> roles;

    private boolean isActive;

    private LocalDateTime createdDate;
}

