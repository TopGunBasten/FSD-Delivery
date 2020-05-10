package frances.emart.com.emartidentityservice.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Buyer {

    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String mobileNumber;
    
}