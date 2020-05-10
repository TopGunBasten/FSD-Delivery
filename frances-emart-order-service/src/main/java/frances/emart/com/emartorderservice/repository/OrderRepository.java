package frances.emart.com.emartorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartorderservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    
}