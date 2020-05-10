package frances.emart.com.emartfinancialservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartfinancialservice.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String>{
    List<Discount> findByBuyerId(String buyerID);
}