package frances.emart.com.emartorderservice.viewmodel;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderLineRequest { 
    @NotBlank    
    private String itemId;
    @NotBlank 
    private String itemName;
    @NotBlank  
    private BigDecimal price;
    @NotBlank
    private Number quantity;
}
