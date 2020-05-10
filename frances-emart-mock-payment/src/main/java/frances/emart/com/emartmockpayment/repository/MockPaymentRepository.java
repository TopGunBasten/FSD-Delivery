package frances.emart.com.emartmockpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartmockpayment.model.MockPayment;

@Repository
public interface MockPaymentRepository extends JpaRepository<MockPayment, String> {

    @Query("select m from MockPayment m where clientId = :clientId and orderId = :orderId")
    MockPayment getPayment(String clientId, String orderId);
     
}