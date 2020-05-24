package frances.emart.com.emartinventoryservice.viewmodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StockRequest {

    @NotBlank
    private String id;
    @NotNull
    @Min(1)
    private int stockNumber;
    
}