package frances.emart.com.emartorderservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartorderservice.config.RabbitMQConfig;
import frances.emart.com.emartorderservice.model.Order;
import frances.emart.com.emartorderservice.model.OrderLine;
import frances.emart.com.emartorderservice.model.OrderStatus;
import frances.emart.com.emartorderservice.repository.OrderLineRepository;
import frances.emart.com.emartorderservice.repository.OrderRepository;
import frances.emart.com.emartorderservice.viewmodel.OrderLineRequest;
import frances.emart.com.emartorderservice.viewmodel.OrderRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig mqConfig;

    @Transactional
    public Order createOrder(final OrderRequest request) {
        final Order order = this.orderRepository.save(new Order(request));
        for (final OrderLineRequest line : request.getLines()) {
            order.getLines().add(new OrderLine(line, order));
        }
        final List<OrderLine> lines = this.orderLineRepository.saveAll(order.getLines());
        order.setLines(lines);
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String orderJson;
        try {
            orderJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
            this.rabbitTemplate.convertAndSend(mqConfig.getExchange(), mqConfig.getRoutingkey(), orderJson);
            log.info("order id:"+order.getId()+"send to MQ");
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } 
        return order;
    }

    public Order getOrder(final String id) {
        final Optional<Order> order = this.orderRepository.findById(id);
        if (!order.isPresent())
            return null;
        final Order result = order.get();
        result.setLines(this.orderLineRepository.findByOrder(result));
        return result;
    }

    public void updateOrderStatus(final String id, final OrderStatus status) {
        final Optional<Order> order = this.orderRepository.findById(id);
        if (!order.isPresent())
            return;
        final Order updatedOrder = order.get();
        updatedOrder.setStatus(status);
        this.orderRepository.save(updatedOrder);
    }

    @Transactional
    public void removeOrder(final Order resultOrder) {
        this.orderLineRepository.deleteAll(resultOrder.getLines());
        this.orderRepository.delete(resultOrder);
	}

	public List<Order> getOrderPerUser(String userId) {
         List<Order> orders = this.orderRepository.findByBuyerId(userId);
         for(Order order: orders){
             order.setLines(this.orderLineRepository.findByOrder(order));
         }
         return orders;
	}
}