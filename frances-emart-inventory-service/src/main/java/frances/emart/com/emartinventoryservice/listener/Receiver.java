package frances.emart.com.emartinventoryservice.listener;

import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import frances.emart.com.emartinventoryservice.message.Order;
import frances.emart.com.emartinventoryservice.message.OrderLine;
import frances.emart.com.emartinventoryservice.model.Item;
import frances.emart.com.emartinventoryservice.service.ItemService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

  @Autowired
  private ItemService itemService;

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    log.info("Received <" + message + ">");
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Order order = objectMapper.readValue(message, Order.class);
      for (OrderLine line : order.getLines()) {
        Item item = this.itemService.getItem(line.getItemId());
        this.itemService.updateStockNumber(item.getId(),item.getStockNumber()-line.getQuantity());
      }
      
    } catch (JsonMappingException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}