package frances.emart.com.emartorderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartorderservice.model.Order;
import frances.emart.com.emartorderservice.service.OrderService;
import frances.emart.com.emartorderservice.viewmodel.OrderRequest;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(path="/orders" ,method = RequestMethod.POST)
    public Order createOrder(@RequestBody OrderRequest order){
        return this.orderService.createOrder(order);
    }

    @RequestMapping(path="/orders" ,method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@RequestParam("id") String id) {
        Order order = this.orderService.getOrder(id);
        if(order == null)
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(path="/orders/byUser" ,method = RequestMethod.GET)
    public List<Order> getOrderPerUser(@RequestParam("userId") String userId) {
       return this.orderService.getOrderPerUser(userId);

    }


}