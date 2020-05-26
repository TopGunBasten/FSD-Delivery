package frances.emart.com.emartfinancialservice.listener;

import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import frances.emart.com.emartfinancialservice.message.Order;
import frances.emart.com.emartfinancialservice.service.EmartTranscationService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

  @Autowired
  private EmartTranscationService emartTranscationService;


  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    log.info("Received <" + message + ">");
    latch.countDown();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Order order = objectMapper.readValue(message, Order.class);
      this.emartTranscationService.createTranscations(order);
    } catch (JsonMappingException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
 
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}