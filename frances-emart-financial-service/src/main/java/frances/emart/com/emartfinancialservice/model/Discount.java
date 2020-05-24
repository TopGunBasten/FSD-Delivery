package frances.emart.com.emartfinancialservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartfinancialservice.viewmodel.DiscountRequest;
import lombok.Data;

@Data
@Entity
@Table(name="discount")
public class Discount {
   
    public Discount() {
        isApplied = false;
    }

    public Discount(DiscountRequest request) {
        this.startDate = request.getStartDate();
        this.endDate  = request.getEndDate();
        this.description = request.getDescription();
        this.percent = request.getPercent();
        this.buyerId = request.getBuyerId();
        isApplied = false;
    }

    @Id
    @Column(name="id", nullable = false, length = 64)
    @GenericGenerator(name = "discount-uuid", strategy = "uuid")
    @GeneratedValue(generator = "discount-uuid")
    private String code;

    @Column(name= "start_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;

    @Column(name= "end_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime endDate;

    @Column(name="description",nullable = false, length = 200)
    private String description;

    @Column(name="is_applied", nullable = false)
    private boolean isApplied;

    @Column(name="percent", nullable = false)
    private int percent; 
    
    @Column(name="buyer_id", length = 64)
    private String buyerId;

}