package frances.emart.com.emartfinancialservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {

    public PurchaseHistory(){
        this.createDate = LocalDateTime.now();
    }

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "purchase-history-uuid", strategy = "uuid")
    @GeneratedValue(generator = "purchase-history-uuid")
    private String id;

    @Column(name="buyer_id",nullable = false, length = 64)
    private String buyerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = EmartTranscation.class)
    @JoinColumn(name="emart_transcation_id",referencedColumnName ="id", updatable = false)
    private EmartTranscation transcation;

    @Column(name="item_id",nullable = false, length = 64)
    private String itemId;

    @Column(name="number",nullable = false)
    private Number numberOfItem;

    @Column(name= "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

    @Column(name= "remarks",  nullable = false, length = 200)
    private String remarks;
}