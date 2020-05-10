package frances.emart.com.emartfinancialservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartfinancialservice.model.PurchaseHistory;
import freemarker.core.StringArraySequence;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, StringArraySequence> {
    List<PurchaseHistory> findByBuyerId(String userId);
}