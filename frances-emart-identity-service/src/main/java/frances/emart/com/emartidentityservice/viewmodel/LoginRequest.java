package frances.emart.com.emartidentityservice.viewmodel;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "username is not allowed to empty or null")
    private String usernameOrEmail;

    @NotBlank(message = "username is not allowed to empty or null")
    private String password;


}
