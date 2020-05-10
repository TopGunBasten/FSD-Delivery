package frances.emart.com.emartmockpayment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartmockpayment.viewmodel.PaymentRequest;
import lombok.Data;

@Data
@Entity
@Table(name = "mock_payment")
public class MockPayment {

    public MockPayment(){
        createDate = LocalDateTime.now();
    }

    public MockPayment(PaymentRequest request){
        this.clientId = request.getClientId();
        this.createDate = LocalDateTime.now();
        this.orderId = request.getOrderId();
        this.remarks = request.getRemarks();
        this.total = request.getTotal();
    }

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "mocker-payment-uuid", strategy = "uuid")
    @GeneratedValue(generator = "mocker-payment-uuid")
    private String id;

    @Column(name = "client_id", length =64 )
    private String clientId;
    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "orderId", nullable = false)
    private String orderId;
    
    @Column(name= "create_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDate;

    @Column(name="remarks")
    private String remarks;
    
}