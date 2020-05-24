package frances.emart.com.emartfinancialservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartfinancialservice.model.EmartTranscation;

@Repository
public interface EmartTranscationRepository extends JpaRepository<EmartTranscation, String>{
   
   @Query("select t from EmartTranscation t where buyerId= :buyerId and type = 'deposit' and createdDate between :startDate  and :endDate")
   List<EmartTranscation> getBuyerReport(LocalDateTime startDate, LocalDateTime endDate, String buyerId);

   List<EmartTranscation> findByOrderId(String orderId);
}