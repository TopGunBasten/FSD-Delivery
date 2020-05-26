package frances.emart.com.emartfinancialservice.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;


public enum OrderStatus {
    ACCEPT("accept"),
    PAID("paid"),
    FINISHED("finished");

    private final String type;
    
    @JsonValue
    public String getType() {
      return type;
    }

    @JsonCreator
    public static OrderStatus of(final String type) {
      return Stream.of(OrderStatus.values())
          .filter(p -> p.getType().equalsIgnoreCase(type))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }

    private OrderStatus(String type) {
      this.type = type;
    }
}