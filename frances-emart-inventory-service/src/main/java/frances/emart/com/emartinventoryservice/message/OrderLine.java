package frances.emart.com.emartinventoryservice.message;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class OrderLine {

    public OrderLine()
    {
        
    }

    private String id;

    private String itemId;

    private String itemName;

    private BigDecimal price;

    private int quantity;

    private String thumbnail;
    
    private Order order;

}