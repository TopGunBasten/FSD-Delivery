package frances.emart.com.emartfinancialservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartfinancialservice.model.PurchaseHistory;
import frances.emart.com.emartfinancialservice.service.PurchaseHistoryService;

@RestController
public class PurchaseHistoryController {

    private PurchaseHistoryService purchaseHistoryService;

    @RequestMapping(path="/history" ,method = RequestMethod.GET)
    public List<PurchaseHistory> getPurchaseHistory(String userId) {
        return this.purchaseHistoryService.getHistoryPerUser(userId);
    }
    
}