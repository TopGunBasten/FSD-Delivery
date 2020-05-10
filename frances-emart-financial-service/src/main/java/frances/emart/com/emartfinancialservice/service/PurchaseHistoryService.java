package frances.emart.com.emartfinancialservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartfinancialservice.model.PurchaseHistory;
import frances.emart.com.emartfinancialservice.repository.PurchaseHistoryRepository;

@Service
public class PurchaseHistoryService {
    
    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    public List<PurchaseHistory> getHistoryPerUser(String userId) {
        return this.purchaseHistoryRepository.findByBuyerId(userId);
    }

    public PurchaseHistory createPurchaseHistory(PurchaseHistory purchaseHistory){
        return this.purchaseHistoryRepository.save(purchaseHistory);
    }
    
}