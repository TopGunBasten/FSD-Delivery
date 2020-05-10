package frances.emart.com.emartinventoryservice.viewmodel;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CatagoryRequest {
    @NotBlank
    private String name;
    
    @NotBlank
    private String brief;
}