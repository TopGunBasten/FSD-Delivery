package frances.emart.com.emartinventoryservice.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StockRequest {

    @NotBlank
    private String id;
    @NotBlank
    @Min(1)
    private Number stockNumber;
    
}