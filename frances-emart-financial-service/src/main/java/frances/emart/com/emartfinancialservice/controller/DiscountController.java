package frances.emart.com.emartfinancialservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartfinancialservice.model.Discount;
import frances.emart.com.emartfinancialservice.service.DiscountService;

@RestController
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @RequestMapping(path="/discounts/all" ,method = RequestMethod.GET)
    public List<Discount> getDiscountByBuyerId(@RequestParam("buyerId") String buyerId) {

        return this.discountService.getUserDiscounts(buyerId);

    }


    @RequestMapping(path="/discounts/active" ,method = RequestMethod.GET)
    public List<Discount> getActiveDiscountByBuyerId(@RequestParam("buyerId") String buyerId) {
        return this.discountService.getUserActiveDiscounts(buyerId);
    }
    
}