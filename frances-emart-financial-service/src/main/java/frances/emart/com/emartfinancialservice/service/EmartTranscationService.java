package frances.emart.com.emartfinancialservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import frances.emart.com.emartfinancialservice.message.Order;
import frances.emart.com.emartfinancialservice.message.OrderLine;
import frances.emart.com.emartfinancialservice.model.EmartTranscation;
import frances.emart.com.emartfinancialservice.model.PurchaseHistory;
import frances.emart.com.emartfinancialservice.repository.EmartTranscationRepository;
import frances.emart.com.emartfinancialservice.repository.PurchaseHistoryRepository;
import frances.emart.com.emartfinancialservice.viewmodel.Item;
import frances.emart.com.emartfinancialservice.viewmodel.MockPayment;
import frances.emart.com.emartfinancialservice.viewmodel.PaymentRequest;

@Service
@Component
public class EmartTranscationService {

    @Autowired
    private EmartTranscationRepository emartTranscationRepository;

    @Autowired
    private ItemProxyService itemProxyService;

    @Autowired
    private PaymentProxyService paymentProxyService;

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    public List<EmartTranscation> getSellerReport(LocalDateTime startDate, LocalDateTime endDate, String buyerId) {
        return this.emartTranscationRepository.getBuyerReport(startDate, endDate,buyerId);
    }

    public void UpdateExternalTransId(String orderId, String transcationId) {
        List<EmartTranscation>  trans = this.emartTranscationRepository.findByOrderId(orderId);
        for(EmartTranscation tran : trans){
            tran.setExernalTransId(transcationId);
        }
        this.emartTranscationRepository.saveAll(trans);
    }

    @Transactional
    public void createTranscations(Order order) {
        String remarkFormat ="%s * %s-total:%s include tax:%s";
        BigDecimal total=BigDecimal.ZERO;
        BigDecimal taxTotal=BigDecimal.ZERO;
        BigDecimal discountTotal = BigDecimal.ZERO;
        BigDecimal percent;
        BigDecimal taxRate = new BigDecimal(order.getTaxRate()/100);
        String discountRemarkFormat="code %s with percent %";
        String discountRemark= null;
        if(!order.getDiscountCode().isEmpty()){
            percent = new BigDecimal((100-order.getDiscountPercentage())/100);
            discountRemark = String.format(discountRemarkFormat, order.getDiscountCode(), order.getDiscountPercentage());
        }
        else{
            percent =new BigDecimal(1);
        }
        
        for(OrderLine orderLine: order.getLines()) {
            BigDecimal line = orderLine.getPrice().multiply(new BigDecimal(orderLine.getQuantity()));
            BigDecimal lineTotal = line.multiply(percent);
            BigDecimal lineDiscount =lineTotal.subtract(line);
            BigDecimal lineTax =lineTotal.multiply(taxRate);
            taxTotal =taxTotal.add(lineTax);
            BigDecimal lineAllTotal = lineTotal.add(lineTax);
            total =total.add(lineAllTotal);
            discountTotal =discountTotal.add(lineDiscount);
            String lineRemarks = String.format(remarkFormat, 
            orderLine.getItemName(), 
            orderLine.getQuantity(), lineAllTotal.toPlainString(),lineTax.toPlainString());
            Item item = itemProxyService.getItem(order.getId());
            EmartTranscation buyerEmartTranscation = EmartTranscation.createBuyerTranscation(order.getId(), order.getBuyerId(), item.getSellerId(), lineRemarks, lineAllTotal);
            
            PurchaseHistory history = new PurchaseHistory();
            history.setBuyerId(order.getBuyerId());
            history.setItemId(orderLine.getItemId());
            history.setNumberOfItem(orderLine.getQuantity());
            history.setRemarks(lineRemarks);
            history.setTranscation(this.emartTranscationRepository.save(buyerEmartTranscation));
            this.purchaseHistoryRepository.save(history);

            EmartTranscation sellerEmartTranscation = EmartTranscation.createSellerTranscation(order.getId(), order.getBuyerId(), item.getSellerId(), lineRemarks, lineAllTotal);
            this.emartTranscationRepository.save(sellerEmartTranscation);
            if(!percent.equals(BigDecimal.ONE)){
                EmartTranscation discountTranscation =  EmartTranscation.createDiscountTranscation(order.getBuyerId(), order.getBuyerId(), item.getSellerId(), discountRemark, lineDiscount);
                this.emartTranscationRepository.save(discountTranscation);
            }
            
        }

        PaymentRequest request = new PaymentRequest();
        request.setClientId("emart");
        request.setOrderId(order.getId());
        request.setRemarks(String.format("order number:$", order.getId()));
        request.setTotal(total);
        MockPayment payment = this.paymentProxyService.submitTransaction(request);
        this.UpdateExternalTransId(order.getId(), payment.getId());
    }
    
}