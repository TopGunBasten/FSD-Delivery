package frances.emart.com.emartorderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartorderservice.model.Order;
import frances.emart.com.emartorderservice.model.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine ,String> {
    List<OrderLine>  findByOrder(Order order);
}