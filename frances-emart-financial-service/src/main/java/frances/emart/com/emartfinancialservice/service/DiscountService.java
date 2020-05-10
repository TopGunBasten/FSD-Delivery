package frances.emart.com.emartfinancialservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartfinancialservice.model.Discount;
import frances.emart.com.emartfinancialservice.repository.DiscountRepository;
import frances.emart.com.emartfinancialservice.viewmodel.DiscountRequest;

@Service
public class DiscountService {
    
    @Autowired
    private DiscountRepository discountRepository;

    public Discount createDiscount(DiscountRequest request) {
       return this.discountRepository.save(new Discount(request));
    }

    public void applyDiscount(String code) {
        Optional<Discount> discount = this.discountRepository.findById(code);
        if(!discount.isPresent()) return;
        discount.get().setApplied(true);
        this.discountRepository.save(discount.get());
    }

    public List<Discount> getUserDiscounts(String userId) {
        return this.discountRepository.findByBuyerId(userId);
    }
}