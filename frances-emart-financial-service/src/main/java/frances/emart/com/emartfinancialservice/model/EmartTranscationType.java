package frances.emart.com.emartfinancialservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;


public enum EmartTranscationType {
    PAID("paid"),
    DISCOUNT("discount"),
    DEPOSIT("deposit"),
    ROllBACK("rollback");
    

    private final String type;
    
    @JsonValue
    public String getType() {
      return type;
    }

    private EmartTranscationType(String type) {
        this.type = type;
    }

    @JsonCreator
    public static EmartTranscationType of(final String type) {
      return Stream.of(EmartTranscationType.values())
          .filter(p -> p.getType().equalsIgnoreCase(type))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
    
}