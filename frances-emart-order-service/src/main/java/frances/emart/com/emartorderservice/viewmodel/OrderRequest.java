package frances.emart.com.emartorderservice.viewmodel;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderRequest {

    @NotBlank
    private String buyerId;

    private String discountCode;
 
    private int discountPercentage;

    @NotNull
    private int taxRate;

    @NotBlank
    private List<OrderLineRequest> lines;
}