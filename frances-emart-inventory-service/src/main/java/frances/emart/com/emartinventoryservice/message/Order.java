package frances.emart.com.emartinventoryservice.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import lombok.Data;

@Data
public class Order {

    
    public Order(){
        createDate = LocalDateTime.now();
        this.lines = new ArrayList<>();
    }

    private String id;

    private String buyerId;

    private String discountCode;

    private int discountPercentage;

    private int taxRate;

    private LocalDateTime createDate;

    private OrderStatus status;
   
    private List<OrderLine> lines;

}