package frances.emart.com.emartorderservice.viewmodel;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderRequest {

    @NotBlank
    private String buyerId;

    @NotBlank
    private String discountCode;
 
    @NotBlank
    private int discountPercentage;

    @NotBlank
    private List<OrderLineRequest> lines;
}