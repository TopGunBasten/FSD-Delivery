package frances.emart.com.emartcart.viewmodel;

import javax.validation.constraints.NotBlank;

import frances.emart.com.emartcart.model.CartItem;
import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank
    private String userId;
    @NotBlank
    private CartItem cart;
    
}