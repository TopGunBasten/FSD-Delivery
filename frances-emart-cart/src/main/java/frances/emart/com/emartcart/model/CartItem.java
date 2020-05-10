package frances.emart.com.emartcart.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CartItem implements Serializable {

    private static final long serialVersionUID = 3920485639219693130L;

    public CartItem() {
        id = UUID.randomUUID().toString();
    }
    private String id;
    @NotBlank
    private String itemId;
    @NotBlank
    private String itemName;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private Number quantity;
}