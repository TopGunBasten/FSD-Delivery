package frances.emart.com.emartfinancialservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "emart_transcation")
@AllArgsConstructor
@NoArgsConstructor
public class EmartTranscation {

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "emart-transcation-uuid", strategy = "uuid")
    @GeneratedValue(generator = "emart-transcation-uuid")
    private String id;

    @Column(name="order_id",nullable = false, length = 64)
    private String orderId;

    @Column(name="buyer_id",nullable = false, length = 64)
    private String buyerId;

    @Column(name="seller_id",nullable = false, length = 64)
    private String sellerId;

    @Column(name= "type",  nullable = false)
    @Enumerated(EnumType.STRING)
    private EmartTranscationType type;

    @Column(name= "remarks",  nullable = false, length = 200)
    private String remarks;

    @Column(name="total",nullable = false)
    private BigDecimal total;

    @Column(name="exernal_trans_id")
    private String exernalTransId;

    @Column(name= "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate; 

}