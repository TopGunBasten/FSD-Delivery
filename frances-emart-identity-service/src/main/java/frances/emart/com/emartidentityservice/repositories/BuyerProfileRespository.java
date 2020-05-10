package frances.emart.com.emartidentityservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartidentityservice.models.BuyerProfile;

@Repository
public interface BuyerProfileRespository extends JpaRepository<BuyerProfile, String> {
    
}