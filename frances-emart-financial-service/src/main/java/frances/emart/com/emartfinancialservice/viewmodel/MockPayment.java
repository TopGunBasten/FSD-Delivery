package frances.emart.com.emartfinancialservice.viewmodel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MockPayment {

    public MockPayment(){
        createDate = LocalDateTime.now();
    }

    private String id;

    private String clientId;

    private BigDecimal total;

   
    private String orderId;
    

    private LocalDateTime createDate;

    private String remarks;
    
}