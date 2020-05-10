package frances.emart.com.emartfinancialservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartfinancialservice.model.EmartTranscation;
import frances.emart.com.emartfinancialservice.repository.EmartTranscationRepository;

@Service
public class EmartTranscationService {

    @Autowired
    private EmartTranscationRepository emartTranscationRepository;

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
    
}