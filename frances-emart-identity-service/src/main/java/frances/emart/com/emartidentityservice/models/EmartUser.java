package frances.emart.com.emartidentityservice.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartidentityservice.viewmodel.Buyer;
import frances.emart.com.emartidentityservice.viewmodel.Seller;
import lombok.Data;

@Data
@Entity
@Table(name="emart_user", indexes = {
    @Index(name = "emart_user_username_index",columnList = "username"),
    @Index(name = "emart_user_email_index",columnList = "email")
})
public class EmartUser {
    
    public EmartUser(Seller seller) {
        this.username = seller.getUsername();
        this.password = seller.getPassword();
        this.email = seller.getEmail();
        this.sellerProfile = new SellerProfile();
        this.sellerProfile.setBrief(seller.getBrief());
        this.sellerProfile.setCompanyName(seller.getCompanyName());
        this.sellerProfile.setContractNumber(seller.getContractNumber());
        this.sellerProfile.setGSTIN(seller.getGSTIN());
        this.sellerProfile.setPostalAddress(seller.getPostalAddress());
        this.sellerProfile.setWebSite(seller.getWebSite());
        this.setActive(true);
        createdDate =  LocalDateTime.now();
    }

    public EmartUser(Buyer buyer) {
        this.username = buyer.getUsername();
        this.password = buyer.getPassword();
        this.email = buyer.getEmail();
        this.buyerProfile = new BuyerProfile();
        this.buyerProfile.setMobileNumber(buyer.getMobileNumber());
        this.setActive(true);
        createdDate =  LocalDateTime.now();
    }

    public EmartUser(){
        createdDate =  LocalDateTime.now();
    }
    
    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    private String id;

    @Column(name="username",length = 64, nullable = false, unique=true)
    private String username;
    
    @Column(name="email",length = 64, nullable = false, unique = true)
    private String email;

    @Column(name="password",length = 64, nullable = false)
    @JsonIgnore
    private String password;
    
    @OneToOne(fetch = FetchType.LAZY,targetEntity = SellerProfile.class)
    @JoinColumn(name="seller_proflie_id",referencedColumnName ="id", updatable = false)
    private SellerProfile sellerProfile;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = BuyerProfile.class)
    @JoinColumn(name="buyer_proflie_id",referencedColumnName ="id", updatable = false)
    private BuyerProfile buyerProfile;
    
    @Column(name="is_active", columnDefinition = "boolean default true")
    private boolean isActive;

    @Column(name="is_admin", columnDefinition = "boolean default false")
    private boolean isAdmin;

    @Column(name= "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate; 
}