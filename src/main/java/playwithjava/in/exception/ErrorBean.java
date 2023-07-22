package playwithjava.in.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ErrorBean {
    String errorMsg;
    Integer errorCode;
    String recommendedAction;
}
