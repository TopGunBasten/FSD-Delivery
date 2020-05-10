package frances.emart.com.emartinventoryservice.viewmodel;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SubCatagoryRequest {

    @NotBlank
    private String catagoryId;

    @NotBlank
    private String brief;

    @NotBlank
    private String gts;

    @NotBlank
    private String name;
}