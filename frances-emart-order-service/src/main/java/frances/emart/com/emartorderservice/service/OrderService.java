package frances.emart.com.emartorderservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartorderservice.model.Order;
import frances.emart.com.emartorderservice.model.OrderLine;
import frances.emart.com.emartorderservice.model.OrderStatus;
import frances.emart.com.emartorderservice.repository.OrderLineRepository;
import frances.emart.com.emartorderservice.repository.OrderRepository;
import frances.emart.com.emartorderservice.viewmodel.OrderLineRequest;
import frances.emart.com.emartorderservice.viewmodel.OrderRequest;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Transactional
    public Order createOrder(OrderRequest request){
        Order order = this.orderRepository.save(new Order(request));
        for(OrderLineRequest line : request.getLines()){
            order.getLines().add(new OrderLine(line, order));
        }
        List<OrderLine> lines = this.orderLineRepository.saveAll(order.getLines());
        order.setLines(lines);
        return order;
    }

    public Order getOrder(String id) {
        Optional<Order> order = this.orderRepository.findById(id);
        if(!order.isPresent()) return null;
        Order result = order.get();
        result.setLines(this.orderLineRepository.findByOrder(result));
        return result;
    }

    public void updateOrderStatus(String id, OrderStatus status) {
        Optional<Order> order = this.orderRepository.findById(id);
        if(!order.isPresent()) return;
        Order updatedOrder = order.get();
        updatedOrder.setStatus(status);
        this.orderRepository.save(updatedOrder);
    }

    @Transactional
	public void removeOrder(Order resultOrder) {
        this.orderLineRepository.deleteAll(resultOrder.getLines());
        this.orderRepository.delete(resultOrder);
	}
}