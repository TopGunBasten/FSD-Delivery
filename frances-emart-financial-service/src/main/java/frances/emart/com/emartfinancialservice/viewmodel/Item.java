package frances.emart.com.emartfinancialservice.viewmodel;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String id;

    private String name;
  
    private BigDecimal price;

    private int stockNumber;

    private String sellerId;
}