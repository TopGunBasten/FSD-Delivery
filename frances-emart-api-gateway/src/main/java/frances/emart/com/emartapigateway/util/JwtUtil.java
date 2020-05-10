package frances.emart.com.emartapigateway.util;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken!=null && !bearerToken.isEmpty() && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
